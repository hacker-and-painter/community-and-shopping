package com.beautifulsoup.chengfeng.constant;

public class ChengfengConstant {

    public interface Validation{
        String PHONE_REGEX="^1[34578]\\d{9}$";
        String NUMBER_REGEX="^[0-9]*[1-9][0-9]*$";
        String EMAIL_REGEX="^\\\\w+@(\\\\w+\\\\.){1,2}\\\\w+$";
    }


    public interface RabbitMQ{
        String QUEUE_NAME_CHENGFENG="chengfeng";
        String TOPIC_EXCHANGE="topicExchange";
        String ORDER_EXCHANGE="order_exchange:";
        String UPDATE_ORDER_EXCHANGE="update_order_exchange:";
        String SPELL_ORDER_DELAY_EXCHANGE="spell_order_delay_exchange";
        String QUEUE_NAME_ORDER="order";
        String QUEUE_NAME_MONGODB="mongodb";
        String QUEUE_NAME_ELASTICSEARCH="elasticsearch";
        String QUEUE_NAME_STOCK="stock";
        String QUEUE_NAME_UPDATE_ORDER="update_order";
        String QUEUE_NAME_SPELL_ORDER="spell_order";

        String MESSAGE_MONGODB_INIT="MONGODB_INIT";
        String MESSAGE_ELASTICSEARCH_INIT="ELASTICSEARCH_INIT";
        String MESSAGE_STOCK_UPDATE="MESSAGE_STOCK_UPDATE";
    }

    public interface Memcached{
        Integer CAS_RETRIES=3;
        Integer USER_EXPIRE=3600;
    }

    public interface File{
        String UPLOAD_EMPTY_ERROR="上传文件不能为空";
        String UPLOAD_FAILURE="文件上传失败";
    }

}
