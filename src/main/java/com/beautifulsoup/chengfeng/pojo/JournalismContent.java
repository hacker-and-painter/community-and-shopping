package com.beautifulsoup.chengfeng.pojo;

public class JournalismContent {
    private Integer id;

    private String imageUrl;

    private String videoUrl;

    private Integer journalismId;

    private String content;

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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public Integer getJournalismId() {
        return journalismId;
    }

    public void setJournalismId(Integer journalismId) {
        this.journalismId = journalismId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}