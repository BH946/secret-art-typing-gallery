package com.dau.secretarttypinggallery.config;


import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.repository.ItemRepository;
import com.dau.secretarttypinggallery.service.ItemService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Slf4j
@Component
@RequiredArgsConstructor // 생성자 주입
public class InitDB {
    private final InitService initService;


    // 해당 클래스 인스턴스 생성(construct)된 후 자동 실행
    @PostConstruct
    public void init() {
        initService.initItem();
    }

    @Service
    @RequiredArgsConstructor
    @Transactional // 쓰기모드 -> 바로 DB 저장
    public static class InitService {
        private final EntityManager em;
        private final ItemRepository itemRepository;

        public void initItem() {
            Item item1 = Item.createItem("김익명", "12345", "최근에 있었던 대외비", "최근에 이름 들으면 알 만한 회사랑 어떤 프로젝트 협업할 뻔했는데, 중간에 갑자기 뭐가 바껴서 결국 나랑 하기로 한 건 무산되었다. 너무 아까운 일이었는데 대외비라 어디에 이름 까고 말도 못해서 답답했음. 근데 최근에 보니까 그 프로젝트 초대박났더라고 하......^^^^;;; 또 생각해도 개빡치네*발ㅠㅠ", "");
            Item item2 = Item.createItem("", "1234", "", "", "");
            itemRepository.save(item1);
            itemRepository.save(item2);
            for(int i= 0 ; i<150; i++) {
                String name = "test"+i;
                Item item = Item.createItem(name, "12345", "테스트", "테스트123123", "");
                itemRepository.save(item);
            }
        }

    }
}
