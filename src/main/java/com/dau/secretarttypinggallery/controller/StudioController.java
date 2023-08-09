package com.dau.secretarttypinggallery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * studio.html, studio_product.html 을 담당
 */

@Controller
@RequiredArgsConstructor
public class StudioController {

    // default
    @GetMapping("studio") // URL 매핑(GET)
    public String studio() {
        return "studio"; // studio.html 반환
    }

    // 스튜디오 완성화면
    @GetMapping("studioComplete") // URL 매핑(GET)
    public String studioComplete() {
        return "studio_product"; // studio_product.html 반환
    }


}
