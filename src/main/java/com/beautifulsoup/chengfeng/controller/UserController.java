package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.service.UserService;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
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

    @PostMapping(value = "/registry",produces = "application/json;charset=UTF-8",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult registryUserInfo(@Valid @RequestBody UserDto userDto, MultipartFile[] files, BindingResult result){
        ParamValidatorUtil.valiteBindingResult(result);

        return null;
    }

    @GetMapping(value = "/token",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult getUserToken(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName());
        log.info(authentication.getAuthorities().toString());
        log.info(((User)authentication.getPrincipal()).getPassword());
        Map<String,Object> tokenInfo= Maps.newHashMap();
        tokenInfo.put("name",authentication.getName());
        tokenInfo.put("password",((User)authentication.getPrincipal()).getPassword());
        tokenInfo.put("authorities",authentication.getAuthorities());
//        Multimap<String,Object> stringObjectMultimap=Multimaps.new
        return ResponseResult.createBySuccess(tokenInfo);
    }

}
