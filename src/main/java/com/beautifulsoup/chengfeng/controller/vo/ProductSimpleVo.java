package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSimpleVo {
    private Integer id;
    private String name;
    private String subtitle;
    private Double goodRatio;
    private Integer categoryId;
    private String detail;
    private String mainImage;
    private PurchaseProductSku productSku;
}
