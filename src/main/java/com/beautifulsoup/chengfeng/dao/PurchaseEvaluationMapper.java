package com.beautifulsoup.chengfeng.dao;

import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;

import java.util.List;

public interface PurchaseEvaluationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseEvaluation record);

    int insertSelective(PurchaseEvaluation record);

    PurchaseEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurchaseEvaluation record);

    int updateByPrimaryKey(PurchaseEvaluation record);

    List<PurchaseEvaluation> getAllPurchaseEvaluations();
}