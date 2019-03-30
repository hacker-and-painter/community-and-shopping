package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import org.springframework.data.repository.query.Param;

public interface PurchaseProductSkuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseProductSku record);

    int insertSelective(PurchaseProductSku record);

    PurchaseProductSku selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseProductSku record);

    int updateByPrimaryKey(PurchaseProductSku record);

    PurchaseProductSku selectAllByPrimaryKey(Integer id);


}