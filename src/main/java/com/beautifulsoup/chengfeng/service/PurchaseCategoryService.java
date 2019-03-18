package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.pojo.PurchaseCategory;

import java.util.List;

public interface PurchaseCategoryService {
    List<CategoryVo> getParentCategories();
    List<CategoryVo> getChildrenCategories();;
    List<PurchaseCategory> getPurchaseIndexInfo(Integer categoryId,Integer pageNum,Integer pageSize);
}
