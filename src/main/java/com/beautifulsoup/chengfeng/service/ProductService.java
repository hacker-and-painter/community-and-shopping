package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.ProductVo;

import java.util.List;

public interface ProductService {
    List<ProductVo> findProductsByCategoryId(Integer categoryId,Integer pageNum,Integer pageSize);
}
