package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.WatersuplyBook;

public interface WatersuplyBookMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WatersuplyBook record);

    int insertSelective(WatersuplyBook record);

    WatersuplyBook selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WatersuplyBook record);

    int updateByPrimaryKey(WatersuplyBook record);
}