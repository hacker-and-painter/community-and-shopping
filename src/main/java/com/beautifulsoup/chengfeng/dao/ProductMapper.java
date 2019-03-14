package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.Product;
import com.beautifulsoup.chengfeng.pojo.ProductWithBLOBs;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductWithBLOBs record);

    int insertSelective(ProductWithBLOBs record);

    ProductWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProductWithBLOBs record);

    int updateByPrimaryKey(Product record);
}