package com.beautifulsoup.chengfeng.pojo;

public class BannerImage {
    private Integer id;

    private String imageUrl;

    private Integer journalismId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public Integer getJournalismId() {
        return journalismId;
    }

    public void setJournalismId(Integer journalismId) {
        this.journalismId = journalismId;
    }
}