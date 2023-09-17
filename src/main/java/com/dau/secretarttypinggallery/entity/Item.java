package com.dau.secretarttypinggallery.entity;


import com.dau.secretarttypinggallery.entity.dto.AddItemDto;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id; // No로도 사용하겠음.

    private String nickName;
    private String password;
    private String title;
    private String content;
    private String imgSrc;

    private String date1;
    private String date2;

    //==생성 편의 메서드==//
    public static Item createItem(String nickName, String password, String title, String content, String imgSrc) {
        Item item = new Item();
        item.nickName = (nickName.equals(""))?"익명":nickName;
        item.password = (password.equals(""))?"":password;
        item.title = (title.equals(""))?"무제":title;
        item.content = (content.equals(""))?"":content;
        item.imgSrc = imgSrc;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yy.MM.dd.HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분");
        item.date1 = LocalDateTime.now().format(formatter1);
        item.date2 = LocalDateTime.now().format(formatter2);
        return item;
    }
    public static Item createItem(AddItemDto addItemDto) {
        Item item = new Item();
        item.nickName = (addItemDto.getNickName().equals(""))?"익명": addItemDto.getNickName();
        item.password = (addItemDto.getPassword().equals(""))?"":addItemDto.getPassword();
        item.title = (addItemDto.getTitle().equals(""))?"무제":addItemDto.getTitle();
        item.content = (addItemDto.getContent().equals(""))?"":addItemDto.getContent();
        item.imgSrc = addItemDto.getImgSrc();
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yy.MM.dd.HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분");
        item.date1 = LocalDateTime.now().format(formatter1);
        item.date2 = LocalDateTime.now().format(formatter2);
        return item;
    }

    //==비지니스 로직 편의 메서드==//
    public Item updateItem(UpdateItemDto dto) {
        this.nickName = (dto.getNickName().equals(""))?"익명":dto.getNickName();
        this.password = (dto.getPassword().equals(""))?"":dto.getPassword();
        this.title = (dto.getTitle().equals(""))?"무제":dto.getTitle();
        this.content = (dto.getContent().equals(""))?"":dto.getContent();
//        this.imgSrc = dto.getImgSrc(); // 이미지는 생성때만@!!
        // 최신 업데이트 시간
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yy.MM.dd.HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분");
        this.date1 = LocalDateTime.now().format(formatter1);
        this.date2 = LocalDateTime.now().format(formatter2);
        return this;
    }
}
