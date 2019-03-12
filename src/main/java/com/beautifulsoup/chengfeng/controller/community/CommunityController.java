package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CommunityVo;
import com.beautifulsoup.chengfeng.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api(tags = "/community",description = "社区",protocols = "http")
@Controller
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @ApiOperation(value = "查询社区信息",notes = "列出所有的社区",produces = "application/json;charset=UTF-8",
            response = List.class,httpMethod = "GET")
    @GetMapping(value = "/listall",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<CommunityVo>> getAllCommunities(){
        List<CommunityVo> communityVos = communityService.getAllCommunities();
        return ResponseResult.createBySuccess(communityVos);
    }
}
