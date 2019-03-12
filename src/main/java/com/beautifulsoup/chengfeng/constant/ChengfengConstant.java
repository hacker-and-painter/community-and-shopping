package com.beautifulsoup.chengfeng.constant;

public class ChengfengConstant {

    public static final String PHONE_REGEX="^[1](([3][0-9])|([4][5,7,9])|([5][4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";

    public interface RabbitMQ{
        String CHENGFENG_QUEUE_NAME="chengfeng";
        String ORDER_QUEUE_NAME="order";
        String TOPIC_EXCHANGE="topicExchange";
    }

    public interface MEMCACHED{
        Integer CAS_RETRIES=3;
    }

}
