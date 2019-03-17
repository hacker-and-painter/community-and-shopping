package com.beautifulsoup.chengfeng.elasticsearch;

import com.beautifulsoup.chengfeng.pojo.PurchaseProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    public void setup(){

    }

    @Test
    public void createIndex(){
        System.setProperty("es.set.netty.runtime.available.processors", "false");//ElasticSearch启动报错
        this.elasticsearchTemplate.createIndex(PurchaseProduct.class);
        this.elasticsearchTemplate.putMapping(PurchaseProduct.class);
    }



}
