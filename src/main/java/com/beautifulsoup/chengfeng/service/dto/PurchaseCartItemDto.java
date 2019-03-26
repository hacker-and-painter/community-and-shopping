package com.beautifulsoup.chengfeng.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PurchaseCartItemDto implements Serializable {
    private Integer productId;
    private Integer skuId;
    private String name;
    private String subtitle;
    private String subImage;
    private Long expire;
    private Integer isChecked;//是否选中
    private Double price;
    private Integer count;
    private Double totalPrice;
}
