package com.beautifulsoup.chengfeng.prop;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProperties {
    private String addresses;
    private Integer port;
    private String virtualHost;
    private String username;
    private String password;

}
