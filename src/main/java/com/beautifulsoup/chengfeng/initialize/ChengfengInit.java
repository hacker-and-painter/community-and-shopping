package com.beautifulsoup.chengfeng.initialize;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.dao.UserMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseEvaluation;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.beautifulsoup.chengfeng.pojo.User;
import com.beautifulsoup.chengfeng.service.dto.PurchaseInfoDto;
import com.beautifulsoup.chengfeng.utils.JsonSerializableUtil;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChengfengInit implements InitializingBean {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    private PurchaseProductMapper productMapper;


    @Override
    public void afterPropertiesSet() throws Exception {
        List<User> users=userMapper.selectAllUsers();
        users.stream().parallel()
                .forEach(user->{
                    stringRedisTemplate.opsForHash().put(RedisConstant.USERINFOS,user.getNickname(), JsonSerializableUtil.obj2String(user));
                    redisTemplate.opsForHash().put(RedisConstant.USERS,user.getNickname(),user);
                });

        List<PurchaseProductSku> collect = productMapper.selectAllPurchaseProducts().parallelStream()
                .map(PurchaseProduct::getPurchaseProductSkus)
                .flatMap(sku -> sku.stream())
                .collect(Collectors.toList());
        collect.parallelStream().forEach(sku->{
            //将商品库存信息加入redis,提高性能
            stringRedisTemplate.opsForHash().put(RedisConstant.PRODUCT_STOCKS,
                    RedisConstant.PRODUCT_PREFIX_SKU+sku.getId(),
                    String.valueOf(sku.getStock()));
        });

    }



}
