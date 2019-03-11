package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class AMQPTemplateTest extends ChengfengApplicationTests {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void sendChengfengTopicMessage(){
        String topic="topic.chengfeng";
        String message="发送chengfeng消息";
        this.rabbitTemplate.convertAndSend(ChengfengConstant.RabbitMQ.TOPIC_EXCHANGE,topic,message);
    }

    @Test
    public void sendOrderTopicMessage(){
        String topic="topic.order";
        String message="发送Order消息";
        this.rabbitTemplate.convertAndSend(ChengfengConstant.RabbitMQ.TOPIC_EXCHANGE,topic,message);
    }
}
