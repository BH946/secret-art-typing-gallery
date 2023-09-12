package com.dau.secretarttypinggallery;

import com.dau.secretarttypinggallery.config.MyDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableCaching // Spring Boot Cache 사용을 선언
public class SecretArtTypingGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretArtTypingGalleryApplication.class, args);
	}

}
