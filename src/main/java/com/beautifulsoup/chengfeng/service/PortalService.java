package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;

import java.util.List;

public interface PortalService {
    List<CommunityNoticeVo> findAllCommunityNoticeVos(Integer pageNum,Integer pageSize);
}
