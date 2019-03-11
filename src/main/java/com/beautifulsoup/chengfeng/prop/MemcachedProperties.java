package com.beautifulsoup.chengfeng.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "memcached")
public class MemcachedProperties {
    private String servers;
    private Integer poolSize;
    private Integer opTimeout;
}
