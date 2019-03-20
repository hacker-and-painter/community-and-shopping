package com.beautifulsoup.chengfeng.controller;

import com.beautifulsoup.chengfeng.common.ResponseResult;
import com.beautifulsoup.chengfeng.controller.vo.PosterVo;
import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.service.UserService;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import io.swagger.annotations.Api;
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

@Api(value="用户信息",tags= {"用户信息Controller"},description = "用户信息",protocols = "http")
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/find",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult<PosterVo> findUserByNickName(){

        PosterVo posterVo = userService.findUserByNickname();

        if(posterVo!=null){
            return ResponseResult.createBySuccess(posterVo);
        }

        return ResponseResult.createByErrorMessage("获取用户信息失败");
    }


    @PostMapping(value = "/registry",produces = "application/json;charset=UTF-8",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult registryUserInfo(@Valid @RequestBody UserDto userDto,
                                           BindingResult result){
        UserVo userVo = userService.registryUserInfo(userDto,result);
        if (null!=userVo){
            return ResponseResult.createBySuccess("用户注册成功",userVo);
        }
        return ResponseResult.createByErrorMessage("用户注册失败");
    }


    @PutMapping(value = "/update",produces = "application/json;charset=UTF-8",consumes = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult updateUserInfo(@Valid @RequestBody UserVo userVo, BindingResult result){

        UserVo resultVo = userService.updateUserInfo(userVo,result);
        if (null!=resultVo){
            return ResponseResult.createBySuccess("用户信息修改成功",userVo);
        }
        return ResponseResult.createByErrorMessage("用户信息修改失败");
    }

    @PostMapping(value = "/password/reset",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseResult resetPassword(@RequestParam("nickname")String nickname,@RequestParam("rawpassword")String rawpassword
            ,@RequestParam("newpassword")String newPassword,@RequestParam("phone")String phone,@RequestParam(value = "email",required = false)String email){
        String result = userService.resetPassword(nickname,rawpassword,newPassword,phone,email);
        return ResponseResult.createBySuccess(result);
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
