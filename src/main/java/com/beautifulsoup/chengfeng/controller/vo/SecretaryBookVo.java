package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.WatersuplyDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecretaryBookVo {
    private Integer id;

    private String phone;

    private String email;

    private String description;

    private String nickname;

    private String avatar;
}
