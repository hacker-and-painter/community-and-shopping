package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.ProductVo;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PurchaseProductMapper purchaseProductMapper;


    @Override
    public List<ProductVo> findProductsByCategoryId(Integer categoryId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ProductVo> productVos= Lists.newArrayList();
        List<PurchaseProduct> purchaseProducts = purchaseProductMapper.selectByCategoryId(categoryId);//默认按销量的降序排列
        purchaseProducts.stream().forEach(purchaseProduct -> {
            ProductVo productVo=new ProductVo();
            BeanUtils.copyProperties(purchaseProduct,productVo);
            productVos.add(productVo);
        });
        return productVos;
    }
}
