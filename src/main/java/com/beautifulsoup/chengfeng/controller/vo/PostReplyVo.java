package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostReplyVo implements Serializable {
    private Integer id;

    private String content;

    private String imgUrl;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date replyTime;

    private Integer star;

    private Integer parentId;

    private Integer postId;
}
