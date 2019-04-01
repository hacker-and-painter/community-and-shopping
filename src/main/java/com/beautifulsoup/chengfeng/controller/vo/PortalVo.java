package com.beautifulsoup.chengfeng.controller.vo;

import com.beautifulsoup.chengfeng.pojo.BannerImage;
import com.beautifulsoup.chengfeng.pojo.Journalism;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PortalVo {
    private List<ProperNoticeVo> properNoticeVos;
    private List<CommunityNoticeVo> communityNoticeVos;
    private List<BannerImage> carousals;
    private List<Journalism> journalisms;
    @JsonProperty(value = "products")
    private List<ProductSimpleVo> simpleVos;
}
