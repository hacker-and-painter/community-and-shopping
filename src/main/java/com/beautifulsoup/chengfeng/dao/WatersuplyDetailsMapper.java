package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.WatersuplyDetails;

public interface WatersuplyDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WatersuplyDetails record);

    int insertSelective(WatersuplyDetails record);

    WatersuplyDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WatersuplyDetails record);

    int updateByPrimaryKey(WatersuplyDetails record);
}