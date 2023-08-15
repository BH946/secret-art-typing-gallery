package com.dau.secretarttypinggallery.entity.dto;

import lombok.Getter;

@Getter
public class UpdateItemDto {
    private String nickName;
    private String password;
    private String title;
    private String content;
    private String imgSrc;
    public UpdateItemDto (String nickName, String password, String title, String content, String imgSrc) {
        this.nickName = nickName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.imgSrc = imgSrc;
    }
}
