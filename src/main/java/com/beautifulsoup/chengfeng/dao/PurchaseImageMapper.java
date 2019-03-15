package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseImage;

public interface PurchaseImageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseImage record);

    int insertSelective(PurchaseImage record);

    PurchaseImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseImage record);

    int updateByPrimaryKey(PurchaseImage record);
}