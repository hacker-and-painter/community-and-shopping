package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.Journalism;

import java.util.List;

public interface JournalismMapper {
    int deleteByPrimaryKey(String id);

    int insert(Journalism record);

    int insertSelective(Journalism record);

    Journalism selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Journalism record);

    int updateByPrimaryKey(Journalism record);

    List<Journalism> selectAllJournalismsById(String id);

    List<Journalism> selectAllJournalisms();
}