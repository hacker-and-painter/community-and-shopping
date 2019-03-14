package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date showtime;

    private Integer userId;

    private String description;

}
