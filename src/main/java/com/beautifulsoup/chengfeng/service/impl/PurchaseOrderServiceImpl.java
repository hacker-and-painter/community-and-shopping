package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.*;
import com.beautifulsoup.chengfeng.dao.*;
import com.beautifulsoup.chengfeng.enums.OrderStatus;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.*;
import com.beautifulsoup.chengfeng.service.PurchaseOrderService;
import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import com.beautifulsoup.chengfeng.utils.AssemblyDataUtil;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang3.RandomUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static com.beautifulsoup.chengfeng.constant.ChengfengConstant.RabbitMQ.UPDATE_ORDER_EXCHANGE;

@Slf4j
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    private AssembleMapper assembleMapper;

    @Autowired
    private AssembleItemMapper assembleItemMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseOrderItemMapper orderItemMapper;

    @Autowired
    private PurchaseProductSkuMapper productSkuMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PurchaseShippingMapper purchaseShippingMapper;

    @Autowired
    private KafkaTemplate<String, PurchaseInfoDto> kafkaTemplate;

    @Autowired
    private PurchaseCategoryMapper purchaseCategoryMapper;
    @Override
    public List<AssembleSimpleVo> listAllSimpleAssembleLists(Integer productId) {
        List<AssembleSimpleVo> assembleSimpleVos= Lists.newArrayList();
        List<Assemble> assembles = assembleMapper.listAllAssemblesByProductId(productId);
        assembles.parallelStream().filter(assemble -> assemble.getStatus()==1).forEach(assemble -> {
            AssembleSimpleVo assembleSimpleVo=new AssembleSimpleVo();
            BeanUtils.copyProperties(assemble,assembleSimpleVo);
            assembleSimpleVos.add(assembleSimpleVo);
        });
        return assembleSimpleVos;
    }

    @Transactional
    @Override
    public AssembleDetailVo createNewAssemble(Integer skuId,Integer count,Integer shippingId) {
        try {
            checkSpellArguments(skuId,count);
            User user= AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            Assemble assemble=new Assemble();
            assemble.setDeadline(new Date(System.currentTimeMillis()+1000*3600*48));//有效期两天
            assemble.setProductId(skuId);
            assemble.setSpellNums(1);
            assemble.setStatus(1);
            assemble.setTitle(String.join(" ",user.getNickname(),"的拼单"));
            assembleMapper.insert(assemble);
            AssembleItem assembleItem=new AssembleItem();
            assembleItem.setAvatar(user.getAvatar());
            assembleItem.setNickname(user.getNickname());
            assembleItem.setAssembleId(assemble.getId());
            assembleItemMapper.insert(assembleItem);
            AssembleDetailVo assembleDetailVo=new AssembleDetailVo();
            BeanUtils.copyProperties(assemble,assembleDetailVo);
            List<AssembleItem> assembleItemList=Lists.newArrayList();
            assembleDetailVo.setAssembleItemList(assembleItemList);
            assembleDetailVo.getAssembleItemList().add(assembleItem);

            generateOrder(user,skuId,count,assemble,shippingId);

            //发送延迟消息
            rabbitTemplate.convertAndSend(ChengfengConstant.RabbitMQ.SPELL_ORDER_DELAY_EXCHANGE,
                    "spell_order_delay_queue", JsonSerializableUtil.obj2String(assemble), message -> {
                        message.getMessageProperties().setHeader("x-delay",10000);
                        return message;
                    });
            return assembleDetailVo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public AssembleDetailVo getAssembleDetailInfoById(Integer assembleId) {
        Assemble assemble = assembleMapper.selectAssembleDetailInfoById(assembleId);
        if (assemble == null) {
            throw new ParamException("查询的拼单信息不存在");
        }
        if (assemble.getStatus()==0){
            throw new ParamException("当前拼单已经失效,无法获得拼单信息");
        }
        AssembleDetailVo assembleDetailVo=new AssembleDetailVo();
        BeanUtils.copyProperties(assemble,assembleDetailVo);
        assembleDetailVo.setAssembleItemList(Lists.newArrayList());
        assemble.getAssembleItems().stream().forEach(assembleItem -> assembleDetailVo.getAssembleItemList().add(assembleItem));
        return assembleDetailVo;
    }

    @Transactional
    @Override
    public AssembleDetailVo joinExistsAssemble(Integer assembleId,Integer skuId,Integer count,Integer shippingId) {
        //加入已经存在的拼单
        Assemble assemble = assembleMapper.selectByPrimaryKey(assembleId);
        Objects.requireNonNull(assemble,"当前拼单不存在");
        if (assemble.getStatus()==0){
            throw new ParamException("当前拼单已经失效,无法加入当前拼单");
        }
        if (assemble.getStatus()==2){
            throw new ParamException("拼单人数已满,加入拼单失败");
        }
        checkSpellArguments(assemble.getProductId(),count);
        //使用分布式锁
        Config config = new Config();
        config.useSingleServer()
                .setAddress(RedisConstant.Redisson.REDIS_ADDRESS);
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock(RedisConstant.Redisson.LOCK_SPELL_ORDER);
        boolean locked = false;
        try{
            log.info("尝试获取拼单锁");
            locked = lock.tryLock(10,TimeUnit.SECONDS);
            log.info("获取锁的状态:{}",locked);
            if(locked){
                User user=AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
                AssembleItem assembleItem=new AssembleItem();
                assembleItem.setNickname(user.getNickname());
                assembleItem.setAvatar(user.getAvatar());
                assembleItem.setAssembleId(assembleId);
                assembleItemMapper.insert(assembleItem);
//                assemble.getAssembleItems().add(assembleItem);
                assemble.setStatus(2);//拼单完成,开始进入订单生效阶段
                assembleMapper.updateByPrimaryKeySelective(assemble);
                generateOrder(user,skuId,count,assemble,shippingId);

                //更新订单下的所有发货信息,异步操作
                this.rabbitTemplate.convertAndSend(UPDATE_ORDER_EXCHANGE,"",String.valueOf(assembleId));
                rabbitTemplate.convertAndSend(ChengfengConstant.RabbitMQ.UPDATE_ORDER_EXCHANGE,
                        "update_order_queue",String.valueOf(assembleId) , message -> {
                            message.getMessageProperties().setHeader("x-delay",5000);
                            return message;
                        });
                log.info("得到锁并且完成拼单操作");
            }
            AssembleDetailVo assembleDetailVo=new AssembleDetailVo();
            BeanUtils.copyProperties(assemble,assembleDetailVo);
            List<AssembleItem> assembleItemList=Lists.newArrayList();
            assembleDetailVo.setAssembleItemList(assembleItemList);
            assembleDetailVo.getAssembleItemList().addAll( assembleMapper.selectAssembleDetailInfoById(assembleId).getAssembleItems());
            return assembleDetailVo;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            log.info("释放锁");
            if(locked){
                lock.unlock();
            }
        }
        return null;
    }

    @Transactional
    @Override
    public PurchaseOrderVo placeSeparateOrder(Integer skuId, Integer count, Integer shippingId) {
        checkSpellArguments(skuId,count);
        //使用分布式锁
        Config config = new Config();
        config.useSingleServer()
                .setAddress(RedisConstant.Redisson.REDIS_ADDRESS);
        RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock(RedisConstant.Redisson.LOCK_SPELL_ORDER);
        boolean locked = false;
        try{
            User user = AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            log.info("尝试获取下单锁");
            locked = lock.tryLock(10,TimeUnit.SECONDS);
            log.info("获取锁的状态:{}",locked);
            if(locked) {
                    PurchaseProductSku productSku = productSkuMapper.selectAllByPrimaryKey(skuId);
                    Long orderNo=Long.parseLong(stringRedisTemplate.opsForValue().increment(RedisConstant.COUNTER_ORDER).toString());
                    PurchaseOrderItem purchaseOrderItem=new PurchaseOrderItem();
                    purchaseOrderItem.setCurrentUnitPrice(productSku.getPrice());
                    purchaseOrderItem.setOrderNo(orderNo);
                    purchaseOrderItem.setQuantity(count);
                    purchaseOrderItem.setNickname(user.getNickname());
                    purchaseOrderItem.setProductName(String.join(" ",productSku.getPurchaseProduct().getName(),productSku.getAttributeName()));
                    purchaseOrderItem.setProductId(skuId);
                    purchaseOrderItem.setProductImage(MoreObjects.firstNonNull(productSku.getPurchaseProduct().getMainImage(),""));
                    purchaseOrderItem.setTotalPrice(productSku.getPrice().multiply(new BigDecimal(count)));
                    orderItemMapper.insert(purchaseOrderItem);
                    PurchaseOrder purchaseOrder=new PurchaseOrder();
                    purchaseOrder.setCloseTime(null);
                    purchaseOrder.setEndTime(null);
                    purchaseOrder.setSendTime(new Date());
                    purchaseOrder.setOrderNo(orderNo);
                    purchaseOrder.setPayment(productSku.getPrice().multiply(new BigDecimal(count)));
                    purchaseOrder.setPaymentType(0);//非拼单
                    purchaseOrder.setPaymentTime(new Date());
                    purchaseOrder.setShippingId(shippingId);
                    purchaseOrder.setStatus(OrderStatus.UNRECEIVED.getCode());
                    purchaseOrder.setAvatar(user.getAvatar());
                    purchaseOrder.setNickname(user.getNickname());
                    purchaseOrderMapper.insert(purchaseOrder);
                    //更新库存
                    Integer stock = Integer.parseInt(stringRedisTemplate.opsForHash().get(RedisConstant.PRODUCT_STOCKS,
                            RedisConstant.PRODUCT_PREFIX_SKU + skuId).toString());
                    stringRedisTemplate.opsForHash().put(RedisConstant.PRODUCT_STOCKS,
                            RedisConstant.PRODUCT_PREFIX_SKU + skuId,String.valueOf(stock-count));
                    PurchaseOrderVo purchaseOrderVo=new PurchaseOrderVo();
                    PurchaseOrderItemVo orderItemVo=new PurchaseOrderItemVo();
                    BeanUtils.copyProperties(purchaseOrderItem,orderItemVo);
                    BeanUtils.copyProperties(purchaseOrder,purchaseOrderVo);
                    List<PurchaseOrderItemVo> orderItemVos=Lists.newArrayList();
                    purchaseOrderVo.setOrderItems(orderItemVos);
                    purchaseOrderVo.getOrderItems().add(orderItemVo);
                    PurchaseShipping shipping=purchaseShippingMapper.selectByPrimaryKey(shippingId);

                PurchaseCategory category=purchaseCategoryMapper.selectByPrimaryKey(productSku.getPurchaseProduct().getCategoryId());
                PurchaseInfoDto purchaseInfoDto = AssemblyDataUtil.assemblyPurchaseInfo(productSku, count, category,stringRedisTemplate);

                kafkaTemplate.send("topic-demo",purchaseInfoDto);
                ShippingVo shippingVo=new ShippingVo();
                if (shipping != null) {
                    BeanUtils.copyProperties(shipping,shippingVo);
                }
                purchaseOrderVo.setShippingVo(shippingVo);
                    return purchaseOrderVo;
                }
        }catch (Exception e) {
            log.error("下单失败");
        }finally {
            log.info("释放锁");
            if(locked){
                lock.unlock();
            }
        }
        return null;
    }
        private void checkSpellArguments(Integer skuId,Integer count){
        Boolean checkProduct = stringRedisTemplate.opsForHash().hasKey(RedisConstant.PRODUCT_STOCKS,
                RedisConstant.PRODUCT_PREFIX_SKU + skuId);
        Preconditions.checkArgument(checkProduct,"商品不存在");
        Integer stock = Integer.parseInt(stringRedisTemplate.opsForHash().get(RedisConstant.PRODUCT_STOCKS,
                RedisConstant.PRODUCT_PREFIX_SKU + skuId).toString());
        if (stock<count){
            throw new ParamException("商品库存不足,下单失败");
        }
    }

    private void generateOrder(User user,Integer skuId,Integer count,Assemble assemble,Integer shippingId){
        //生成订单
        PurchaseProductSku productSku = productSkuMapper.selectAllByPrimaryKey(skuId);
        Long orderNo=Long.parseLong(stringRedisTemplate.opsForValue().increment(RedisConstant.COUNTER_ORDER).toString());
        PurchaseOrderItem purchaseOrderItem=new PurchaseOrderItem();
        purchaseOrderItem.setCurrentUnitPrice(productSku.getSpellPrice());
        purchaseOrderItem.setOrderNo(orderNo);
        purchaseOrderItem.setQuantity(count);
        purchaseOrderItem.setNickname(user.getNickname());
        purchaseOrderItem.setProductName(String.join(" ",productSku.getPurchaseProduct().getName(),productSku.getAttributeName()));
        purchaseOrderItem.setProductId(skuId);
        purchaseOrderItem.setProductImage(MoreObjects.firstNonNull(productSku.getPurchaseProduct().getMainImage(),""));
        purchaseOrderItem.setTotalPrice(productSku.getSpellPrice().multiply(new BigDecimal(count)));
        orderItemMapper.insert(purchaseOrderItem);

        PurchaseOrder purchaseOrder=new PurchaseOrder();
        purchaseOrder.setCloseTime(null);
        purchaseOrder.setEndTime(null);
        purchaseOrder.setSendTime(null);
        purchaseOrder.setOrderNo(orderNo);
        purchaseOrder.setPayment(productSku.getSpellPrice().multiply(new BigDecimal(count)));
        purchaseOrder.setPaymentType(assemble.getId());
        purchaseOrder.setPaymentTime(new Date());
        purchaseOrder.setShippingId(shippingId);
        purchaseOrder.setStatus(OrderStatus.PAID.getCode());
        purchaseOrder.setAvatar(user.getAvatar());
        purchaseOrder.setNickname(user.getNickname());
        purchaseOrderMapper.insert(purchaseOrder);
        //更新库存
        Integer stock = Integer.parseInt(stringRedisTemplate.opsForHash().get(RedisConstant.PRODUCT_STOCKS,
                RedisConstant.PRODUCT_PREFIX_SKU + skuId).toString());
        stringRedisTemplate.opsForHash().put(RedisConstant.PRODUCT_STOCKS,
                RedisConstant.PRODUCT_PREFIX_SKU + skuId,String.valueOf(stock-count));

        //记录数据

        PurchaseCategory category=purchaseCategoryMapper.selectByPrimaryKey(productSku.getPurchaseProduct().getCategoryId());
        PurchaseInfoDto purchaseInfoDto = AssemblyDataUtil.assemblyPurchaseInfo(productSku, count, category,stringRedisTemplate);

        kafkaTemplate.send("topic-demo",purchaseInfoDto);


    }
}
