package com.dau.secretarttypinggallery.entity.dto;

import com.dau.secretarttypinggallery.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddItemDto {
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

    //==생성 편의 메서드==//
    public AddItemDto(String nickName, String password, String title, String content, String imgSrc) {;
        this.nickName = nickName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.imgSrc = imgSrc;
    }
}