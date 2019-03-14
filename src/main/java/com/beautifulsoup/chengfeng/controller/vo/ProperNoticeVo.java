package com.beautifulsoup.chengfeng.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProperNoticeVo {
    private Integer id;

    private String notice;

    private Date showtime;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    private String description;

}
