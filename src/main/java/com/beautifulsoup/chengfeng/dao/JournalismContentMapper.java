package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.JournalismContent;

public interface JournalismContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JournalismContent record);

    int insertSelective(JournalismContent record);

    JournalismContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JournalismContent record);

    int updateByPrimaryKeyWithBLOBs(JournalismContent record);

    int updateByPrimaryKey(JournalismContent record);
}