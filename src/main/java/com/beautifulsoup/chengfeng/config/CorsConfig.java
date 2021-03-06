package com.beautifulsoup.chengfeng.config;

import com.beautifulsoup.chengfeng.annotation.NotUsed;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//@NotUsed
public class CorsConfig {


    public CorsConfiguration buildConfig(){
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addExposedHeader("Authorization");
//        corsConfiguration.addExposedHeader(X_TOTAL_COUNT);
        return corsConfiguration;
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",buildConfig());
        return source;
    }

}
