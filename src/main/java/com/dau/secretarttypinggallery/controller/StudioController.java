package com.dau.secretarttypinggallery.controller;

import com.dau.secretarttypinggallery.controller.dto.StudioImgDto;
import com.dau.secretarttypinggallery.controller.dto.StudioItemDto;
import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import com.dau.secretarttypinggallery.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * studio.html, studio-complete.html 을 담당
 * 작 품 전시도 마찬가지로 추가, 수정하는거라서 꼭 캐싱 작업 필수
 */

@Controller
@RequiredArgsConstructor
public class StudioController {
    private final ItemService itemService;

    /**
     * 작품 제작실 화면 -> 일명 "스튜디오"
     */
    @GetMapping("studio") // URL 매핑(GET)
    public String studio() {
        return "studio"; // studio.html 반환
    }
    @PostMapping("studio") // 이미지 제작 후 complete 화면
    public String studioImg(StudioImgDto item, Model model) {
//        System.out.println(item.getImgSrc());
        model.addAttribute("imgSrc", item.getImgSrc());
        return "studio-complete"; // studio-complete.html 반환
    }
    
    /**
     * 작품 완성 화면 -> POST로 작품 전시(DB추가)
     * 이때 생성한 "앨범(사진)" 데이터가 함께 넘어올것임.
     */
    @GetMapping("studioComplete") // URL 매핑(GET)
    public String studioComplete() {
        return "studio-complete"; // studio-complete.html 반환
    }
    /**
     * 처음 전시라 id 없는경우임 -> 전시실 미지정상태
     * 이때 이미지도 저장하게되고 db에는 경로를 기록하게 되는 것
     */
    @PostMapping("studioComplete")
    public String studioAdd(UpdateItemDto form) throws IOException {
        String imgSrc = form.getImgSrc(); // 경로를 바꾸자.ㅇㅇ
        FileOutputStream fo = null;
        try{
            imgSrc = imgSrc.replaceAll("data:image/jpeg;base64,", "");
            byte[] file = Base64.decodeBase64(imgSrc); // 인코드된 Base64를 디코드
            System.out.println("try 안으로 들어옴");
//            System.out.println(file);
            String fileName = UUID.randomUUID().toString();
//            String filePath = "C:/images-spring/"+fileName+".jpeg";
            String filePath = "/var/www/images-spring/"+fileName+".jpeg";
            fo = new FileOutputStream(filePath);
            imgSrc = fileName+".jpeg"; // 이름을 기록
            fo.write(file);
            System.out.println("image 생성 성공");
            fo.close();
        }catch(Exception e){
            System.out.println("catch로 들어옴 - 이미지 생성 실패");
            e.printStackTrace();
        }

        Item item = Item.createItem(form.getNickName(),
                form.getPassword(), form.getTitle(), form.getContent(), imgSrc);
        itemService.save(item); // 이때 id 할당받음
        int pageId = itemService.findPageId(item.getId()); // 바로 가져올 수 있음
        itemService.updateCachePage(pageId); // 캐싱
        return "redirect:/gallery"; // gallery() 함수로 이동
    }


    /**
     * 이곳은 작품 상세화면에서 "수정" 버튼을 눌러서 넘어온 경우이다.
     * id와 함께 넘어오기 때문에 해당 item 데이터를 다 가져올 수 있다.
     * 작품 업데이트 -> DB 수정(POST로)
     */
    @GetMapping("studioComplete/{itemId}")
    public String studioCompleteId(@PathVariable Long itemId, Model model) {
        Item item = itemService.findOne(itemId);
        StudioItemDto studioItemDto = new StudioItemDto(item);
        model.addAttribute("item", studioItemDto);
        return "studio-complete"; // studio-complete.html 반환
    }
    /**
     * 전시된것을 수정하는거라 id 있음 -> 전시실 이미 지정상태
     */
    @PostMapping("studioComplete/{itemId}")
    public String studioIdUpdate(@PathVariable Long itemId, UpdateItemDto form) {
        Item item = itemService.findOne(itemId); // 없으면 null
        if(item != null) {
            int pageId = itemService.findPageId(itemId);
            itemService.update(item.getId(), form);
            itemService.updateCachePage(pageId);
        }
        return "redirect:/gallery"; // gallery() 함수로 이동
    }
}
