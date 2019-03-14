package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.ProperNotice;

public interface ProperNoticeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProperNotice record);

    int insertSelective(ProperNotice record);

    ProperNotice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProperNotice record);

    int updateByPrimaryKeyWithBLOBs(ProperNotice record);

    int updateByPrimaryKey(ProperNotice record);
}