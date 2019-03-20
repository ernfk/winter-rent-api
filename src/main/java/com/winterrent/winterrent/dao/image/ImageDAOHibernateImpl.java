package com.winterrent.winterrent.dao.image;

import com.winterrent.winterrent.entity.Image;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class ImageDAOHibernateImpl implements ImageDAO {

    private EntityManager entityManager;

    @Autowired
    public ImageDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Image addImage(Image image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(image);
        return image;
    }
}
