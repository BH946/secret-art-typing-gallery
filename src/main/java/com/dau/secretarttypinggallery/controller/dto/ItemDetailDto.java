package com.dau.secretarttypinggallery.controller.dto;

import com.dau.secretarttypinggallery.entity.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ItemDetailDto {
    private Long id;
    private String title;
    private String nickName;
    private String content;
    private String date2;

    //==생성 편의 메서드==//
    public ItemDetailDto(Item item) {
        this.id = item.getId();
        this.content = item.getContent();
        this.title = item.getTitle();
        this.date2 = item.getDate2();
        this.nickName = item.getNickName();
    }
}
