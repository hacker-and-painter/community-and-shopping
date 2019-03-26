package com.beautifulsoup.chengfeng.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "journalism")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Journalism implements Serializable {
    @Id
    private String id;

    private String title;

    private String author;

    private String description;

    private String images;

    private String videos;

    private Date publishTime;

    private String publishName;

    private Integer communityId;

    private Integer commentNums;

    private Integer starNums;

    private List<JournalismContent> contents;

    private List<JournalismComment> comments;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos == null ? null : videos.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishName() {
        return publishName;
    }

    public void setPublishName(String publishName) {
        this.publishName = publishName == null ? null : publishName.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getCommentNums() {
        return commentNums;
    }

    public void setCommentNums(Integer commentNums) {
        this.commentNums = commentNums;
    }

    public Integer getStarNums() {
        return starNums;
    }

    public void setStarNums(Integer starNums) {
        this.starNums = starNums;
    }

    public List<JournalismContent> getContents() {
        return contents;
    }

    public void setContents(List<JournalismContent> contents) {
        this.contents = contents;
    }

    public List<JournalismComment> getComments() {
        return comments;
    }

    public void setComments(List<JournalismComment> comments) {
        this.comments = comments;
    }
}