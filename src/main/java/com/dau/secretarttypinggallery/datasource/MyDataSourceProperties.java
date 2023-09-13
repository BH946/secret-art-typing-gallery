package com.dau.secretarttypinggallery.datasource;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


/**
 * @ConfigurationProperties 로 application.yml 에 저장한 my.datasource 하위 내용
 * 가져와서 "자바 객체" 로 변환 + 검증까지
 */
@Getter
//@ConfigurationProperties("my.variable")
@ConfigurationProperties("my.datasource")
@Validated
public class MyDataSourceProperties {
    @NotEmpty
    private String imgPath;

    public MyDataSourceProperties(String imgPath) {
        this.imgPath = imgPath;
    }


}
