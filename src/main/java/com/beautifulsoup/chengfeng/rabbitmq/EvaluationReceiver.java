package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.repository.ProductRepository;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Component
public class EvaluationReceiver {

    @Autowired
    private PurchaseProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_EVALUATION)
    public void process(String msg, Message message, Channel channel) throws IOException {
        try {

            PurchaseEvaluation evaluation= JsonSerializableUtil.string2Obj(msg,PurchaseEvaluation.class);
            PurchaseProduct purchaseProduct = productMapper.selectByPrimaryKey(evaluation.getProductId());
            if (purchaseProduct != null) {
                purchaseProduct.setEvaluationNums(purchaseProduct.getEvaluationNums() + 1);
                if (evaluation.getType() == 1) {
                    purchaseProduct.setGoodEvaluationNums(purchaseProduct.getGoodEvaluationNums() + 1);
                }
                double ratio = purchaseProduct.getGoodEvaluationNums().doubleValue()/ purchaseProduct.getEvaluationNums().doubleValue();
//                BigDecimal divide = new BigDecimal(purchaseProduct.getGoodEvaluationNums()).divide(new BigDecimal(purchaseProduct.getEvaluationNums()));
                BigDecimal ratioDecimal = new BigDecimal(Double.toString(ratio));
                purchaseProduct.setGoodRatio(ratioDecimal.setScale(2,RoundingMode.HALF_UP).doubleValue());
                productMapper.updateByPrimaryKey(purchaseProduct);
                Optional<PurchaseProduct> optional = productRepository.findById(evaluation.getProductId());
                if (optional.isPresent()) {
                    PurchaseProduct purchaseProduct1 = optional.get();
                    purchaseProduct1.setGoodRatio(ratioDecimal.setScale(2,RoundingMode.HALF_UP).doubleValue());
                    purchaseProduct1.setGoodEvaluationNums(purchaseProduct.getGoodEvaluationNums());
                    purchaseProduct1.setEvaluationNums(purchaseProduct.getEvaluationNums());
                    productRepository.save(purchaseProduct1);
                }
            }
        }finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }

    }

}
