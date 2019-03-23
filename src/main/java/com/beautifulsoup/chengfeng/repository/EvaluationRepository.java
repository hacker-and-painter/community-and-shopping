package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EvaluationRepository extends ElasticsearchRepository<PurchaseEvaluation,Integer> {
    List<PurchaseEvaluation> findByTypeAndProductId(Integer type, Integer productId, Pageable pageable);
}
