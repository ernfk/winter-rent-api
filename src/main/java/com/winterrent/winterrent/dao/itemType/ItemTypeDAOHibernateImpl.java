package com.winterrent.winterrent.dao.itemType;

import com.winterrent.winterrent.entity.ItemType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemTypeDAOHibernateImpl implements ItemTypeDAO {

    private EntityManager entityManager;

    @Autowired
    public ItemTypeDAOHibernateImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<ItemType> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemType> theQuery = currentSession.createQuery("from ItemType", ItemType.class);
        List<ItemType> itemTypes = theQuery.getResultList();
        return itemTypes;
    }
}
