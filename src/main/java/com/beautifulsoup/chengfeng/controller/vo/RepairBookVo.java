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
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepairBookVo {
    private Integer id;

    private String telephone;

    private String address;

    private String description;

    private String nickname;

    private String avatar;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date hopeTime;
}
