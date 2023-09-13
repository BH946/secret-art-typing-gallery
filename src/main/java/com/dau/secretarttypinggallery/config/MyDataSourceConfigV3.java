package com.dau.secretarttypinggallery.config;

import com.dau.secretarttypinggallery.datasource.MyDataSource;
import com.dau.secretarttypinggallery.datasource.MyDataSourcePropertiesV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @EnableConfigurationProperties 로 MyDataSourcePropertiesV3 를 사용 및 스프링 빈
 */
@Slf4j
@EnableConfigurationProperties(MyDataSourcePropertiesV3.class)
public class MyDataSourceConfigV3 {
    private final MyDataSourcePropertiesV3 properties;
    public MyDataSourceConfigV3(MyDataSourcePropertiesV3 properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource dataSource() {
        log.info("BeanTest");
        return new MyDataSource(
                properties.getImgPath()
        );
    }
}