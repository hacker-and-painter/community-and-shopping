package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseEvaluationMapper;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.repository.EvaluationRepository;
import com.beautifulsoup.chengfeng.repository.ProductRepository;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
@Component
public class ElasticSearchReceiver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private PurchaseProductMapper productMapper;

    @Autowired
    private PurchaseEvaluationMapper purchaseEvaluationMapper;

    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_ELASTICSEARCH)
    public void process(String init, Message message, Channel channel) throws IOException {
            try {
                log.info(init + "消息收到" + "初始化数据库内容到elasticsearch");
                List<PurchaseProduct> purchaseProducts = productMapper.selectAllPurchaseProducts();
                purchaseProducts.stream().forEach(purchaseProduct -> {
                    double ratio = purchaseProduct.getGoodEvaluationNums().doubleValue() / purchaseProduct.getEvaluationNums().doubleValue();
                    BigDecimal ratioDecimal = new BigDecimal(ratio);
                    purchaseProduct.setGoodRatio(ratioDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue());
                    log.info(purchaseProduct.getGoodRatio().toString());
                    purchaseProduct.setDetail(new StringBuilder().append(purchaseProduct.getName()).append(purchaseProduct.getSubtitle()).toString());
                    productRepository.save(purchaseProduct);
                });
                List<PurchaseEvaluation> allPurchaseEvaluations = purchaseEvaluationMapper.getAllPurchaseEvaluations();
                allPurchaseEvaluations.parallelStream().forEach(evaluation -> {
                    evaluationRepository.save(evaluation);
                });
            }finally {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }

    }

}
