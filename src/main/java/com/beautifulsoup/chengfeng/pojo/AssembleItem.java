package com.beautifulsoup.chengfeng.pojo;

public class AssembleItem {
    private Integer id;

    private String nickname;

    private String avatar;

    private Integer assembleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Integer getAssembleId() {
        return assembleId;
    }

    public void setAssembleId(Integer assembleId) {
        this.assembleId = assembleId;
    }
}