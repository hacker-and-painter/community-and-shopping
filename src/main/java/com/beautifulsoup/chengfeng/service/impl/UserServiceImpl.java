package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo findUserByNickname(String nickname) {
        if(StringUtils.isBlank(nickname)){
            throw new ParamException("昵称不能为空");
        }
        User user=userMapper.selectByNickname(nickname);
        if(user!=null){
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(user,userVo);
            return userVo;
        }

        return null;
    }
}
