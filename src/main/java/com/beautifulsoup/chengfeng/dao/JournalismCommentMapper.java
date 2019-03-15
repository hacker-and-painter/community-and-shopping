package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.JournalismComment;

public interface JournalismCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JournalismComment record);

    int insertSelective(JournalismComment record);

    JournalismComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JournalismComment record);

    int updateByPrimaryKey(JournalismComment record);
}