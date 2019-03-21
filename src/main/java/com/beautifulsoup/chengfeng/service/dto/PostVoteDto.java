package com.beautifulsoup.chengfeng.service.dto;

import com.beautifulsoup.chengfeng.serializable.Long2DateDeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class PostVoteDto {
    private String title;

    private String description;

    private Integer choice;

    @JsonDeserialize(using = Long2DateDeSerializer.class)
    private Date validityTime;

    private List<VoteOptionDto> optionList;
}
