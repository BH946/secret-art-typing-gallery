package com.dau.secretarttypinggallery.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateItemDto {
//    @NotBlank
//    private Long id;
    @NotNull
    private String nickName;
    @NotNull
    private String password;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotBlank(message = "이미지가 없습니다. 다시 생성하세요.")
    private String imgSrc;

    public UpdateItemDto (String nickName, String password, String title, String content, String imgSrc) {
//        this.id = id;
        this.nickName = nickName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.imgSrc = imgSrc;
    }
}
