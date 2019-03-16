package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.JournalismMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.repository.JournalismRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_MONGODB)
public class MongodbReceiver {

    @Autowired
    private JournalismMapper journalismMapper;

    @Autowired
    private JournalismRepository mongoRepository;


    @RabbitHandler
    public void process(String message){
        if (StringUtils.equals(message,ChengfengConstant.RabbitMQ.MESSAGE_MONGODB_INIT)){
            log.info(message+"消息收到"+"初始化数据库内容到mongodb");
            List<Journalism> journalisms = journalismMapper.selectAllJournalisms();
            journalisms.stream().parallel().forEach(journalism -> {
                journalism.setCommentNums(0);
                journalism.setStarNums(0);
                mongoRepository.save(journalism);
            });
        }
    }
}
