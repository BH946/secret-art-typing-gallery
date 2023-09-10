package com.dau.secretarttypinggallery.controller;

import com.dau.secretarttypinggallery.controller.dto.PWForm;
import com.dau.secretarttypinggallery.controller.dto.ItemDetailDto;
import com.dau.secretarttypinggallery.controller.dto.ItemDto;
import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import com.dau.secretarttypinggallery.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * gallery-item.html, gallery.html 를 담당
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("gallery")
@Slf4j
public class GalleryController {
    private final ItemService itemService;

    /**
     * 페이지 별로 조회 URL 매핑(GET)
     * 1page : 10data 로 구현하겠음
     * 따로 findTotalCount 로 model 에 총 개수도 포함해서 넘기겠음
     * => 캐싱은 따로 안했는데 나중에 필요해보이면 하던지 하겠음.
     */
    @GetMapping() // default
    public String gallery() {
        return "redirect:/gallery/1"; // -> galleryPage() 함수로 토스
    }
    @GetMapping("/{pageId}")
    public String galleryPage(@PathVariable int pageId, Model model) {
        log.info("pageId : {}", pageId);
        List<Item> items = itemService.findAllWithPage(pageId);
        log.info("items : {}", items.size());
        List<ItemDto> itemsDto = items.stream()
                .map(o -> new ItemDto(o))
                .collect(Collectors.toList());
        model.addAttribute("items", itemsDto); // gallery.html 에 넘길 데이터
        model.addAttribute("totalCount", itemService.findTotalCount());
        return "gallery"; // gallery.html 반환
    }

    /**
     * 작품 삭제 -> updateCachePage 도 꼭 같이 사용
     */
    @PostMapping("/delete/{itemId}")
    public String deleteGalleryItem(@PathVariable Long itemId, PWForm form) { // Model을 적용한것처럼 자동으로 속성:값 매칭해서 파라미터 가져온다.
        Item item = itemService.findOne(itemId); // 이미 없으면 null
        if (item != null) {
            log.info("item 널 아님");
            if(item.getPassword().equals(form.getPassword())){
                log.info("비번통과");
                int pageId = itemService.findPageId(itemId);
                itemService.remove(item);
                itemService.updateCachePage(pageId);
            }
            else log.info("비번실패");
        }
        return "redirect:/gallery"; // gallery() 함수로 이동
    }

    /**
     * 작품 수정 -> updateCachePage 도 꼭 같이 사용
     */
    @PostMapping("/update/{itemId}")
    public String updateGalleryItem(@PathVariable Long itemId, PWForm form) {
        Item item = itemService.findOne(itemId); // 이미 없으면 null
        if(item != null) {
            if(item.getPassword().equals(form.getPassword())) {
                log.info("비번통과");
                return "redirect:/studioComplete/"+itemId; // studioComplete() 업데이트
            }
            else log.info("비번실패");
        }
        return "redirect:/gallery/itemDetail/"+itemId; // 기존 화면 다시 로딩
    }



    /**
     * 작품상세 화면 조회
     */
    @GetMapping("/itemDetail/{itemId}")
    public String galleryItemDetail(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        ItemDetailDto itemDetailDto = new ItemDetailDto(item);
        model.addAttribute("item", itemDetailDto);
        return "gallery-item"; // gallery-item.html
    }

}
