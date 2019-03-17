package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.PurchaseProductVo;

import java.util.List;

public interface ProductService {
    List<PurchaseProductVo> findProductsByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize);
}
