package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.ErrorConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.dao.CryptPasswordMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.CryptPassword;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.UserService;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.beautifulsoup.chengfeng.utils.FastDfsClientUtil.saveFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CryptPasswordMapper cryptPasswordMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Transactional
    @Override
    public UserVo registryUserInfo(UserDto userDto, MultipartFile[] files) {

        boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, userDto.getNickname()).booleanValue();
        if (checkNickname){
            throw new ParamException("用户已存在,注册失败");
        }

        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user.setSignUp(new Date());
        user.setAvatar(uploadFiles(files));
        int result = userMapper.insertSelective(user);
        CryptPassword cryptPassword=new CryptPassword();
        cryptPassword.setUserId(user.getId());
        cryptPassword.setCryptPassword(userDto.getPassword());
        cryptPasswordMapper.insertSelective(cryptPassword);
        UserVo userVo=new UserVo();
        userVo.setId(user.getId());
        userVo.setNickname(user.getNickname());
        userVo.setSignUp(user.getSignUp());
        return userVo;
    }

    @Override
    public UserVo updateUserInfo(UserDto userDto, MultipartFile[] files) {
        boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, userDto.getNickname()).booleanValue();
        if (!checkNickname){
            throw new ParamException("用户不存在,用户信息更新失败");
        }



        return null;
    }

    @Override
    public UserVo resetPassword(String nickname, String rawPassword, String newPassword, String phone, String email) {
        return null;
    }


    @Override
    @Cacheable(value = "user",key = "#nickname")
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

    private String uploadFiles(MultipartFile[] files){
        StringBuffer buffer=new StringBuffer();
        if (files!=null&&files.length>0){
            for (int i=0;i<files.length;i++){
                if(files[i].isEmpty()){
                    throw new MultipartException(ErrorConstant.UPLOAD_EMPTY_ERROR);
                }
                try {
                    String path = saveFile(files[i]);
                    if (i!=files.length-1){
                        buffer.append(path).append(",");
                    }else{
                        buffer.append(path);
                    }
                } catch (IOException e) {
                    throw new MultipartException(ErrorConstant.UPLOAD_FAILURE);
                }
            }
        }
        return buffer.toString();
    }
}
