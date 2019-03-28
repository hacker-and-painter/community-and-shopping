package com.beautifulsoup.chengfeng.tasks.job;

import com.beautifulsoup.chengfeng.constant.RedisConstant;
import com.beautifulsoup.chengfeng.dao.PurchaseProductSkuMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
public class StockJob implements Job {

    private static final Splitter splitter=Splitter.on(":").trimResults().omitEmptyStrings();
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private PurchaseProductSkuMapper productSkuMapper;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
      log.info(String.join(":",new Date().toString(),"redis库存同步"));
      stringRedisTemplate.opsForHash().keys(RedisConstant.PRODUCT_STOCKS).parallelStream().forEach(key->{
          List<String> skusPrefix = splitter.splitToList(key.toString());
          String s = skusPrefix.get(1);
          log.info("skuId："+s);
          String redisStock = (String) stringRedisTemplate.opsForHash().get(RedisConstant.PRODUCT_STOCKS, key);
          PurchaseProductSku productSku = productSkuMapper.selectByPrimaryKey(Integer.parseInt(s));
          productSku.setSales(productSku.getSales()+(productSku.getStock()-Integer.parseInt(redisStock)));
          productSku.setStock(Integer.parseInt(redisStock));
          productSkuMapper.updateByPrimaryKey(productSku);
      });
    }
}
