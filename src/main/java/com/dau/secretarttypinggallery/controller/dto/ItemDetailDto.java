package com.dau.secretarttypinggallery.controller.dto;

import com.dau.secretarttypinggallery.entity.Item;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public class ItemDetailDto {
    private Long id;
    private String title;
    private String nickName;
    private String content;
    @DateTimeFormat(pattern = "yy.MM.dd.HH:mm")
    private LocalDateTime date1;
    @DateTimeFormat(pattern = "yy년 MM월 dd일 HH시 mm분")
    private LocalDateTime date2;
    private String imgSrc;
    private String password;

    //==생성 편의 메서드==//
    public ItemDetailDto(Item item) {
        this.id = item.getId();
        this.content = item.getContent();
        this.title = item.getTitle();
        this.date2 = item.getDate2();
        this.date1 = item.getDate1();
        this.nickName = item.getNickName();
        this.imgSrc = item.getImgSrc();
        this.password = item.getPassword();
    }
}
