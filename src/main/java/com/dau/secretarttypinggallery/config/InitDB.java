package com.dau.secretarttypinggallery.config;


import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
//@Component
@RequiredArgsConstructor // 생성자 주입
public class InitDB {
    private final InitService initService;


    // 해당 클래스 인스턴스 생성(construct)된 후 자동 실행
//    @PostConstruct
    public void init() {
        initService.initItem();
    }

//    @Service
    @RequiredArgsConstructor
//    @Transactional // 쓰기모드 -> 바로 DB 저장
    public static class InitService {
        private final EntityManager em;
        private final ItemRepository itemRepository;

        public void initItem() {
            Item item1 = Item.createItem("김익명", "123", "최근에 있었던 대외비", "최근에 이름 들으면 알 만한 회사랑 어떤 프로젝트 협업할 뻔했는데, 중간에 갑자기 뭐가 바껴서 결국 나랑 하기로 한 건 무산되었다. 너무 아까운 일이었는데 대외비라 어디에 이름 까고 말도 못해서 답답했음. 근데 최근에 보니까 그 프로젝트 초대박났더라고 하......^^^^;;; 또 생각해도 개빡치네*발ㅠㅠ", "test.jpeg");
            Item item2 = Item.createItem("", "1234", "", "", "");
            Item item3 = Item.createItem("철수", "123", "", "테스트", "test.jpeg");
            itemRepository.save(item1);
            itemRepository.save(item2);
            itemRepository.save(item3);
            List<Item> items = itemRepository.updateAllNo();
//            for(int i= 0 ; i<150; i++) {
//                String name = "test"+i;
//                Item item = Item.createItem(name, "12345", "테스트", "테스트123123", "");
//                itemRepository.save(item);
//            }
        }

    }
}
