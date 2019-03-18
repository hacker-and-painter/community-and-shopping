package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.repository.ProductRepository;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_ELASTICSEARCH)
public class ElasticSearchReceiver {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseProductMapper productMapper;

    @RabbitHandler
    public void process(String message){
        if (StringUtils.equals(message,ChengfengConstant.RabbitMQ.MESSAGE_ELASTICSEARCH_INIT)){
            log.info(message+"消息收到"+"初始化数据库内容到elasticsearch");
            List<PurchaseProduct> purchaseProducts = productMapper.selectAllPurchaseProducts();
            purchaseProducts.stream().forEach(purchaseProduct -> {
                purchaseProduct.setGoodRatio(purchaseProduct.getGoodEvaluationNums().doubleValue()/purchaseProduct.getEvaluationNums().doubleValue());
                purchaseProduct.setDetail(new StringBuilder().append(purchaseProduct.getName()).append(purchaseProduct.getSubtitle()).toString());
                productRepository.save(purchaseProduct);
            });
        }
    }

}
