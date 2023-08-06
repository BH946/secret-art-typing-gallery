package com.dau.secretarttypinggallery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StudioController {

    // default
    @GetMapping("studio") // URL 매핑(GET)
    public String Studio() {
        return "studio"; // studio.html 반환
    }

    // test
    @GetMapping("test") // URL 매핑(GET)
    public String test() {
        return "test"; // test.html 반환
    }
}
