package com.dau.secretarttypinggallery.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    // default
    @GetMapping("home") // URL 매핑(GET)
    public String home() {
        return "home"; // home.html 반환
    }

    // test
    @GetMapping("test") // URL 매핑(GET)
    public String test() {
        return "test"; // test.html 반환
    }
}
