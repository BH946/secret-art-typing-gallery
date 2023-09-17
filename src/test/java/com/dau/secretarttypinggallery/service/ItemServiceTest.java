package com.dau.secretarttypinggallery.service;

import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@SpringBootTest // 스프링과 통합 테스트
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    EntityManager em;

    /**
     * save, findOne, findAll, update, remove
     * findAllWithPage 추가 + findPageId
     */
    @Test
    public void 저장과조회() {
        Item item = Item.createItem("닉넴1", "12345", "제목임", "내용아무거나", "");
        Long itemId = itemService.save(item);

        em.flush(); // flush 없으면 db가 아니라 메모리에서 가져옴(영속성 컨텍스트에 보관되어 있기 때문) -> db 에서 가져오는걸 확인할 목적으로 추가
        Item findItem = itemService.findOne(itemId);
        Assertions.assertEquals(item, findItem);
        System.out.println(findItem.getContent());

        List<Item> findItems = itemService.findAllWithPage(2);
        for(int i = 0 ; i<findItems.size(); i++)
            System.out.println(findItems.get(i).getContent());
    }
    @Test
    public void 수정() {
        Item item = Item.createItem("닉넴1", "12345", "제목임", "내용아무거나", "");
        Long itemId = itemService.save(item);
        System.out.println(item.getContent()); // "내용아무거나"

        UpdateItemDto updateItemDto = new UpdateItemDto(itemId, "이름변경", "123", "제목변경", "내용수정", "");
        itemService.update(itemId, updateItemDto);
        // 조회로 item 가져왔고(영속성 컨텍스트) 자동 업데이트로 DB에 update 쿼리 전송됨 - 더티 체킹 (flush 를 해야 감지)
        // 쿼리 로그 확인 -> 로그가 안보이는데(로그찾으려다가 시간낭비 엄청했ㄴ..), getContent() 값은 잘 변경됨을 알 수 있음

        Assertions.assertEquals(item.getContent(), (updateItemDto.getContent()));
        System.out.println(item.getContent()); // "내용수정"
    }
    @Test
    public void 삭제() {
        Item item = itemService.findOne(1l);
        itemService.remove(item);
        Item findItem = itemService.findOne(1l); // 로그는 안보이지만 확실히 삭제 쿼리 전송
        System.out.println(findItem); // null
    }
}