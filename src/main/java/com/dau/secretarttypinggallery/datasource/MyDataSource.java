package com.dau.secretarttypinggallery.datasource;


import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그 확인용!! 외부설정 내용!!
 */
@Slf4j
@Data
public class MyDataSource {
    private String imgPath;

    // 생성자
    public MyDataSource(String imgPath) {
        this.imgPath = imgPath;
    }
    
    @PostConstruct // 로딩완료 후 바로 실행
    public void init() {
        log.info("imgPath={}", imgPath);
    }
}