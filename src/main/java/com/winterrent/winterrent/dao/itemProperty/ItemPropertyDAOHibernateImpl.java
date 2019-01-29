package com.winterrent.winterrent.dao.itemProperty;

import com.winterrent.winterrent.entity.ItemProperty;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemPropertyDAOHibernateImpl implements ItemPropertyDAO {

    private EntityManager entityManager;

    @Autowired
    public ItemPropertyDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<ItemProperty> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemProperty> theQuery =
                currentSession.createQuery("from ItemProperty", ItemProperty.class);
        List<ItemProperty> itemProperties = theQuery.getResultList();
        return itemProperties;
    }
}
