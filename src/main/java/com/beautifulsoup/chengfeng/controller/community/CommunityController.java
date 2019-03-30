package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CommunityVo;
import com.beautifulsoup.chengfeng.service.CommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({
            @ApiResponse(code = 100, message = "请求参数有误"),
            @ApiResponse(code = 101, message = "未授权"),
            @ApiResponse(code = 103, message = "禁⽌访问"),
            @ApiResponse(code = 104, message = "请求路径不存在"),
            @ApiResponse(code = 200, message = "服务器内部错误"),
            @ApiResponse(code = 400,message = "请求参数不正确")
    })
    @GetMapping(value = "/listall",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<List<CommunityVo>> getAllCommunities(){
        List<CommunityVo> communityVos = communityService.getAllCommunities();
        return ResponseResult.createBySuccess("获取社区列表成功",communityVos);
    }


}
