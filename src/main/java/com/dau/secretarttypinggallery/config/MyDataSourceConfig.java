package com.dau.secretarttypinggallery.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 외부변수 사용
 */
@Slf4j
@Configuration
public class MyDataSourceConfig {
    @Value("${my.datasource.imgPath}")
    private String imgPath;

    @Bean
    public String getImgPath() {
        return imgPath;
    }

    // test
    @PostConstruct
    public void init() {
//        log.info("imgPath = {}", imgPath);
    }
}
