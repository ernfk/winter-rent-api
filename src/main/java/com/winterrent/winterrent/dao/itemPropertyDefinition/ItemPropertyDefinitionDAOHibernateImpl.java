package com.winterrent.winterrent.dao.itemPropertyDefinition;

import com.winterrent.winterrent.entity.ItemPropertyDefinition;
import com.winterrent.winterrent.entity.ItemType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemPropertyDefinitionDAOHibernateImpl implements ItemPropertyDefinitionDAO {

    private EntityManager entityManager;

    @Autowired
    public ItemPropertyDefinitionDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public List<ItemPropertyDefinition> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemPropertyDefinition> theQuery =
                currentSession.createQuery("from ItemPropertyDefinition", ItemPropertyDefinition.class);
        List<ItemPropertyDefinition> itemPropertyDefinitions = theQuery.getResultList();
        return itemPropertyDefinitions;
    }

    @Override
    @Transactional
    public List<ItemPropertyDefinition> findByItemTypeId(ItemType itemType) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<ItemPropertyDefinition> theQuery = currentSession.createQuery("from ItemPropertyDefinition AS ipf where ipf.itemType=:itemType", ItemPropertyDefinition.class);
        theQuery.setParameter("itemType", itemType);
        List<ItemPropertyDefinition> itemPropertyDefinitions = theQuery.getResultList();
        return itemPropertyDefinitions;
    }
}
