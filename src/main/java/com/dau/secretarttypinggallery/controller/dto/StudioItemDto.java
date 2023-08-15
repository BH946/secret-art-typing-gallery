package com.dau.secretarttypinggallery.controller.dto;

import com.dau.secretarttypinggallery.entity.Item;
import lombok.Getter;

@Getter
public class StudioItemDto {
    // date 빼고 다 필요
    private Long id;
    private String nickName;
    private String password;
    private String title;
    private String content;
    private String imgSrc;

    //==생성 편의 메서드==//
    public StudioItemDto(Item item) {
        this.id = item.getId();
        this.content = item.getContent();
        this.title = item.getTitle();
        this.password = item.getPassword();
        this.nickName = item.getNickName();
        this.imgSrc = item.getImgSrc();
    }
}
