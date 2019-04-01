package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.PosterVo;
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
import com.beautifulsoup.chengfeng.utils.MailSenderUtil;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static com.beautifulsoup.chengfeng.constant.RedisConstant.EMAIL_VALIDATE_CODE;
import static com.beautifulsoup.chengfeng.constant.RedisConstant.EMAIL_VALIDATE_CODE_PREFIX;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final Joiner joiner=Joiner.on("").skipNulls();

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

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        cryptPassword.setCryptPassword(passwordEncoder.encode(userDto.getPassword()));
        cryptPasswordMapper.insertSelective(cryptPassword);

        stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS, user.getNickname(), JsonSerializableUtil.obj2String(user));
        redisTemplate.opsForHash().put(RedisConstant.USERS,user.getNickname(),user);
        UserVo userVo1=new UserVo();

        BeanUtils.copyProperties(user,userVo1);
//        userVo1.setIdcard(null);
//        userVo1.setGender(null);
//        userVo1.setAvatar(user.getAvatar());
        PosterVo posterVo=new PosterVo();

        posterVo.setUserVo(userVo1);
        posterVo.setPosts(0);
        posterVo.setReplys(0);
        posterVo.setCollections(0);
        posterVo.setFollowerNums(0);
        posterVo.setFollowingNums(0);
        redisTemplate.opsForHash().put(RedisConstant.POSTERS_INFO,posterVo.getUserVo().getNickname(),posterVo);//保存用户在社区的状态信息

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

            UserVo vo=new UserVo();
            BeanUtils.copyProperties(user,userVo);

            stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS, user.getNickname(), JsonSerializableUtil.obj2String(user));
            redisTemplate.opsForHash().put(RedisConstant.USERS,user.getNickname(),user);
            PosterVo posterVo= (PosterVo) redisTemplate.opsForHash().get(RedisConstant.POSTERS_INFO,rawNickname);
            posterVo.setUserVo(userVo);
            redisTemplate.opsForHash().put(RedisConstant.POSTERS_INFO,posterVo.getUserVo().getNickname(),posterVo);//更新用户在社区的状态信息

            SecurityContextHolder.getContext().setAuthentication(null);
            userInfoService.deleteUserLoginInfo(rawNickname);



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

    @Override
    public void sendEmail(String nickname,String email) {
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            if (!StringUtils.equals(user.getNickname(),nickname)){
                throw new ParamException("当前用户昵称错误");
            }
            if (StringUtils.isBlank(user.getEmail())){
                throw new ParamException("您还未绑定邮箱,请通过更新用户信息修改您的邮箱");
            }
            if (!StringUtils.equals(email,user.getEmail())){
                throw new ParamException("当前邮箱和您绑定过的邮箱地址不一致");
            }
            ImmutableList<Integer> immutableList = ImmutableList.of(RandomUtils.nextInt(1,10)
                    ,RandomUtils.nextInt(1,10),RandomUtils.nextInt(1,10),RandomUtils.nextInt(1,10),RandomUtils.nextInt(1,10),
                    RandomUtils.nextInt(1,10));
            String validateCode = immutableList.parallelStream().map(String::valueOf).collect(Collectors.joining(""));
            stringRedisTemplate.opsForValue().set(EMAIL_VALIDATE_CODE_PREFIX+nickname,validateCode);
            stringRedisTemplate.expire(EMAIL_VALIDATE_CODE_PREFIX+nickname,10, TimeUnit.MINUTES);
            mailSenderUtil.sendSimpleMail(email,"【城风网验证码】","亲，感谢您选择城风社区平台。您的本次验证码为:"+validateCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


    @Transactional
    @Override
    public String resetPassword(String nickname, String rawPassword, String newPassword, String validateCode) {
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

                boolean check = stringRedisTemplate.hasKey(EMAIL_VALIDATE_CODE_PREFIX + nickname).booleanValue();
                if (!check){
                    throw new ParamException("验证码已失效,重置密码失败");
                }
                String code = stringRedisTemplate.opsForValue().get(EMAIL_VALIDATE_CODE_PREFIX + nickname);
                if (!StringUtils.equals(code,validateCode)){
                    throw new ParamException("验证码不正确,重置密码失败");
                }

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
    public PosterVo findUserByNickname() {
        try {
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            Boolean check = stringRedisTemplate.opsForHash().hasKey(RedisConstant.USERINFOS, user.getNickname());
            if (check){
                PosterVo posterVo= (PosterVo) redisTemplate.opsForHash().get(RedisConstant.POSTERS_INFO,user.getNickname());//保存用户在社区的状态信息
                return posterVo;
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
