package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    PurchaseOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

    List<PurchaseOrder> selectByNickname(String nickname);

    List<PurchaseOrder> selectByAssembleId(Integer assembleId);
}