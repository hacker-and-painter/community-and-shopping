package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
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
    private PurchaseProductMapper productMapper;

    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_ELASTICSEARCH)
    public void process(String init, Message message, Channel channel){
            try{
                log.info(init+"消息收到"+"初始化数据库内容到elasticsearch");
                List<PurchaseProduct> purchaseProducts = productMapper.selectAllPurchaseProducts();
                purchaseProducts.stream().forEach(purchaseProduct -> {
                    double ratio=purchaseProduct.getGoodEvaluationNums().doubleValue()/purchaseProduct.getEvaluationNums().doubleValue();
                    BigDecimal ratioDecimal=new BigDecimal(ratio);
                    purchaseProduct.setGoodRatio(ratioDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue());
                    purchaseProduct.setDetail(new StringBuilder().append(purchaseProduct.getName()).append(purchaseProduct.getSubtitle()).toString());
                    productRepository.save(purchaseProduct);
                });
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            }catch (IOException e){
                //做其他的补偿处理
                log.warn(e.getMessage());
            }
    }

}
