package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EvaluationRepository extends ElasticsearchRepository<PurchaseEvaluation,Integer> {
    List<PurchaseEvaluation> findByTypeAndProductIdOrderByEvaluationTimeDesc(Integer type,Integer productId,Pageable pageable);
    List<PurchaseEvaluation> findByProductId(Integer productId, Pageable pageable);
}
