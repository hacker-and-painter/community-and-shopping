package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EvaluationRepository extends ElasticsearchRepository<PurchaseEvaluation,Integer> {
}
