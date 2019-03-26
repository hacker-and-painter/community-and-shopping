package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.PurchaseCartVo;

public interface PurchaseCartService {
    PurchaseCartVo addNewPurchaseProduct(Integer skuId,Integer count);

    PurchaseCartVo listAllCartProducts();
}
