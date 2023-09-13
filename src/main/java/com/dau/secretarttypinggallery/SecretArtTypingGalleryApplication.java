package com.dau.secretarttypinggallery;

import com.dau.secretarttypinggallery.config.MyDataSourceConfig;
import com.dau.secretarttypinggallery.config.MyDataSourceConfigV3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;


//@Import(MyDataSourceConfigV3.class) // 해당 파일 컴포넌트 스캔
@SpringBootApplication
@EnableCaching // Spring Boot Cache 사용을 선언
public class SecretArtTypingGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretArtTypingGalleryApplication.class, args);
	}

}
