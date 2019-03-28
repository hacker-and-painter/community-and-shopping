package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.AssembleDetailVo;
import com.beautifulsoup.chengfeng.controller.vo.AssembleSimpleVo;
import com.beautifulsoup.chengfeng.controller.vo.PurchaseOrderVo;


import java.util.List;

public interface PurchaseOrderService {
    List<AssembleSimpleVo> listAllSimpleAssembleLists(Integer productId);
    AssembleDetailVo createNewAssemble(Integer productId,Integer count,Integer shippingId);
    AssembleDetailVo getAssembleDetailInfoById(Integer assembleId);
    AssembleDetailVo joinExistsAssemble(Integer assembleId,Integer skuId,Integer count,Integer shippingId);
    PurchaseOrderVo placeSeparateOrder(Integer skuId, Integer count, Integer shippingId);
}
