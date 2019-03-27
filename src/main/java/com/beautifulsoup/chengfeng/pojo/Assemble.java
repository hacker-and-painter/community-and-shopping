package com.beautifulsoup.chengfeng.pojo;

import java.util.Date;
import java.util.List;

public class Assemble {
    private Integer id;

    private String title;

    private Integer spellNums;

    private Integer productId;

    private Integer status;

    private Date deadline;

    private List<AssembleItem> assembleItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSpellNums() {
        return spellNums;
    }

    public void setSpellNums(Integer spellNums) {
        this.spellNums = spellNums;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<AssembleItem> getAssembleItems() {
        return assembleItems;
    }

    public void setAssembleItems(List<AssembleItem> assembleItems) {
        this.assembleItems = assembleItems;
    }
}