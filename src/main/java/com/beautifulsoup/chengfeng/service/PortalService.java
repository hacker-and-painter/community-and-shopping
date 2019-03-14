package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;
import com.beautifulsoup.chengfeng.controller.vo.ProperNoticeVo;

import java.util.List;

public interface PortalService {
    List<CommunityNoticeVo> findAllCommunityNoticeVos(Integer pageNum,Integer pageSize);
    List<ProperNoticeVo> findAllProperNoticeVos(Integer pageNum, Integer pageSize);
    List<CommunityNoticeVo> findLatestCommunityNoticeVos(Integer limit);
    List<ProperNoticeVo> findLatestProperNoticeVos(Integer limit);
}
