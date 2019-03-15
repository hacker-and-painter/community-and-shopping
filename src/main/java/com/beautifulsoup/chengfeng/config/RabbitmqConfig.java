package com.beautifulsoup.chengfeng.config;


import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;

public class RabbitmqConfig {

    @Bean
    public Queue chengfengQueue(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_CHENGFENG);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_ORDER);
    }

    @Bean
    public Queue orderMongodb(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_MONGODB);
    }

    @Bean
    public TopicExchange chengfengExchange(){
        return new TopicExchange(ChengfengConstant.RabbitMQ.TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingExchangeChengfeng(Queue chengfengQueue,TopicExchange chengfengExchange){
        return BindingBuilder.bind(chengfengQueue).to(chengfengExchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeOrder(Queue orderQueue,TopicExchange chengfengExchange){
        return BindingBuilder.bind(orderQueue).to(chengfengExchange).with("topic.order");
    }

    @Bean
    Binding bindingExchangeMongodb(Queue orderMongodb,TopicExchange chengfengExchange){
        return BindingBuilder.bind(orderMongodb).to(chengfengExchange).with("topic.mongodb");
    }

}
