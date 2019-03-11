package com.beautifulsoup.chengfeng.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommunityVo implements Serializable {
    private Integer id;

    private String name;

    private String address;

    private String admin;
}
