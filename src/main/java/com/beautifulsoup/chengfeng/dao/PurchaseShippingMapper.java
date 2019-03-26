package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseShipping;

import java.util.List;

public interface PurchaseShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseShipping record);

    int insertSelective(PurchaseShipping record);

    PurchaseShipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseShipping record);

    int updateByPrimaryKey(PurchaseShipping record);

    List<PurchaseShipping> selectAllShippingsByUserId(Integer userId);
}