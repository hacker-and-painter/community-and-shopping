package com.beautifulsoup.chengfeng.pojo;

import java.io.Serializable;
import java.util.Date;

public class PostVote implements Serializable {
    private Integer id;

    private String title;

    private String description;

    private Integer voteTotal;

    private Integer choice;

    private Integer userId;

    private Date validityTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getVoteTotal() {
        return voteTotal;
    }

    public void setVoteTotal(Integer voteTotal) {
        this.voteTotal = voteTotal;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getValidityTime() {
        return validityTime;
    }

    public void setValidityTime(Date validityTime) {
        this.validityTime = validityTime;
    }
}