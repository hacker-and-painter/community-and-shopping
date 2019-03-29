package com.beautifulsoup.chengfeng.service.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "purchaseinfo", type = "docs", shards = 1, replicas = 0)
public class PurchaseInfoDto implements Serializable {
    @Id
    private Integer id;

    @Field(type = FieldType.Keyword, index = false)
    private String category;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String subtitle;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String detail;

    @Field(type = FieldType.Keyword, index = false)
    private String mainImage;

    @Field(type = FieldType.Keyword, index = false)
    private String subImages;

    private Double goodRatio;

    private Integer sales;

    private BigDecimal price;

    private BigDecimal spellPrice;
}
