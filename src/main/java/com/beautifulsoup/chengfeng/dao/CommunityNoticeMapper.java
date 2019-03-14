package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.CommunityNotice;

public interface CommunityNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityNotice record);

    int insertSelective(CommunityNotice record);

    CommunityNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityNotice record);

    int updateByPrimaryKeyWithBLOBs(CommunityNotice record);

    int updateByPrimaryKey(CommunityNotice record);
}