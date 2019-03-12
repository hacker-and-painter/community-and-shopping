package com.beautifulsoup.chengfeng.pattern;

import com.beautifulsoup.chengfeng.ChengfengApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PatternTest  {


    @Test
    public void patternTest(){
        String rules="^[1](([3][0-9])|([4][5,7,9])|([5][4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$";
        Pattern pattern = Pattern.compile(rules);
        Matcher matcher = pattern.matcher("15910062234");
        if (matcher.matches()){
            log.info("手机号校验成功");
        }else{
            log.error("手机号校验失败");
        }
    }
}
