package com.beautifulsoup.chengfeng.pojo;

public class WatersuplyDetails {
    private Integer id;

    private Integer suplyId;

    private Integer brandId;

    private String waterNums;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuplyId() {
        return suplyId;
    }

    public void setSuplyId(Integer suplyId) {
        this.suplyId = suplyId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getWaterNums() {
        return waterNums;
    }

    public void setWaterNums(String waterNums) {
        this.waterNums = waterNums == null ? null : waterNums.trim();
    }
}