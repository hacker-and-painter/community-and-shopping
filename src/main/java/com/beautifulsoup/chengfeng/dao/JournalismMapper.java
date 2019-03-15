package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.Journalism;

public interface JournalismMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Journalism record);

    int insertSelective(Journalism record);

    Journalism selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Journalism record);

    int updateByPrimaryKey(Journalism record);
}