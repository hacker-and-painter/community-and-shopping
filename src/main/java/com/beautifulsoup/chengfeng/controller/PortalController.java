package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;
import com.beautifulsoup.chengfeng.controller.vo.ProperNoticeVo;
import com.beautifulsoup.chengfeng.service.PortalService;
import com.beautifulsoup.chengfeng.service.dto.RepairBookDto;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseResult<List<ProperNoticeVo>> getAllProperNotices(
            @RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize){
        List<ProperNoticeVo> properNoticeVos=portalService.findAllProperNoticeVos(pageNum,pageSize);
        return ResponseResult.createBySuccess(properNoticeVos);
    }

    @GetMapping(value = "/notice/community/latest",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<CommunityNoticeVo>> getLatestCommunityNotices(
            @RequestParam(value = "limit",required = false,defaultValue = "3")Integer limit){
        List<CommunityNoticeVo> latestCommunityNoticeVos = portalService.findLatestCommunityNoticeVos(limit);
        return ResponseResult.createBySuccess(latestCommunityNoticeVos);
    }

    @GetMapping(value = "/notice/proper/latest",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<List<ProperNoticeVo>> getLatestProperNotices(
            @RequestParam(value = "limit",required = false,defaultValue = "3")Integer limit){
        List<ProperNoticeVo> latestProperNoticeVos = portalService.findLatestProperNoticeVos(limit);
        return ResponseResult.createBySuccess(latestProperNoticeVos);
    }

    @PostMapping(value = "/book/repair",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseResult<String> submitRepairInfo(@Valid @RequestBody RepairBookDto bookDto, BindingResult bindingResult){
        String result = portalService.submitRepairInfo(bookDto, bindingResult);
        if (!StringUtils.isBlank(result)){
            return ResponseResult.createBySuccess(result);
        }
        return ResponseResult.createByError();
    }

}
