package com.dau.secretarttypinggallery.controller;

import com.dau.secretarttypinggallery.config.MyDataSourceConfig;
import com.dau.secretarttypinggallery.controller.dto.StudioItemDto;
import com.dau.secretarttypinggallery.datasource.MyDataSource;
import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.AddItemDto;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import com.dau.secretarttypinggallery.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * studio.html, studio-complete.html 을 담당
 * 작 품 전시도 마찬가지로 추가, 수정하는거라서 꼭 캐싱 작업 필수
 */

@Slf4j
@Controller
@RequiredArgsConstructor
public class StudioController {
    private final ItemService itemService;
    private final MyDataSourceConfig source;

    /**
     * 작품 제작실 화면 -> 일명 "스튜디오"
     * base64 가 너무 길어서 이곳 POST 에서 바로 "이미지만 저장"
     */
    @GetMapping("studio") // URL 매핑(GET)
    public String studio(Model model) {
        Long totalCount = itemService.findTotalCount();
        model.addAttribute("totalCount", totalCount);
        return "studio"; // studio.html 반환
    }
    @PostMapping("studio") // 이미지 제작 후 -> complete 화면이동
    public String studioImg(@RequestParam String imgSrc, Model model, RedirectAttributes redirectAttributes) {
        log.debug("imgSrc : {}", imgSrc);
        MyDataSource myDataSource = source.getMyDataSource();
        log.debug("imgPath = {}", myDataSource.getImgPath());
        FileOutputStream fo = null;
        try{
            imgSrc = imgSrc.replaceAll("data:image/jpeg;base64,", "");
            byte[] file = Base64.decodeBase64(imgSrc); // 인코드된 Base64를 디코드
            log.debug("try 안으로 들어옴");
            String fileName = UUID.randomUUID().toString();
//            String filePath = "C:/images-spring/"+fileName+".jpeg";
//            String filePath = "/var/www/images-spring/"+fileName+".jpeg";
            String filePath = myDataSource.getImgPath()+fileName+".jpeg";
            log.debug("filePath : {}", filePath);
            fo = new FileOutputStream(filePath);
            imgSrc = fileName+".jpeg"; // 이름을 기록
            fo.write(file);
            log.debug("image 생성 성공");
            fo.close();
        }catch(Exception e){
            log.debug("catch로 들어옴 - 이미지 생성 실패");
            e.printStackTrace();
        }
        redirectAttributes.addAttribute("status", "addON");
        redirectAttributes.addAttribute("imgSrc", imgSrc);
        return "redirect:/studioComplete"; // PRG 패턴 적용
    }
    
    /**
     * ADD!!
     * 작품 완성 화면 -> POST로 작품 전시 (DB추가)
     */
    @GetMapping("studioComplete") // URL 매핑(GET)
    public String studioComplete(Model model,
                                 @RequestParam(defaultValue = "") String imgSrc
                                 ) {
        log.debug("imgSrc : {}", imgSrc);

        // 기본적으로 th:object 같은 문법 사용시 "빈값"으로 세팅을 해둬야 안전 + forward 로 자원재활용
        Item item = Item.createItem("","","","", imgSrc);
        Long totalCount = itemService.findTotalCount();

        model.addAttribute("item", item);
        model.addAttribute("totalCount", totalCount);
        return "studio-complete"; // studio-complete.html 반환
    }
    /**
     * 처음 전시라 id 없는경우임 -> 전시실 미지정 상태
     * @ModelAttribute("item") 매우중요!!
     * => th:object를 item 사용하므로 반드시 Model에 "item"으로 담기게끔!
     */
    @PostMapping("studioComplete")
    public String studioAdd(@Validated @ModelAttribute("item") AddItemDto form, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) throws IOException {
        // FieldError 는 알아서 검증
        // ObjectError 인 특정 필드가 아닌 복합 룰 검증 - 딱히 할거없음 PASS

        if(bindingResult.hasErrors()) {
            log.info("error={}", bindingResult);
            return "studio-complete"; // studio-complete.html 반환 -> forward 로 자원 재활용
        }

        // 성공 로직
        Item item = Item.createItem(form);
        log.debug("form:{}", form.getImgSrc());
        log.debug("item:{}", item.getImgSrc());
        itemService.save(item); // 이때 id 할당받음
        int pageId = itemService.findPageId(item.getId()); // 바로 가져올 수 있음
        itemService.updateAllWithPage(pageId); // 캐싱
        itemService.updateTotalCount(); // 캐싱
        redirectAttributes.addAttribute("status", "addON");
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("pageId", pageId);
        return "redirect:/gallery/{pageId}/itemDetail/{itemId}";
        // PRG 패턴 적용
    }


    /**
     * UPDATE!!
     * 이곳은 작품 상세화면에서 "수정" 버튼을 눌러서 넘어온 경우이다.
     * id와 함께 넘어오기 때문에 해당 item 데이터를 다 가져올 수 있다.
     * 작품 업데이트 -> DB 수정(POST로)
     */
    @GetMapping("studioComplete/{itemId}")
    public String studioCompleteId(@PathVariable Long itemId, Model model) {
        Long totalCount = itemService.findTotalCount();
        Item item = itemService.findOne(itemId);
        StudioItemDto studioItemDto = new StudioItemDto(item);
        model.addAttribute("item", studioItemDto);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("itemId", itemId); // add, update 구분하려는 목적
        return "studio-complete"; // studio-complete.html 반환
    }
    /**
     * 전시된것을 수정하는거라 id 있음 -> 전시실 이미 지정된 상태
     */
    @PostMapping("studioComplete/{itemId}")
    public String studioIdUpdate(@PathVariable Long itemId, UpdateItemDto form, RedirectAttributes redirectAttributes) {
        Item item = itemService.findOne(itemId); // 없으면 null
        if(item != null) {
            int pageId = itemService.findPageId(itemId);
            itemService.update(item.getId(), form);
            itemService.updateAllWithPage(pageId);
            redirectAttributes.addAttribute("status", "updateON");
            redirectAttributes.addAttribute("itemId", item.getId());
            redirectAttributes.addAttribute("pageId", pageId);
            return "redirect:/gallery/{pageId}/itemDetail/{itemId}";
        }
        // 이부분도 문제 있음 - error
        return "redirect:/gallery"; // gallery() 함수로 이동
    }
}
