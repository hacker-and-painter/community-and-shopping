package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.AssembleItem;
import com.beautifulsoup.chengfeng.serializable.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssembleDetailVo {
    private Integer id;

    private String title;

    private Integer spellNums;

    private Integer productId;

    private Integer status;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date deadline;

    private List<AssembleItem> assembleItemList;
}
