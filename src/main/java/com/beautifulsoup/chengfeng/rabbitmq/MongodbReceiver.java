package com.beautifulsoup.chengfeng.rabbitmq;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.dao.JournalismMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.repository.JournalismRepository;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@Slf4j
@Component

public class MongodbReceiver {

    @Autowired
    private JournalismMapper journalismMapper;

    @Autowired
    private JournalismRepository mongoRepository;



    @RabbitListener(queues = ChengfengConstant.RabbitMQ.QUEUE_NAME_MONGODB)
    public void process(String init,Message message, Channel channel){
        try{
            log.info(init+"消息收到"+"初始化数据库内容到mongodb");
            List<Journalism> journalisms = journalismMapper.selectAllJournalisms();
            journalisms.stream().parallel().forEach(journalism -> {
                journalism.setCommentNums(0);
                Random random=new Random();

                journalism.setStarNums(random.nextInt(300));
                mongoRepository.save(journalism);
            });
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }catch (IOException e){
            //做其他的补偿处理
            log.warn(e.getMessage());
        }
    }
}
