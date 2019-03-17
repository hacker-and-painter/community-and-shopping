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
public class PurchaseProductVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private Integer sales;

    private BigDecimal price;

    private Integer status;

    private BigDecimal spellPrice;

    private Integer evaluationNums;

    private Integer goodEvaluationNums;

    private String mainImage;

    private String subImages;

    private String detail;

    private String category;
}
