package com.beautifulsoup.chengfeng.service.impl;

import com.beautifulsoup.chengfeng.controller.vo.ShippingVo;
import com.beautifulsoup.chengfeng.dao.PurchaseShippingMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.exception.ParamException;
import com.beautifulsoup.chengfeng.pojo.PurchaseShipping;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.PurchaseShippingService;
import com.beautifulsoup.chengfeng.service.dto.ShippingDto;
import com.beautifulsoup.chengfeng.utils.AuthenticationInfoUtil;
import com.beautifulsoup.chengfeng.utils.ParamValidatorUtil;
import com.google.common.collect.Lists;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class PurchaseShippingServiceImpl implements PurchaseShippingService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private PurchaseShippingMapper shippingMapper;

    @Override
    public ShippingVo createNewShipping(ShippingDto shippingDto, BindingResult result) {
        ParamValidatorUtil.validateBindingResult(result);
        try {
            PurchaseShipping purchaseShipping=new PurchaseShipping();
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            BeanUtils.copyProperties(shippingDto,purchaseShipping);
            purchaseShipping.setUserId(user.getId());
            shippingMapper.insertSelective(purchaseShipping);
            ShippingVo shippingVo=new ShippingVo();
            BeanUtils.copyProperties(purchaseShipping,shippingVo);
            return shippingVo;
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
    public List<ShippingVo> listAllShippings() {
        try {
            List<ShippingVo> shippingVos= Lists.newArrayList();
            User user = AuthenticationInfoUtil.getUser(userMapper, memcachedClient);
            List<PurchaseShipping> shippings=shippingMapper.selectAllShippingsByUserId(user.getId());
            shippings.stream().forEach(shipping->{
                ShippingVo shippingVo=new ShippingVo();
                BeanUtils.copyProperties(shipping,shippingVo);
                shippingVos.add(shippingVo);
            });
            return shippingVos;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MemcachedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    @Override
    public ShippingVo deleteShippingById(Integer shippingId) {
        PurchaseShipping purchaseShipping = shippingMapper.selectByPrimaryKey(shippingId);
        if (null==purchaseShipping){
            throw new ParamException("收货地址不存在,删除失败");
        }
        ShippingVo shippingVo=new ShippingVo();
        BeanUtils.copyProperties(purchaseShipping,shippingVo);
        shippingMapper.deleteByPrimaryKey(shippingId);
        return shippingVo;
    }


}
