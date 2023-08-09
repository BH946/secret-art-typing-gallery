package com.dau.secretarttypinggallery.controller;

import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * studio.html, studio-complete.html 을 담당
 */

@Controller
@RequiredArgsConstructor
public class StudioController {

    // default
    @GetMapping("studio") // URL 매핑(GET)
    public String studio() {
        return "studio"; // studio.html 반환
    }


    /**
     * 작품 완성 화면
     */
    @GetMapping("studioComplete") // URL 매핑(GET)
    public String studioComplete() {
        return "studio-complete"; // studio-complete.html 반환
    }
    /**
     * 작품 전시하기 -> DB에 추가
     */
//    @PostMapping("studioComplete")
//    public String studioAdd() {
//
//    }


    /**
     * 작품 업데이트 -> DB 수정
     */
//    @GetMapping("studioComplete/{itemId}")
//    public String studioCompleteId() {
//
//    }
//    @PostMapping("studioComplete/{itemId}")
//    public String studioUpdate(@PathVariable Long itemId, UpdateItemDto form) {
//
//    }



    //    @PostMapping("/update/{itemId}")
//    public String updateGalleryItem(@PathVariable Long itemId, UpdateItemDto form) {
//        Item item = itemService.findOne(itemId); // 이미 없으면 null
//        if(item != null) {
//            if(item.getPassword().equals(form.getPassword())) {
//                log.info("비번통과");
//                int pageId = itemService.findPageId(itemId);
//                item.updateItem(form);
//                itemService.updateCachePage(pageId);
//            }
//            else log.info("비번실패");
//        }
//        else return "redirect:/gallery"; // gallery() 함수로 이동
//        return "redirect:/gallery/itemDetail/"+itemId; // galleryItemDetail() 함수로 이동
//    }
}
