package com.beautifulsoup.chengfeng.mapper;

import com.beautifulsoup.chengfeng.dao.PurchaseProductMapper;
import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import com.beautifulsoup.chengfeng.pojo.PurchaseProductSku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ProductMapperTest {

    @Autowired
    private PurchaseProductMapper productMapper;

    @Test
    public void productMapperTest(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");//ElasticSearch启动报错
        List<PurchaseProductSku> collect = productMapper.selectAllPurchaseProducts().parallelStream()
                .map(PurchaseProduct::getPurchaseProductSkus)
                .flatMap(sku -> sku.stream())
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}
