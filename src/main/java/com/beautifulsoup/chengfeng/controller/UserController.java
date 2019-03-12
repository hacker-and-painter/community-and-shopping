package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/find",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<UserVo> findUserByNickName(@RequestParam(value = "nickname")String nickname){

        UserVo userVo = userService.findUserByNickname(nickname);

        if(userVo!=null){
            return ResponseResult.createBySuccess(userVo);
        }

        return null;
    }

    @PostMapping
    public ResponseResult login(){
        return null;
    }
}
