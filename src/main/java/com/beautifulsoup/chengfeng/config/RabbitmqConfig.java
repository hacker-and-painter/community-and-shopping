package com.beautifulsoup.chengfeng.config;


import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.prop.RabbitmqProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@Slf4j
@EnableConfigurationProperties(value = {RabbitmqProperties.class})
public class RabbitmqConfig {

    @Autowired
    private RabbitmqProperties rabbitmqProperties;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory(rabbitmqProperties.getAddresses(),rabbitmqProperties.getPort());
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitmqProperties.getVirtualHost());
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }


    @Bean
    public Queue chengfengQueue(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_CHENGFENG,true);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_ORDER,true);
    }

    @Bean
    public Queue orderMongodb(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_MONGODB,true);
    }

    @Bean
    public Queue orderElasticSearch(){
        return new Queue(ChengfengConstant.RabbitMQ.QUEUE_NAME_ELASTICSEARCH,true);
    }

    @Bean
    public TopicExchange chengfengExchange(){
        return new TopicExchange(ChengfengConstant.RabbitMQ.TOPIC_EXCHANGE,true,false);
    }

    @Bean
    public FanoutExchange orderExchange(){return new FanoutExchange(ChengfengConstant.RabbitMQ.ORDER_EXCHANGE,true,false);}

    @Bean
    Binding bindingExchangeChengfeng(Queue chengfengQueue,TopicExchange chengfengExchange){
        return BindingBuilder.bind(chengfengQueue).to(chengfengExchange).with("topic.#");
    }

    @Bean
    Binding bindingExchangeOrder(Queue orderQueue,FanoutExchange orderExchange){
        return BindingBuilder.bind(orderQueue).to(orderExchange);
    }

    @Bean
    Binding bindingExchangeMongodb(Queue orderMongodb,TopicExchange chengfengExchange){
        return BindingBuilder.bind(orderMongodb).to(chengfengExchange).with("topic.mongodb");
    }

    @Bean
    Binding bindingExchangElasticSearch(Queue orderElasticSearch,TopicExchange chengfengExchange){
        return BindingBuilder.bind(orderElasticSearch).to(chengfengExchange).with("topic.elasticsearch");
    }
}
