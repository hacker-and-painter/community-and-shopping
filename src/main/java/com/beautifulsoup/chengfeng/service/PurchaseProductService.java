package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.ProductSimpleVo;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseProductVo;

import java.util.List;

public interface PurchaseProductService {
    List<ProductSimpleVo> findSimpleProductsByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize);
    PurchaseProductVo findProductDetailInfoById(Integer productId);
}
