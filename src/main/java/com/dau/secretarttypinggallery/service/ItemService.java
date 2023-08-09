package com.dau.secretarttypinggallery.service;


import com.dau.secretarttypinggallery.entity.Item;
import com.dau.secretarttypinggallery.entity.dto.UpdateItemDto;
import com.dau.secretarttypinggallery.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true) // 읽기모드
@RequiredArgsConstructor // 생성자 주입 + 엔티티 매니저(서비스에서는 사용안하긴 함)
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * save, findOne, findAll, update, remove
     */

    @Transactional // 쓰기모드 -> DB 저장위함
    public void save(Item item) { itemRepository.save(item); }

    public Item findOne(Long itemId) { return itemRepository.findOne(itemId); }

    public List<Item> findAll() { return itemRepository.findAll(); }

    @Transactional // 쓰기모드 -> DB 저장위함
    public Item update(Long id, UpdateItemDto updateItemDto) {
        // dirty checking
        Item item = findOne(id);
        item.updateItem(updateItemDto);
        return item;
    }
    public void remove(Item item) { itemRepository.remove(item); }
}
