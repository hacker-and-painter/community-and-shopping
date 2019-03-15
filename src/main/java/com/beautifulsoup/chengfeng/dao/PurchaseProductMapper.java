package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;

public interface PurchaseProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseProduct record);

    int insertSelective(PurchaseProduct record);

    PurchaseProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseProduct record);

    int updateByPrimaryKey(PurchaseProduct record);
}