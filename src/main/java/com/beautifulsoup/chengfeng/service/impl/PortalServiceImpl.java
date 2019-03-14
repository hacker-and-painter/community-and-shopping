package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;
import com.beautifulsoup.chengfeng.dao.CommunityNoticeMapper;
import com.beautifulsoup.chengfeng.dao.ProperNoticeMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.pojo.Community;
import com.beautifulsoup.chengfeng.pojo.CommunityNotice;
import com.beautifulsoup.chengfeng.service.PortalService;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class PortalServiceImpl implements PortalService {

    @Autowired
    private CommunityNoticeMapper communityNoticeMapper;

    @Autowired
    private ProperNoticeMapper properNoticeMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<CommunityNoticeVo> findAllCommunityNoticeVos(Integer pageNum, Integer pageSize){
        User authenticationInfo = AuthenticationInfoUtil.getAuthenticationInfo();
        List<CommunityNoticeVo> communityNoticeVos= Lists.newArrayList();
        try {
            String userJson = memcachedClient.get(authenticationInfo.getUsername());
            com.beautifulsoup.chengfeng.pojo.User user;
            if (StringUtils.isBlank(userJson)){
                user= JsonSerializableUtil.string2Obj(userJson, com.beautifulsoup.chengfeng.pojo.User.class);

            }else{
                user=userMapper.selectByNickname(authenticationInfo.getUsername());
            }
            PageHelper.startPage(pageNum,pageSize);
            List<CommunityNotice> communityNotices=communityNoticeMapper.selectByCommunityId(user.getCommunityId());
            communityNotices.stream().forEach(communityNotice -> {
                CommunityNoticeVo vo=new CommunityNoticeVo();
                BeanUtils.copyProperties(communityNotice,vo);
                communityNoticeVos.add(vo);
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        return communityNoticeVos;
    }


}
