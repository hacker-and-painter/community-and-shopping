package com.beautifulsoup.chengfeng.controller.community;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.CommunityVo;
import com.beautifulsoup.chengfeng.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;


    @GetMapping("/listall")
    @ResponseBody
    public ResponseResult<List<CommunityVo>> getAllCommunities(){
        List<CommunityVo> communityVos = communityService.getAllCommunities();
        return ResponseResult.createBySuccess(communityVos);
    }
}
