package com.dau.secretarttypinggallery.config;

import com.dau.secretarttypinggallery.datasource.MyDataSource;
import com.dau.secretarttypinggallery.datasource.MyDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @EnableConfigurationProperties 로 MyDataSourceProperties 를 사용 및 스프링 빈
 * @Import 를 통해서 "컴포넌트 스캔 대상!"
 */
@Slf4j
@EnableConfigurationProperties(MyDataSourceProperties.class)
public class MyDataSourceConfig {
    private final MyDataSourceProperties properties;
    public MyDataSourceConfig(MyDataSourceProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MyDataSource getMyDataSource() {
        log.info("BeanTest");
        return new MyDataSource(
                properties.getImgPath()
        );
    }
}