package com.beautifulsoup.chengfeng.initialize;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class StartupLoadMongodb implements CommandLineRunner {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void run(String... args) throws Exception {
        this.amqpTemplate.convertAndSend(ChengfengConstant.RabbitMQ.TOPIC_EXCHANGE,"topic.mongodb",ChengfengConstant.RabbitMQ.MESSAGE_MONGODB_INIT);
        log.info(ChengfengConstant.RabbitMQ.MESSAGE_MONGODB_INIT+"消息发送");
    }
}
