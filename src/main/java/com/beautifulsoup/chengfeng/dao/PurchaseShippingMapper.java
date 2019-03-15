package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseShipping;

public interface PurchaseShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseShipping record);

    int insertSelective(PurchaseShipping record);

    PurchaseShipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseShipping record);

    int updateByPrimaryKey(PurchaseShipping record);
}