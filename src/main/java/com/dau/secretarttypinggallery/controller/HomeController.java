package com.dau.secretarttypinggallery.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // index.html 반환
    }


    @GetMapping("/lobby")
    public String lobby() {
        return "lobby"; // lobby.html 반환
    }
}
