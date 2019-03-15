package com.beautifulsoup.chengfeng.pojo;

import java.math.BigDecimal;

public class PurchaseProduct {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private Integer stock;

    private Integer sales;

    private BigDecimal price;

    private Integer status;

    private BigDecimal spellPrice;

    private Integer evaluationNums;

    private String mainImage;

    private String subImages;

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
}