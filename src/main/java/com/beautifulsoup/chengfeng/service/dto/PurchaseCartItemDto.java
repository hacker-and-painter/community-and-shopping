package com.beautifulsoup.chengfeng.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCartItemDto implements Serializable {
    private Integer productId;
    private String name;
    private String subtitle;
    private String subImage;
    private Long expire;
    private Integer isChecked;//是否选中
    private Double price;
    private Integer count;
    private Double totalPrice;
}
