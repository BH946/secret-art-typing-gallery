package com.dau.secretarttypinggallery.controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class errorTest {
    /**
     * 일부러 에러 발생 시키기 -> 예외 페이지 확인위해
     * 테스트가 아닌 실제로 에러 발생시 해당 에러내용들이 예외 페이지로 정리되어 출력
     */

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404 오류!");
    }
    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }
    @GetMapping("/error-403")
    public void error403(HttpServletResponse response) throws IOException {
        response.sendError(403, "403 오류!");
    }
}
