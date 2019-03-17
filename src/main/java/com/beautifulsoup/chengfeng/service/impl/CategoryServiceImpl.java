package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.CategoryVo;
import com.beautifulsoup.chengfeng.dao.PurchaseCategoryMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseCategory;
import com.beautifulsoup.chengfeng.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private PurchaseCategoryMapper purchaseCategoryMapper;

    @Override
    public List<CategoryVo> getParentCategories() {
        List<CategoryVo> categoryVos= Lists.newArrayList();
        List<PurchaseCategory> categories= purchaseCategoryMapper.selectAllParentCategories();
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
        List<PurchaseCategory> categories= purchaseCategoryMapper.selectAllChildrenCategories();
        categories.stream().parallel().forEach(category -> {
            CategoryVo vo=new CategoryVo();
            BeanUtils.copyProperties(category,vo);
            categoryVos.add(vo);
        });
        return categoryVos;
    }

    @Override
    public List<PurchaseCategory> getPurchaseIndexInfo(Integer categoryId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<PurchaseCategory> categorys=purchaseCategoryMapper.selectAllPurchaseInfoByCategoryId(categoryId);
        return categorys;
    }
}
