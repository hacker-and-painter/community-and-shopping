package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;
import com.beautifulsoup.chengfeng.controller.vo.ProperNoticeVo;
import com.beautifulsoup.chengfeng.service.PortalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(value="系统首页",tags= {"系统首页Controller"},description = "系统首页",protocols = "http")
@Controller
@RequestMapping("/portal")
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping(value = "/notice/community/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CommunityNoticeVo>> getAllCommunityNotices(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize){
        List<CommunityNoticeVo> communityNoticeVos=portalService.findAllCommunityNoticeVos(pageNum,pageSize);
        return ResponseResult.createBySuccess(communityNoticeVos);
    }

    @GetMapping(value = "/notice/proper/all",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ProperNoticeVo>> getAllProperNotices(){
        return null;
    }

    @GetMapping(value = "/notice/community/latest",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CommunityNoticeVo>> getLatestCommunityNotices(){
        return null;
    }

    @GetMapping(value = "/notice/proper/latest",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ProperNoticeVo>> getLatestProperNotices(){
        return null;
    }


}
