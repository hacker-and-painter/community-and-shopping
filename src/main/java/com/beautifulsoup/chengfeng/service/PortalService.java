package com.beautifulsoup.chengfeng.service;

import com.beautifulsoup.chengfeng.controller.vo.*;
import com.beautifulsoup.chengfeng.pojo.BannerImage;
import com.beautifulsoup.chengfeng.service.dto.RepairBookDto;
import com.beautifulsoup.chengfeng.service.dto.SecretaryBookDto;
import com.beautifulsoup.chengfeng.service.dto.WatersuplyDto;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface PortalService {
    List<CommunityNoticeVo> findAllCommunityNoticeVos(Integer pageNum,Integer pageSize);
    List<ProperNoticeVo> findAllProperNoticeVos(Integer pageNum, Integer pageSize);
    List<CommunityNoticeVo> findLatestCommunityNoticeVos(Integer limit);
    List<ProperNoticeVo> findLatestProperNoticeVos(Integer limit);
    String submitRepairInfo(RepairBookDto repairBookDto, BindingResult bindingResult);
    String searchSecretary(SecretaryBookDto bookDto, BindingResult bindingResult);
    List<WaterBrandVo> findAllWaterBrands();
    WaterBookVo bookWaterSuply(WatersuplyDto watersuplyDto,BindingResult bindingResult);
    List<BannerImage> findCarousalImages();
    PortalVo getAllInformation();
}
