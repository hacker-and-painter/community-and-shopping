package com.beautifulsoup.chengfeng.pojo;

import java.util.List;

public class PurchaseCategory {
    private Integer id;

    private Integer parentId;

    private String name;

    private List<PurchaseImage> images;

    private List<PurchaseProduct> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<PurchaseImage> getImages() {
        return images;
    }

    public void setImages(List<PurchaseImage> images) {
        this.images = images;
    }

    public List<PurchaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<PurchaseProduct> products) {
        this.products = products;
    }
}