package com.dau.secretarttypinggallery.repository;


import com.dau.secretarttypinggallery.entity.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return em.createQuery("select i from Item i " +
                        "where i.id >= :itemId1 and " +
                        "i.id <= :itemId2", Item.class)
                .setParameter("itemId1", itemId-1)
                .setParameter("itemId2", itemId+1)
                .getResultList();
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
    
    public List<Item> findAllWithPage(int pageId) {
        return em.createQuery("select i from Item i", Item.class)
                .setFirstResult((pageId-1)*10)
                .setMaxResults(10) // 개수임!!
                .getResultList();
    }

    public int findPageId(Long itemId) {
        Long count = (Long) em.createQuery(
                        "SELECT COUNT(i) / 10 FROM Item i " +
                                "WHERE i.id <= :itemId", Long.class) // 반환 타입 지정
                .setParameter("itemId", itemId)
                .getSingleResult();
        int pageId = count.intValue()+1;
        return pageId;
    }

    public void remove(Item item) { em.remove(item); }

    public Long findTotalCount() {
        return em.createQuery("select count(i) from Item i", Long.class)
                .getSingleResult();
    }
}
