package com.beautifulsoup.chengfeng.mongodb;

import com.beautifulsoup.chengfeng.dao.JournalismMapper;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.pojo.JournalismComment;
import com.beautifulsoup.chengfeng.pojo.JournalismContent;
import com.beautifulsoup.chengfeng.repository.JournalismRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodbRepositoryTest  {

    @Autowired
    private JournalismRepository mongoRepository;

    @Autowired
    private JournalismMapper journalismMapper;


    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveJournalisms(){
        Journalism journalism=new Journalism();
        journalism.setAuthor("BeautifulSoup");
        journalism.setCommentNums(4);
        journalism.setDescription("搞个大新闻");
        journalism.setPublishTime(new Date());
        journalism.setStarNums(137);
        journalism.setTitle("十三届全国人大第二次会议");
        List<JournalismComment> comments= Lists.newArrayList();
        for (int i=0;i<4;i++){
            JournalismComment comment=new JournalismComment();
            comment.setCommentTime(new Date());
            comment.setAvatar("http://wwww.jpg"+i);
            comment.setContent("评论"+i);
            comment.setTitle("biaoti"+i);
            comment.setUsername("wangshu"+i);
            comments.add(comment);
        }
        List<JournalismContent> contents= Lists.newArrayList();
        for (int i=0;i<4;i++){
            JournalismContent content=new JournalismContent();
            content.setContent("内容"+i);
            content.setImageUrl(UUID.randomUUID().toString());
            contents.add(content);
        }
        journalism.setComments(comments);
        journalism.setContents(contents);
        mongoRepository.save(journalism);
    }

    @Test
    public void getAllJournalismsFromMysql(){
        List<Journalism> journalisms = journalismMapper.selectAllJournalisms();
        journalisms.stream().parallel().forEach(journalism -> {
            journalism.setCommentNums(0);
            journalism.setStarNums(0);
//            mongoRepository.insert(journalism);
            mongoRepository.save(journalism);
        });
    }


    @Test
    public void getAllJournalisms(){
//        List<Journalism> publishTime = mongoRepository.findFirst5(Sort.by(ImmutableList.of(new Sort.Order(Sort.Direction.DESC, "publishTime"))));
//        publishTime.stream().forEach(journalism -> {
//            System.out.println(publishTime);
//        });

    }


}
