package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.EvaluationVo;
import com.beautifulsoup.chengfeng.dao.*;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import com.beautifulsoup.chengfeng.pojo.PurchaseOrderItem;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.repository.EvaluationRepository;
import com.beautifulsoup.chengfeng.service.PurchaseEvaluationService;
import com.beautifulsoup.chengfeng.service.dto.EvaluationDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static com.beautifulsoup.chengfeng.constant.ChengfengConstant.RabbitMQ.EVALUATION_EXCHANGE;

@Service
public class PurchaseEvaluationServiceImpl implements PurchaseEvaluationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private PurchaseEvaluationMapper evaluationMapper;

    @Autowired
    private PurchaseProductSkuMapper productSkuMapper;

    @Autowired
    private PurchaseOrderItemMapper orderItemMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    @Override
    public EvaluationVo publishNewEvaluation(EvaluationDto evaluationDto, BindingResult result) {
        ParamValidatorUtil.validateBindingResult(result);

        try {
            User user= AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            PurchaseProductSku productSku=productSkuMapper.selectAllByPrimaryKey(evaluationDto.getSkuId());
            if (null==productSku){
                throw new ParamException("商品不存在,评价发表失败");
            }
            List<PurchaseOrderItem> orderItems = orderItemMapper.selectByIdAndNickname(evaluationDto.getSkuId(), user.getNickname());
            if (CollectionUtils.isEmpty(orderItems)){
                throw new ParamException("当前商品订单不存在,评价发表失败");
            }
            PurchaseEvaluation purchaseEvaluation=new PurchaseEvaluation();
            BeanUtils.copyProperties(evaluationDto,purchaseEvaluation);
            purchaseEvaluation.setNickname(user.getNickname());
            purchaseEvaluation.setAvatar(user.getAvatar());
            purchaseEvaluation.setProductId(productSku.getPurchaseProduct().getId());
            purchaseEvaluation.setAttributeName(productSku.getAttributeName());
            purchaseEvaluation.setEvaluationTime(new Date());
            evaluationMapper.insert(purchaseEvaluation);
            evaluationRepository.save(purchaseEvaluation);
            //异步更新商品的评价信息
            rabbitTemplate.convertAndSend(EVALUATION_EXCHANGE,"", JsonSerializableUtil.obj2String(purchaseEvaluation));

            EvaluationVo evaluationVo=new EvaluationVo();
            BeanUtils.copyProperties(purchaseEvaluation,evaluationVo);
            return evaluationVo;
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
    public List<EvaluationVo> getAllEvaluations(Integer productId,Integer pageNum,Integer pageSize) {
        List<EvaluationVo> evaluationVos=Lists.newArrayList();
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"evaluationTime");
        Sort sort=Sort.by(Lists.newArrayList(order));
        evaluationRepository.findByProductId(productId, PageRequest.of(pageNum-1,pageSize,sort)).forEach(purchaseEvaluation -> {
            EvaluationVo evaluationVo=new EvaluationVo();
            BeanUtils.copyProperties(purchaseEvaluation,evaluationVo);
            evaluationVos.add(evaluationVo);
        });
        return evaluationVos;
    }

    @Override
    public List<EvaluationVo> getGoodEvaluations(Integer productId,Integer pageNum,Integer pageSize) {
        List<EvaluationVo> evaluationVos=Lists.newArrayList();
        evaluationRepository.findByTypeAndProductIdOrderByEvaluationTimeDesc(1,productId,PageRequest.of(pageNum-1,pageSize)).forEach(purchaseEvaluation -> {
            EvaluationVo evaluationVo=new EvaluationVo();
            BeanUtils.copyProperties(purchaseEvaluation,evaluationVo);
            evaluationVos.add(evaluationVo);
        });
        return evaluationVos;
    }
}
