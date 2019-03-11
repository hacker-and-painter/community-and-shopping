package com.beautifulsoup.chengfeng.config;

import com.beautifulsoup.chengfeng.prop.MemcachedProperties;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@Slf4j
@EnableConfigurationProperties(value = {MemcachedProperties.class})
public class MemcachedConfig {


    @Autowired
    private MemcachedProperties memcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClient() {
        MemcachedClient memcachedClient = null;
        try {
            MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.
                    getAddresses(memcachedProperties.getServers()));
            builder.setConnectionPoolSize(memcachedProperties.getPoolSize());
            builder.setOpTimeout(memcachedProperties.getOpTimeout());
            memcachedClient = builder.build();
        } catch (IOException e) {
            log.error("inint MemcachedClient failed ",e);
        }
        return memcachedClient;
    }

}
