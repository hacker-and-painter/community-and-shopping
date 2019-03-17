package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<PurchaseProduct,Integer> {
    List<PurchaseProduct> findBySubtitle(String subtitle);
}
