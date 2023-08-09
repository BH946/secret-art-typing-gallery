package com.dau.secretarttypinggallery.entity.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // 생성자 주입
public class UpdateItemDto {
    private final String nickName, password, title, content;
}
