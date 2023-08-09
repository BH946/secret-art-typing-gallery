package com.dau.secretarttypinggallery.controller.dto;

import com.dau.secretarttypinggallery.entity.Item;
import lombok.Getter;

@Getter
public class ItemDto {
    private Long id;
    private String title;
    private String nickName;
    private String date1;

    //==생성 편의 메서드==//
    public ItemDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.nickName = item.getNickName();
        this.date1 = item.getDate1();
    }
}
