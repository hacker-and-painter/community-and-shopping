package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.annotation.NotUsed;
import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductSkuMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.google.common.base.Splitter;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//@Slf4j
//@Component
public class StockReceiver {

    private static final Splitter splitter=Splitter.on(",").trimResults().omitEmptyStrings();
    @Autowired
    private PurchaseProductSkuMapper productSkuMapper;

//    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_STOCK)
    public void process(String msg, Message message, Channel channel){
        try{
//            log.info(msg+"消息收到"+"更新数据库中商品库存信息");
            List<String> info = splitter.splitToList(msg);
            String id = info.get(0);
            String stock=info.get(1);
            String count=info.get(2);
            PurchaseProductSku productSku = productSkuMapper.selectByPrimaryKey(Integer.valueOf(id));
            if (productSku!=null){
                productSku.setStock(Integer.valueOf(stock));
                productSku.setSales(productSku.getSales()+Integer.valueOf(count));
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }

        }finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
