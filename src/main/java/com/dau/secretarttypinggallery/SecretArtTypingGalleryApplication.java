package com.dau.secretarttypinggallery;

import com.dau.secretarttypinggallery.config.MyDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;


@Import(MyDataSourceConfig.class) // 해당 파일 컴포넌트 스캔 필수
@SpringBootApplication
@EnableCaching // Spring Boot Cache 사용을 선언
public class SecretArtTypingGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretArtTypingGalleryApplication.class, args);
	}

}
