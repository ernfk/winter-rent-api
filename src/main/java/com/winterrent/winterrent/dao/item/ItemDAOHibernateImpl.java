package com.winterrent.winterrent.dao.item;

import com.winterrent.winterrent.entity.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemDAOHibernateImpl implements ItemDAO {

    private EntityManager entityManager;

    @Autowired
    public ItemDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<Item> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Item> theQuery = currentSession.createQuery("from Item", Item.class);
        List<Item> items = theQuery.getResultList();
        return items;
    }

    @Override
    public Item addItem(Item item) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(item);
        return item;
    }
}
