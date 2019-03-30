package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.beautifulsoup.chengfeng.service.JournalismService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult<List<Journalism>> getTop5Journalisms(){

        List<Journalism> top5Journalisms= journalismService.getTop5JournalismsOrderByPublishTime();

        return ResponseResult.createBySuccess("获取新闻信息成功",top5Journalisms);
    }


    @GetMapping(value = "/get/{journalismId}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<Journalism> getJournalismById(@PathVariable("journalismId")String journalismId){

        Journalism journalism= journalismService.getJournalismById(journalismId);

        return ResponseResult.createBySuccess("获取新闻信息成功",journalism);
    }

    @GetMapping(value = "/all",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<Journalism>> getJournalismPages(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize){

        List<Journalism> allJournalismsByPage= journalismService.getAllJournalismsByPage(pageNum,pageSize);

        return ResponseResult.createBySuccess("获取新闻信息成功",allJournalismsByPage);
    }

    @GetMapping(value = "/hot",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<Journalism>> getHotJournalisms(
            @RequestParam(value = "num",defaultValue = "3",required = false)Integer num){
        List<Journalism> hotJournalisms = journalismService.getHotJournalisms(num);
        return ResponseResult.createBySuccess("获取新闻信息成功",hotJournalisms);
    }

}
