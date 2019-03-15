package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseCategory;

public interface PurchaseCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseCategory record);

    int insertSelective(PurchaseCategory record);

    PurchaseCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseCategory record);

    int updateByPrimaryKey(PurchaseCategory record);
}