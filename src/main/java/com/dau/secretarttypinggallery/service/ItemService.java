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
    private final CacheManager cacheManager;

    /**
     * save, findOne, findAll, update, remove
     */

    @Transactional // 쓰기모드 -> DB 저장위함
    public void save(Item item) { itemRepository.save(item); }

    public Item findOne(Long itemId) { return itemRepository.findOne(itemId); }

    @Cacheable(value = "codeCache") // 캐시 없으면 기록 및 조회
    public List<Item> findAll() {
        log.info("codeCache Cacheable start");
        List<Item> items = itemRepository.findAll();
        log.info("codeCache Cacheable end");
        return items;
    }
    @CachePut(value = "codeCache") // 덮어씌우기
    public List<Item> findAllNoCache() {
        log.info("codeCache CachePut start");
        List<Item> items = itemRepository.findAll();
        log.info("codeCache CachePut end");
        return items;
    }


    @Transactional // 쓰기모드 -> DB 저장위함
    public Item update(Long id, UpdateItemDto updateItemDto) {
        // dirty checking
        Item item = findOne(id);
        item.updateItem(updateItemDto);
        return item;
    }
    public void remove(Item item) { itemRepository.remove(item); }
}
