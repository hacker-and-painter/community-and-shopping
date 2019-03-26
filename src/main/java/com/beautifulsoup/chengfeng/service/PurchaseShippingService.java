package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.ShippingVo;
import com.beautifulsoup.chengfeng.service.dto.ShippingDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PurchaseShippingService {
    ShippingVo createNewShipping(ShippingDto shippingDto, BindingResult result);
    List<ShippingVo> listAllShippings();
    ShippingVo deleteShippingById(Integer shippingId);
}
