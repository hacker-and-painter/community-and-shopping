package com.beautifulsoup.chengfeng.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "product", type = "docs", shards = 1, replicas = 0)
public class PurchaseProduct implements Serializable {
    @Id
    private Integer id;

    private Integer categoryId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String subtitle;

    private Integer stock;

    private Integer sales;

    private BigDecimal price;

    private Integer status;

    private BigDecimal spellPrice;

    private Integer evaluationNums;

    private Integer goodEvaluationNums;
    @Field(type = FieldType.Keyword, index = false)
    private String mainImage;
    @Field(type = FieldType.Keyword, index = false)
    private String subImages;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String detail;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getSpellPrice() {
        return spellPrice;
    }

    public void setSpellPrice(BigDecimal spellPrice) {
        this.spellPrice = spellPrice;
    }

    public Integer getEvaluationNums() {
        return evaluationNums;
    }

    public void setEvaluationNums(Integer evaluationNums) {
        this.evaluationNums = evaluationNums;
    }

    public Integer getGoodEvaluationNums() {
        return goodEvaluationNums;
    }

    public void setGoodEvaluationNums(Integer goodEvaluationNums) {
        this.goodEvaluationNums = goodEvaluationNums;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getSubImages() {
        return subImages;
    }

    public void setSubImages(String subImages) {
        this.subImages = subImages == null ? null : subImages.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}