package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;

import java.util.List;

public interface CategoryService {
    List<CategoryVo> getParentCategories();
    List<CategoryVo> getChildrenCategories();;
}
