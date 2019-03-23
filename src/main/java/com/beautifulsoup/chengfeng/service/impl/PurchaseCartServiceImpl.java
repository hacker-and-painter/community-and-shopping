package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseCartVo;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.PurchaseCartService;
import com.beautifulsoup.chengfeng.service.dto.PurchaseCartItemDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.google.common.base.Splitter;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class PurchaseCartServiceImpl implements PurchaseCartService {

    @Autowired
    private PurchaseProductMapper productMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;


    @Override
    public PurchaseCartVo addNewPurchaseProduct(Integer productId, Integer count) {
        try {
            Boolean checkProduct = stringRedisTemplate.opsForHash().hasKey(RedisConstant.PRODUCT_STOCKS, RedisConstant.PRODUCT_PREFIX + productId);
            if (!checkProduct){
                throw new ParamException("商品不存在");
            }
            Integer stock = Integer.parseInt(stringRedisTemplate.opsForHash().get(RedisConstant.PRODUCT_STOCKS, RedisConstant.PRODUCT_PREFIX + productId).toString());
            if (stock<count){
                throw new ParamException("商品库存不足");
            }
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            boolean hasProduct = redisTemplate.opsForHash().hasKey(RedisConstant.CART_BELONG_TO + user.getId(), RedisConstant.CART_PRODUCT_PREFIX + productId).booleanValue();

            if (hasProduct){
                PurchaseCartItemDto cartItemDto = (PurchaseCartItemDto) redisTemplate.opsForHash().get(RedisConstant.CART_BELONG_TO + user.getId(), RedisConstant.CART_PRODUCT_PREFIX + productId);
                cartItemDto.setCount(count+cartItemDto.getCount());
                cartItemDto.setTotalPrice(cartItemDto.getPrice()*cartItemDto.getCount());
                redisTemplate.opsForHash().put(RedisConstant.CART_BELONG_TO+user.getId(),RedisConstant.CART_PRODUCT_PREFIX+productId,cartItemDto);
            }else{
                PurchaseProduct purchaseProduct = productMapper.selectByPrimaryKey(productId);
                PurchaseCartItemDto cartItemDto=new PurchaseCartItemDto();
                cartItemDto.setProductId(productId);
                cartItemDto.setName(purchaseProduct.getName());
                cartItemDto.setCount(count);
                cartItemDto.setSubtitle(purchaseProduct.getSubtitle());
                cartItemDto.setIsChecked(1);
                List<String> subImages = Splitter.on(",").splitToList(purchaseProduct.getSubImages());
                if (!CollectionUtils.isEmpty(subImages)){
                    cartItemDto.setSubImage(purchaseProduct.getSubImages());
                }
//                cartItemDto.setPrice(purchaseProduct.getPrice().doubleValue());
                cartItemDto.setTotalPrice(cartItemDto.getPrice()*cartItemDto.getCount());
                redisTemplate.opsForHash().put(RedisConstant.CART_BELONG_TO+user.getId(),RedisConstant.CART_PRODUCT_PREFIX+productId,cartItemDto);
            }
            PurchaseCartVo purchaseCartVo=new PurchaseCartVo();
            purchaseCartVo.setNickname(user.getNickname());
            redisTemplate.opsForHash().keys(RedisConstant.CART_BELONG_TO+user.getId()).stream().forEach(key->{
                PurchaseCartItemDto cartItem = (PurchaseCartItemDto) redisTemplate.opsForHash().get(RedisConstant.CART_BELONG_TO + user.getId(), key);
                purchaseCartVo.getCartItems().add(cartItem);
            });
            return purchaseCartVo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return null;
    }



    
}
