package com.dau.secretarttypinggallery.repository;


import com.dau.secretarttypinggallery.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor // 생성자 주입 + 엔티티매니저 주입
public class ItemRepository {
    private final EntityManager em;

    /**
     * save, findOne, findAll, update(entity 에서 해결), remove
     * findAllWithPage 추가 + findPageId
     * 총 게시물 수 구하는 함수도 추가
     */
    public void save(Item item) {
        // null 인 경우 db에 없다는 의미(db에 insert 할 때 id 생성하기 때문)
        if(item.getId() == null) {
            em.persist(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }


    public List<Item> findThree(Long itemId) {
        // 이전, 이후 전시실 버튼 생성용
        Item item = em.find(Item.class, itemId);
        Long No = item.getNo();
        return em.createQuery("select i from Item i " +
                        "where i.No >= :itemNo1 and " +
                        "i.No <= :itemNo2", Item.class)
                .setParameter("itemNo1", No-1)
                .setParameter("itemNo2", No+1)
                .getResultList();
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
    
    public List<Item> findAllWithPage(int pageId) {
        return em.createQuery("select i from Item i" +
                        " order by i.id desc", Item.class)
                .setFirstResult((pageId-1)*10)
                .setMaxResults(10) // 개수임!!
                .getResultList();
    }

    public int findPageId(Long itemId) {
        Long count = (Long) em.createQuery(
                        "SELECT COUNT(i) / 10 FROM Item i " +
                                "WHERE i.id >= :itemId", Long.class) // 반환 타입 지정
                .setParameter("itemId", itemId)
                .getSingleResult();
        int pageId = count.intValue()+1;
        return pageId;
    }

    /*
    ALTER TABLE `tb_board_item` AUTO_INCREMENT=1; SET @COUNT = 0; UPDATE `tb_board_item` SET board_item_key = @COUNT:=@COUNT+1;
    em.createNativeQuery("alter table Item AUTO_INCREMENT=1").executeUpdate();
    em.createNativeQuery("set @COUNT = 0").executeUpdate();
    em.createNativeQuery("update Item set item_id = @COUNT:=@COUNT+1").executeUpdate();
     */
    public List<Item> findAllWithNoPage(int pageId) {
        List<Item> items = em.createQuery("select i from Item i" +
                        " order by i.id desc", Item.class)
                .setFirstResult((pageId-1)*10)
                .setMaxResults(10) // 개수임!!
                .getResultList();

        int start = (pageId-1)*10;
        int end = start + items.size();
        for(Item item : items) {
            if(item.getNo()!=null && item.getNo() == end) {
                end--;
                continue;
            }
            else {
                item.setNo((long)end);
                end--;
            }
        }
        return items;
    }


    public void remove(Item item) {
        em.remove(item);
    }

    public Long findTotalCount() {
        return em.createQuery("select count(i) from Item i", Long.class)
                .getSingleResult();
    }
}
