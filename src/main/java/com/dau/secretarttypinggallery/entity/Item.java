package com.dau.secretarttypinggallery.entity;


import com.dau.secretarttypinggallery.entity.dto.AddItemDto;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

  @Id
  @GeneratedValue
  @Column(name = "item_id")
  private Long id; // No.로도 사용하겠음.

  private Long No;

  private String nickName;
  private String password;
  private String title;
  private String content;
  private String imgSrc;

  @DateTimeFormat(pattern = "yy.MM.dd.HH:mm")
  private LocalDateTime date1;
  @DateTimeFormat(pattern = "yy년 MM월 dd일 HH시 mm분")
  private LocalDateTime date2;

  //==생성 편의 메서드==//
  public static Item createItem(String nickName, String password, String title, String content,
      String imgSrc) {
    Item item = new Item();
    item.nickName = (nickName.equals("")) ? "익명" : nickName;
    item.password = (password.equals("")) ? "" : password;
    item.title = (title.equals("")) ? "무제" : title;
    item.content = (content.equals("")) ? "" : content;
    item.imgSrc = imgSrc;
//        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yy.MM.dd.HH:mm");
//        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yy년 MM월 dd일 HH시 mm분");
//        item.date1 = LocalDateTime.now().format(formatter1);
//        item.date2 = LocalDateTime.now().format(formatter2);
    item.date1 = LocalDateTime.now();
    item.date2 = LocalDateTime.now();
    return item;
  }

  public static Item createItem(AddItemDto addItemDto) {
    Item item = new Item();
    item.nickName = (addItemDto.getNickName().equals("")) ? "익명" : addItemDto.getNickName();
    item.password = (addItemDto.getPassword().equals("")) ? "" : addItemDto.getPassword();
    item.title = (addItemDto.getTitle().equals("")) ? "무제" : addItemDto.getTitle();
    item.content = (addItemDto.getContent().equals("")) ? "" : addItemDto.getContent();
    item.imgSrc = addItemDto.getImgSrc();

    item.date1 = LocalDateTime.now();
    item.date2 = LocalDateTime.now();
    return item;
  }

  //==비지니스 로직 편의 메서드==//
  public Item updateItem(UpdateItemDto dto) {
    this.nickName = (dto.getNickName().equals("")) ? "익명" : dto.getNickName();
    this.password = (dto.getPassword().equals("")) ? "" : dto.getPassword();
    this.title = (dto.getTitle().equals("")) ? "무제" : dto.getTitle();
    this.content = (dto.getContent().equals("")) ? "" : dto.getContent();
    // 최신 업데이트 시간
    this.date1 = LocalDateTime.now();
    this.date2 = LocalDateTime.now();
    return this;
  }
}
