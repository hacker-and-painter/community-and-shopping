package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EvaluationVo {
    private Integer id;
    private String nickname;
    private String avatar;
    private String content;
    private String imageUrl;
    private String attributeName;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date evaluationTime;
    private Integer type;
}
