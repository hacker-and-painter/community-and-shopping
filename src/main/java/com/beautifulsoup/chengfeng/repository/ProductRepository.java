package com.beautifulsoup.chengfeng.repository;

import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<PurchaseProduct,Integer> {
    List<PurchaseProduct> findByDetail(String detail);
    List<PurchaseProduct> findByCategoryId(Integer categoryId, Pageable pageable);
//    List<PurchaseProduct> findBySubtitle(String subtitle,Pageable pageable);
}
