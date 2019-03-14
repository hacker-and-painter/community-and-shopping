package com.beautifulsoup.chengfeng.constant;

public class ChengfengConstant {

    public interface Validation{
        String PHONE_REGEX="^[1](([3][0-9])|([4][5,7,9])|([5][4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        String NUMBER_REGEX="^[0-9]*[1-9][0-9]*$";
    }


    public interface RabbitMQ{
        String CHENGFENG_QUEUE_NAME="chengfeng";
        String ORDER_QUEUE_NAME="order";
        String TOPIC_EXCHANGE="topicExchange";
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
