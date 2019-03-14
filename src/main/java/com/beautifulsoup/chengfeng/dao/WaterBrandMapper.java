package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.WaterBrand;

public interface WaterBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaterBrand record);

    int insertSelective(WaterBrand record);

    WaterBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaterBrand record);

    int updateByPrimaryKey(WaterBrand record);
}