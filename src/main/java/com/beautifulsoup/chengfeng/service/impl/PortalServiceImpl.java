package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.constant.ChengfengConstant;
import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.controller.vo.CommunityNoticeVo;
import com.beautifulsoup.chengfeng.controller.vo.ProperNoticeVo;
import com.beautifulsoup.chengfeng.controller.vo.WaterBookVo;
import com.beautifulsoup.chengfeng.controller.vo.WaterBrandVo;
import com.beautifulsoup.chengfeng.dao.*;
import com.beautifulsoup.chengfeng.exception.BaseException;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.*;
import com.beautifulsoup.chengfeng.service.PortalService;
import com.beautifulsoup.chengfeng.service.dto.RepairBookDto;
import com.beautifulsoup.chengfeng.service.dto.SecretaryBookDto;
import com.beautifulsoup.chengfeng.service.dto.WatersuplyDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class PortalServiceImpl implements PortalService {

    @Autowired
    private CommunityNoticeMapper communityNoticeMapper;

    @Autowired
    private ProperNoticeMapper properNoticeMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RepairBookMapper repairBookMapper;

    @Autowired
    private SecretaryBookMapper secretaryBookMapper;

    @Autowired
    private WaterBrandMapper waterBrandMapper;

    @Autowired
    private WatersuplyBookMapper watersuplyBookMapper;

    @Autowired
    private WatersuplyDetailsMapper watersuplyDetailsMapper;

    @Autowired
    private BannerImageMapper bannerImageMapper;


    @Override
    public List<CommunityNoticeVo> findAllCommunityNoticeVos(Integer pageNum, Integer pageSize){
        List<CommunityNoticeVo> communityNoticeVos= Lists.newArrayList();
        try {
            com.beautifulsoup.chengfeng.pojo.User user=AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            PageHelper.startPage(pageNum,pageSize);
            List<CommunityNotice> communityNotices=communityNoticeMapper.selectByCommunityId(user.getCommunityId());
            communityNotices.stream().forEach(communityNotice -> {
                CommunityNoticeVo vo=new CommunityNoticeVo();
                BeanUtils.copyProperties(communityNotice,vo);
                communityNoticeVos.add(vo);
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }

        return communityNoticeVos;
    }

    @Override
    public List<ProperNoticeVo> findAllProperNoticeVos(Integer pageNum, Integer pageSize) {
        List<ProperNoticeVo> properNoticeVos = Lists.newArrayList();
        try {
            com.beautifulsoup.chengfeng.pojo.User user = AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            PageHelper.startPage(pageNum, pageSize);
            List<ProperNotice> properNotices = properNoticeMapper.selectByUserId(user.getId());
            properNotices.stream().forEach(properNotice -> {
                ProperNoticeVo vo = new ProperNoticeVo();
                BeanUtils.copyProperties(properNotice, vo);
                properNoticeVos.add(vo);
            });
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        }
        return properNoticeVos;
    }

    //最新的记录,用redis来实现,提高效率,数据库加redis
    @Override
    public List<CommunityNoticeVo> findLatestCommunityNoticeVos(Integer limit) {
        List<CommunityNoticeVo> communityNoticeVos=Lists.newArrayList();
        try {
            com.beautifulsoup.chengfeng.pojo.User user =AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            Set<String> keys = stringRedisTemplate.opsForZSet().reverseRange(RedisConstant.COMMUNITY_NOTICE_ORDER + user.getCommunityId(), 0, limit - 1);
            keys.stream().forEach(key->{
                String communityNoticeJson = (String) stringRedisTemplate.opsForHash().get(RedisConstant.COMMUNITY_NOTICES+user.getCommunityId(), key);
                CommunityNotice communityNotice = JsonSerializableUtil.string2Obj(communityNoticeJson, CommunityNotice.class);
                CommunityNoticeVo communityNoticeVo=new CommunityNoticeVo();
                BeanUtils.copyProperties(communityNotice,communityNoticeVo);
                communityNoticeVos.add(communityNoticeVo);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return communityNoticeVos;
    }

    @Override
    public List<ProperNoticeVo> findLatestProperNoticeVos(Integer limit) {
        List<ProperNoticeVo> properNoticeVos=Lists.newArrayList();
        try {
            com.beautifulsoup.chengfeng.pojo.User user = AuthenticationInfoUtil.getUser(userMapper,memcachedClient);
            Set<String> keys = stringRedisTemplate.opsForZSet().reverseRange(RedisConstant.PROPER_NOTICE_ORDER + user.getId(), 0, limit - 1);
            keys.stream().forEach(key->{
                String properNoticeJson = (String) stringRedisTemplate.opsForHash().get(RedisConstant.PROPER_NOTICES+user.getId(), key);
                ProperNotice properNotice = JsonSerializableUtil.string2Obj(properNoticeJson, ProperNotice.class);
                ProperNoticeVo properNoticeVo=new ProperNoticeVo();
                BeanUtils.copyProperties(properNotice,properNoticeVo);
                properNoticeVos.add(properNoticeVo);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return properNoticeVos;
    }

    @Override
    public String submitRepairInfo(RepairBookDto repairBookDto, BindingResult bindingResult) {
        ParamValidatorUtil.validateBindingResult(bindingResult);
        try {
            RepairBook repairBook=new RepairBook();
            BeanUtils.copyProperties(repairBookDto,repairBook);
            repairBook.setUserId(AuthenticationInfoUtil.getUser(userMapper,memcachedClient).getId());
            repairBookMapper.insert(repairBook);
            return "立即报修提交成功";
        } catch (Exception e) {
            log.error("立即报修提交失败");
        }
        return null;
    }

    @Override
    public String searchSecretary(SecretaryBookDto bookDto, BindingResult bindingResult) {
        ParamValidatorUtil.validateBindingResult(bindingResult);
        try {
            SecretaryBook secretaryBook=new SecretaryBook();
            BeanUtils.copyProperties(bookDto,secretaryBook);
            secretaryBook.setUserId(AuthenticationInfoUtil.getUser(userMapper,memcachedClient).getId());
            secretaryBookMapper.insert(secretaryBook);
            return "找书记提交成功";
        } catch (Exception e) {
            log.error("找书记提交失败");
        }
        return null;
    }

    @Override
    public List<WaterBrandVo> findAllWaterBrands() {
        List<WaterBrandVo> waterBrandVoList=Lists.newArrayList();
        try {
            List<WaterBrand> waterBrands=waterBrandMapper.selectByUserId(AuthenticationInfoUtil.getUser(userMapper,memcachedClient).getId());
            waterBrands.stream().parallel().forEach(waterBrand -> {
                WaterBrandVo waterBrandVo=new WaterBrandVo();
                BeanUtils.copyProperties(waterBrand,waterBrandVo);
                waterBrandVoList.add(waterBrandVo);
            });
            return waterBrandVoList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WaterBookVo bookWaterSuply(WatersuplyDto watersuplyDto, BindingResult bindingResult) {
        ParamValidatorUtil.validateBindingResult(bindingResult);

        WaterBookVo waterBookVo=new WaterBookVo();

        WatersuplyBook watersuplyBook=new WatersuplyBook();
        BeanUtils.copyProperties(watersuplyDto,watersuplyBook);
        watersuplyBookMapper.insertSelective(watersuplyBook);

        List<WatersuplyDetails> detailsList = watersuplyDto.getDetailsList();

        List<WatersuplyDetails> watersuplyDetails=Lists.newArrayList();

        if (CollectionUtils.isEmpty(detailsList)){
            throw new ParamException("所购水的数量不正确");
        }

        detailsList.stream().forEach(detail->{
            WatersuplyDetails details=new WatersuplyDetails();
            BeanUtils.copyProperties(detail,details);
            details.setSuplyId(watersuplyBook.getId());
            watersuplyDetailsMapper.insert(details);
            watersuplyDetails.add(details);
        });
        BeanUtils.copyProperties(watersuplyBook,waterBookVo);
        waterBookVo.setDetailsList(watersuplyDetails);
        return waterBookVo;
    }

    @Override
    public List<BannerImage> findCarousalImages() {
        List<BannerImage> carousalImages = bannerImageMapper.getCarousalImage();
        return carousalImages;
    }


}
