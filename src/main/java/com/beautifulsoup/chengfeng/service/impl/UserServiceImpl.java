package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.UserVo;
import com.beautifulsoup.chengfeng.dao.CryptPasswordMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.exception.UserAuthenticationException;
import com.beautifulsoup.chengfeng.pojo.CryptPassword;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.UserService;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
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
        stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS, user.getNickname(), JsonSerializableUtil.obj2String(user));
        UserVo userVo=new UserVo();
        userVo.setId(user.getId());
        userVo.setNickname(user.getNickname());
        userVo.setSignUp(user.getSignUp());
        return userVo;
    }

    @Override
    public UserVo updateUserInfo(UserVo userVo, MultipartFile[] files) {
        if (!StringUtils.isBlank(userVo.getNickname())){
            boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, userVo.getNickname()).booleanValue();
            if (checkNickname){
                throw new ParamException("昵称冲突,用户信息更新失败");
            }
        }
        User user=userMapper.selectByPrimaryKey(userVo.getId());
        if (null!=user){
            String path = uploadFiles(files);
            BeanUtils.copyProperties(userVo,user);
            user.setAvatar(path);
            userMapper.updateByPrimaryKeySelective(user);
            return userVo;
        }

        return null;
    }


    @Transactional
    @Override
    public String resetPassword(String nickname, String rawPassword, String newPassword, String phone, String email) {
        boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, nickname).booleanValue();
        if (!checkNickname){
            throw new ParamException("用户不存在,用户信息更新失败");
        }

        if (newPassword.length()<5||newPassword.length()>20){
            throw new ParamException("密码长度在5到20个字符之间");
        }

        //TODO 基于手机号和邮箱的验证方式

        CryptPassword cryptPassword=checkPassword(nickname,rawPassword);

        cryptPassword.setCryptPassword(newPassword);

        int i = cryptPasswordMapper.updateByPrimaryKeySelective(cryptPassword);

        if (i>0){
            //修改密码成功
            return "密码修改成功";
        }
        throw new UserAuthenticationException("密码修改失败");
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
                    throw new MultipartException(ChengfengConstant.File.UPLOAD_EMPTY_ERROR);
                }
                try {
                    String path = saveFile(files[i]);
                    if (i!=files.length-1){
                        buffer.append(path).append(",");
                    }else{
                        buffer.append(path);
                    }
                } catch (IOException e) {
                    throw new MultipartException(ChengfengConstant.File.UPLOAD_FAILURE);
                }
            }
        }
        return buffer.toString();
    }

    private CryptPassword checkPassword(String nickname,String rawpassword){
        User user=JsonSerializableUtil.string2Obj(stringRedisTemplate.opsForHash().get(RedisConstant.USERINFOS,nickname).toString(),User.class);
        CryptPassword cryptPassword=cryptPasswordMapper.selectByUserId(user.getId());
        if (cryptPassword==null){
            throw  new UserAuthenticationException("原密码不正确");
        }
        //TODO 数据库密码字段加密
        if (StringUtils.equals(cryptPassword.getCryptPassword(),rawpassword)){
            return cryptPassword;
        }
        throw new UserAuthenticationException("用户名或密码不正确");
    }
}
