package com.dau.secretarttypinggallery.service;


import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import com.dau.secretarttypinggallery.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기모드
@RequiredArgsConstructor // 생성자 주입 + 엔티티 매니저(서비스에서는 사용안하긴 함)
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * save, findOne, findAll, update, remove
     * findAllWithPage 추가 + findPageId
     */

    @Transactional // 쓰기모드 -> DB 저장위함
    public Long save(Item item) { itemRepository.save(item); return item.getId(); }

    public Item findOne(Long itemId) { return itemRepository.findOne(itemId); }

    // page 단위로(key) 캐시 기록 -> 참고 : value 로 꼭 캐시 영역을 지정해줘야 함
    @Cacheable(value = "posts", key = "#pageId") // [캐시 없으면 저장] 조회
    public List<Item> findAllWithPage(int pageId) {
        return itemRepository.findAllWithPage(pageId);
    }
    // page 단위로(key) 캐시 기록 -> 참고 : value 로 꼭 캐시 영역을 지정해줘야 함
    @CachePut(value = "posts", key = "#pageId") // [캐시에 데이터 있어도] 저장
    public List<Item> updateCachePage(int pageId) {
        // pageId 로 간단히 캐시 업데이트용 함수
        return itemRepository.findAllWithPage(pageId); // 반환값을 캐시에 기록하기 때문에 만든 함수
    }
    public int findPageId(Long itemId) { // 현제 페이징 찾는 함수
        return itemRepository.findPageId(itemId);
    }

    @Transactional // 쓰기모드 -> DB 저장위함
    public void update(Long itemId, UpdateItemDto updateItemDto) {
        // dirty checking
        Item item = itemRepository.findOne(itemId); // 영속성
        item.updateItem(updateItemDto); // 준영속(updateItemDto) -> 영속성
    }

    @Transactional // 쓰기모드 -> DB 삭제위함
    public void remove(Item item) { itemRepository.remove(item); }

    public Long findTotalCount() { return itemRepository.findTotalCount(); }
}
