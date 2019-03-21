package com.beautifulsoup.chengfeng.controller.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoteOptionVo {
    private Integer id;

    private String content;

    private Integer voteId;

    private Integer voteNums;
}
