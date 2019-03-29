package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PurchaseInfoRepository extends ElasticsearchRepository<PurchaseInfoDto,Integer> {
}
