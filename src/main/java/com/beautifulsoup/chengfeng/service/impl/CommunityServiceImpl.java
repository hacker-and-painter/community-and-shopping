package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.CommunityVo;
import com.beautifulsoup.chengfeng.dao.CommunityMapper;
import com.beautifulsoup.chengfeng.pojo.Community;
import com.beautifulsoup.chengfeng.service.CommunityService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public List<CommunityVo> getAllCommunities() {
        List<CommunityVo> communityVos= Lists.newArrayList();
        List<Community> communities=communityMapper.selectAllCommunities();
        if (!CollectionUtils.isEmpty(communities)){
            communities.stream().parallel()
                    .forEach(community -> {
                        CommunityVo vo=new CommunityVo();
                        BeanUtils.copyProperties(community,vo);
                        communityVos.add(vo);
                    });
            return communityVos;
        }
        return null;
    }
}
