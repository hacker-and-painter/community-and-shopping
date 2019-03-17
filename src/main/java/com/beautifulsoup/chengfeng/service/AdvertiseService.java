package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.PurchaseProductVo;

import java.util.List;

public interface AdvertiseService {
    List<PurchaseProductVo> getAllProductsByInfo(String info);
}
