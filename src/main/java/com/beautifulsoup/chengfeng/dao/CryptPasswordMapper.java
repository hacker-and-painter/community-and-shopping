package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.CryptPassword;

public interface CryptPasswordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CryptPassword record);

    int insertSelective(CryptPassword record);

    CryptPassword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CryptPassword record);

    int updateByPrimaryKey(CryptPassword record);
}