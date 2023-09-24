package com.dau.secretarttypinggallery.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final MyDataSourceConfig source;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.maxAge(Duration.ofDays(365));
        registry.addResourceHandler("/image/**")
//                .addResourceLocations("file:///C:/images-spring/");
//                .addResourceLocations("file:///var/www/images-spring/");
                .addResourceLocations("file:///"+source.getMyDataSource().getImgPath());

        registry.addResourceHandler("/**") // **/*.*, /resources/**
                .addResourceLocations("classpath:/static/")
                .setCacheControl(cacheControl); // 정적 리소스들 캐시 추가
    }
}
