package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PurchaseInfoRepository extends ElasticsearchRepository<PurchaseInfoDto,Integer> {
    PurchaseInfoDto findBySubtitle(String subtitle);
    PurchaseInfoDto findByDetail(String subtitle);
}
