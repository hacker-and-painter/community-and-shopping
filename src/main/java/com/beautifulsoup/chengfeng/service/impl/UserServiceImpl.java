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
import com.beautifulsoup.chengfeng.security.UserInfoService;
import com.beautifulsoup.chengfeng.service.UserService;
import com.beautifulsoup.chengfeng.service.dto.UserDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeoutException;



@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CryptPasswordMapper cryptPasswordMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private UserInfoService userInfoService;

    @Transactional
    @Override
    public UserVo registryUserInfo(UserDto userDto, BindingResult result) {
        ParamValidatorUtil.validateBindingResult(result);
        boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, userDto.getNickname()).booleanValue();
        if (checkNickname){
            throw new ParamException("用户已存在,注册失败");
        }

        User user=new User();
        BeanUtils.copyProperties(userDto,user);
        user.setSignUp(new Date());
        userMapper.insertSelective(user);
        CryptPassword cryptPassword=new CryptPassword();
        cryptPassword.setUserId(user.getId());
        cryptPassword.setCryptPassword(userDto.getPassword());
        cryptPasswordMapper.insertSelective(cryptPassword);

        stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS, user.getNickname(), JsonSerializableUtil.obj2String(user));
        redisTemplate.opsForHash().put(RedisConstant.USERS,user.getNickname(),user);

        UserVo userVo=new UserVo();
        userVo.setId(user.getId());
        userVo.setNickname(user.getNickname());
        userVo.setSignUp(user.getSignUp());
        return userVo;
    }

    @Override
    public UserVo updateUserInfo(UserVo userVo, BindingResult result) {
        ParamValidatorUtil.validateBindingResult(result);

        if (!StringUtils.isBlank(userVo.getNickname())){
            boolean checkNickname = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, userVo.getNickname()).booleanValue();
            if (checkNickname){
                throw new ParamException("昵称冲突,用户信息更新失败");
            }
        }
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            String rawNickname=user.getNickname();
            stringRedisTemplate.opsForHash().delete(RedisConstant.USERINFOS, user.getNickname());
            redisTemplate.opsForHash().delete(RedisConstant.USERS,user.getNickname());

            user.setUsername(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getUsername()),user.getUsername()));
            user.setNickname(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getNickname()),user.getNickname()));
            if (userVo.getBirthday()!=null){
                user.setBirthday(userVo.getBirthday());
            }
            if (userVo.getCommunityId()!=null){
                user.setCommunityId(userVo.getCommunityId());
            }
            user.setUpdateTime(new Date());
            user.setAvatar(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getAvatar()),user.getAvatar()));
            user.setEmail(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getEmail()),user.getEmail()));
            user.setPhone(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getPhone()),user.getPhone()));
            user.setMotto(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getMotto()),user.getMotto()));
            user.setIdcard(MoreObjects.firstNonNull(Strings.emptyToNull(userVo.getIdcard()),user.getMotto()));
            userMapper.updateByPrimaryKeySelective(user);


            stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS, user.getNickname(), JsonSerializableUtil.obj2String(user));
            redisTemplate.opsForHash().put(RedisConstant.USERS,user.getNickname(),user);
            SecurityContextHolder.getContext().setAuthentication(null);
            userInfoService.deleteUserLoginInfo(rawNickname);

            UserVo vo=new UserVo();
            vo.setId(user.getId());
            vo.setNickname(user.getNickname());
            vo.setSignUp(user.getSignUp());
            return vo;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
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

        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            if (user.getNickname().equals(nickname)){
                //TODO 基于手机号和邮箱的验证方式

                CryptPassword cryptPassword=checkPassword(nickname,rawPassword);

                cryptPassword.setCryptPassword(newPassword);

                int i = cryptPasswordMapper.updateByPrimaryKeySelective(cryptPassword);

                if (i>0){
                    SecurityContextHolder.getContext().setAuthentication(null);
                    userInfoService.deleteUserLoginInfo(user.getNickname());
                    //修改密码成功
                    return "密码修改成功";
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        throw new UserAuthenticationException("密码修改失败");
    }


    @Override
    public UserVo findUserByNickname() {
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            Boolean check = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, user.getNickname());
            if (check){
                UserVo userVo=new UserVo();
                BeanUtils.copyProperties(user,userVo);
                return userVo;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
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
