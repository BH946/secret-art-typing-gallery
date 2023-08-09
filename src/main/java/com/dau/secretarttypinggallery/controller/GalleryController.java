package com.dau.secretarttypinggallery.controller;

import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * product_detail.html, gallery.html 를 담당
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("gallery")
@Slf4j
public class GalleryController {
    private final ItemService itemService;

    // default
    // 나중에 페이징 적당히 지정해주면 됨. 현재는 테스트니까 그냥 데이터 전부 다가져오겠음.
    @GetMapping() // URL 매핑(GET)
    public String gallery(Model model) {
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "gallery"; // gallery.html 반환
    }
    // 삭제, 수정, 추가에서 findAllNoCache 이걸 쓸건데 지금은 그냥 테스트용으로..
    @GetMapping("/test")
    public String test(Model model) {
        List<Item> items = itemService.findAllNoCache();
        model.addAttribute("items", items);
        return "redirect:/gallery";
    }


    // id별 작품상세 화면
    @GetMapping("/productDetail/{itemId}")
    public String ProductDetail(@PathVariable Long itemId, Model model) {
        // 해당 id값으로 db에서 데이터가져온다. -> test용으로 임의로 지정.
//        if(itemId == 1) {
//            model.addAttribute("product", itemId); // text용
//        }
        return "product_detail";
    }

}
