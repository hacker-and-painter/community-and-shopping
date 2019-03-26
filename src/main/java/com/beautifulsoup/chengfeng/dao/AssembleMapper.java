package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.Assemble;

public interface AssembleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Assemble record);

    int insertSelective(Assemble record);

    Assemble selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Assemble record);

    int updateByPrimaryKey(Assemble record);
}