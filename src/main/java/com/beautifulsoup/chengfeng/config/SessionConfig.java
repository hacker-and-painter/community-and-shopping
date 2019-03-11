package com.beautifulsoup.chengfeng.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400)
public class SessionConfig {
}
