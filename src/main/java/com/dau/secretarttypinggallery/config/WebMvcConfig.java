package com.dau.secretarttypinggallery.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final MyDataSourceConfig source;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
//                .addResourceLocations("file:///C:/images-spring/");
//                .addResourceLocations("file:///var/www/images-spring/");
                .addResourceLocations("file:///"+source.getMyDataSource().getImgPath());
    }
}
