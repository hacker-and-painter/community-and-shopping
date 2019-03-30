package com.beautifulsoup.chengfeng.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    private Date evaluationTime;
    private Integer type;
}
