package com.dau.secretarttypinggallery.controller.dto;

import com.dau.secretarttypinggallery.entity.Item;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class ItemDto {
    private Long id;
    private String title;
    private String nickName;
    private String imgSrc;
    @DateTimeFormat(pattern = "yy.MM.dd.HH:mm")
    private LocalDateTime date1;
    @DateTimeFormat(pattern = "yy년 MM월 dd일 HH시 mm분")
    private LocalDateTime date2;

    //==생성 편의 메서드==//
    public ItemDto(Item item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.nickName = item.getNickName();
        this.imgSrc = item.getImgSrc();
        this.date1 = item.getDate1();
        this.date2 = item.getDate2();
    }
}
