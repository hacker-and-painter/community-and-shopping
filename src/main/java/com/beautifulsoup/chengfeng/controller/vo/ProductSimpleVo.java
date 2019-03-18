package com.beautifulsoup.chengfeng.controller.vo;

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
    private String name;
    private String mainImage;
    private Double goodRatio;
    private Integer sales;
    private BigDecimal price;
    private BigDecimal spellPrice;
}
