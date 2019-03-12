package com.beautifulsoup.chengfeng.pojo;

import java.io.Serializable;

public class CryptPassword implements Serializable {
    private Integer id;

    private String cryptPassword;

    private String cryptMode;

    private String cryptSalt;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCryptPassword() {
        return cryptPassword;
    }

    public void setCryptPassword(String cryptPassword) {
        this.cryptPassword = cryptPassword == null ? null : cryptPassword.trim();
    }

    public String getCryptMode() {
        return cryptMode;
    }

    public void setCryptMode(String cryptMode) {
        this.cryptMode = cryptMode == null ? null : cryptMode.trim();
    }

    public String getCryptSalt() {
        return cryptSalt;
    }

    public void setCryptSalt(String cryptSalt) {
        this.cryptSalt = cryptSalt == null ? null : cryptSalt.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}