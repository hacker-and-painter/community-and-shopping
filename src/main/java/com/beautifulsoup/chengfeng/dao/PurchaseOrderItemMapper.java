package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseOrderItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseOrderItem record);

    int insertSelective(PurchaseOrderItem record);

    PurchaseOrderItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseOrderItem record);

    int updateByPrimaryKey(PurchaseOrderItem record);

    List<PurchaseOrderItem> selectByIdAndNickname(@Param("skuId")Integer skuId, @Param("nickname")String nickname);
}