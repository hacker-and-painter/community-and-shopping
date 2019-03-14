package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.dao.CategoryMapper;
import com.beautifulsoup.chengfeng.pojo.Category;
import com.beautifulsoup.chengfeng.service.CategoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> getParentCategories() {
        List<CategoryVo> categoryVos= Lists.newArrayList();
        List<Category> categories=categoryMapper.selectAllParentCategories();
        categories.stream().parallel().forEach(category -> {
            CategoryVo vo=new CategoryVo();
            BeanUtils.copyProperties(category,vo);
            categoryVos.add(vo);
        });
        return categoryVos;
    }

    @Override
    public List<CategoryVo> getChildrenCategories() {
        List<CategoryVo> categoryVos= Lists.newArrayList();
        List<Category> categories=categoryMapper.selectAllChildrenCategories();
        categories.stream().parallel().forEach(category -> {
            CategoryVo vo=new CategoryVo();
            BeanUtils.copyProperties(category,vo);
            categoryVos.add(vo);
        });
        return categoryVos;
    }
}
