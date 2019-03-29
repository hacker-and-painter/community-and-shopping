package com.beautifulsoup.chengfeng.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
    private String brokerList;
    private String topic;
    private String pClientId;
    private String ackConfig;
    private Integer retries;
    private String cGroupId;
    private String cClientId;
    private Integer linger;
}
