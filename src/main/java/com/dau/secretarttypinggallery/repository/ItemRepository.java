package com.dau.secretarttypinggallery.repository;


import com.dau.secretarttypinggallery.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor // 생성자 주입 + 엔티티매니저 주입
public class ItemRepository {
    private final EntityManager em;

    /**
     * save, findOne, findAll, update(entity 에서 해결), remove
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

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public void remove(Item item) { em.remove(item); }
}
