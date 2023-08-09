package com.dau.secretarttypinggallery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // Spring Boot Cache 사용을 선언
public class SecretArtTypingGalleryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecretArtTypingGalleryApplication.class, args);
	}

}
