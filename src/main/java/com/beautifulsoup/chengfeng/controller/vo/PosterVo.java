package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.PostNews;
import com.beautifulsoup.chengfeng.pojo.PostReply;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 用户的详细信息,包括用户的状态
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PosterVo implements Serializable {
    @JsonProperty(value = "base_info")
    private UserVo userVo;
    private Integer posts;//发帖数
    private Integer replys;//回帖数
    private Integer collections;//帖子收藏数
    private List<PostNewsVo> collectNews;//收藏的帖子
    private List<PostNewsVo> postNewsList;
    private List<PostReplyVo> postReplyList;
    private Integer followerNums;
    private Integer followingNums;
    private List<String> followers;
    private List<String> followings;
}
