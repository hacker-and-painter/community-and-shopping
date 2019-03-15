package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseOrderItem;

public interface PurchaseOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrderItem record);

    int insertSelective(PurchaseOrderItem record);

    PurchaseOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseOrderItem record);

    int updateByPrimaryKey(PurchaseOrderItem record);
}