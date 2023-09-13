package com.dau.secretarttypinggallery.datasource;

import lombok.Getter;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.List;


/**
 * @ConfigurationProperties 로 application.yml 에 저장한 my.datasource 하위 내용
 * 가져와서 "자바 객체" 로 변환
 */
@Getter
@ConfigurationProperties("my.datasource")
//@Validated
public class MyDataSourcePropertiesV3 {
    private String imgPath;

    public MyDataSourcePropertiesV3(String imgPath) {
        this.imgPath = imgPath;
    }


}
