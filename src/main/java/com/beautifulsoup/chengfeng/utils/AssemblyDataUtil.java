package com.beautifulsoup.chengfeng.utils;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.pojo.PurchaseCategory;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

public class AssemblyDataUtil {
    public static PurchaseInfoDto assemblyPurchaseInfo(PurchaseProductSku productSku,Integer count,PurchaseCategory category,StringRedisTemplate stringRedisTemplate){
        PurchaseInfoDto purchaseInfoDto=new PurchaseInfoDto();
        purchaseInfoDto.setId(productSku.getId());
        purchaseInfoDto.setName(productSku.getPurchaseProduct().getName());
        purchaseInfoDto.setSubtitle(productSku.getPurchaseProduct().getName()+" "+productSku.getAttributeName());
        purchaseInfoDto.setDetail(productSku.getPurchaseProduct().getName()+" "+productSku.getAttributeName());
        purchaseInfoDto.setCategory(category.getName());

        purchaseInfoDto.setGoodRatio(productSku.getPurchaseProduct().getGoodRatio());
        purchaseInfoDto.setMainImage(productSku.getPurchaseProduct().getMainImage());
        purchaseInfoDto.setSubImages(productSku.getPurchaseProduct().getSubImages());
        purchaseInfoDto.setSales(stringRedisTemplate.opsForValue().increment(RedisConstant.SALES_PURCHASEINFO+productSku.getId(),count).intValue());
        purchaseInfoDto.setPrice(productSku.getPrice());
        purchaseInfoDto.setSpellPrice(productSku.getSpellPrice());
        return purchaseInfoDto;
    }
}
