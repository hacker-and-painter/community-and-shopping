package com.beautifulsoup.chengfeng.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WatersuplyBook implements Serializable {
    private Integer id;

    private String phone;

    private Date hopeTime;

    private String address;

    private String description;

    private List<WatersuplyDetails> detailsList;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getHopeTime() {
        return hopeTime;
    }

    public void setHopeTime(Date hopeTime) {
        this.hopeTime = hopeTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public List<WatersuplyDetails> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<WatersuplyDetails> detailsList) {
        this.detailsList = detailsList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}