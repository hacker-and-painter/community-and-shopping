package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.service.JournalismService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="社区头条",tags= {"社区头条Controller"},description = "新闻版块",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/headline")
public class JournalismController {

    @Autowired
    private JournalismService journalismService;

    @GetMapping(value = "/top5",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<Journalism>> findUserByNickName(){

        List<Journalism> top5Journalisms= journalismService.getTop5JournalismsOrderByPublishTime();

        return ResponseResult.createBySuccess(top5Journalisms);
    }


}
