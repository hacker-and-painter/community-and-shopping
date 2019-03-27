package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.AssembleMapper;
import com.beautifulsoup.chengfeng.dao.PurchaseOrderMapper;
import com.beautifulsoup.chengfeng.enums.OrderStatus;
import com.beautifulsoup.chengfeng.pojo.PurchaseOrder;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class UpdateOrderReceiver {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_UPDATE_ORDER)
    public void process(String msg, Message message, Channel channel){
        if (!StringUtils.isBlank(msg)){
            try{
                log.info("更新订单消息:"+msg);
                List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.selectByAssembleId(Integer.valueOf(msg));
                purchaseOrders.stream().forEach(purchaseOrder -> {
                    purchaseOrder.setSendTime(new Date());
                    purchaseOrder.setStatus(OrderStatus.UNRECEIVED.getCode());
                    purchaseOrderMapper.updateByPrimaryKey(purchaseOrder);
                });
            }catch (Exception e){
                log.error(e.getMessage());
            }finally {
                try {
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
