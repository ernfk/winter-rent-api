package com.winterrent.winterrent.dao.item;

import com.winterrent.winterrent.entity.Item;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public Optional<Item> findItem(int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Item item = currentSession.get(Item.class, itemId);
        return Optional.ofNullable(item);
    }

    @Override
    @Transactional
    public void deleteItem(int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query theQuery = currentSession.createQuery("delete from Item as it where it.id=:itemId");
        theQuery.setParameter("itemId", itemId);
        theQuery.executeUpdate();
    }

    @Override
    @Transactional
    public Item saveOrUpdate(Item item) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(item);
        return item;
    }
}
