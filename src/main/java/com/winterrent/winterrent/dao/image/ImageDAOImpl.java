package com.winterrent.winterrent.dao.image;

import com.winterrent.winterrent.entity.Image;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class ImageDAOImpl implements ImageDAO {

    private EntityManager entityManager;

    @Autowired
    public ImageDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Image addImage(Image image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(image);
        return image;
    }

    @Override
    @Transactional
    public Optional<Image> findImageByItemId(int itemId) {
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession
                .createQuery("from Image as i where i.itemId=:itemId")
                .setParameter("itemId", itemId)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    @Transactional
    public Optional<Image> findImageById(int imageId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Image image = currentSession.get(Image.class, imageId);
        return Optional.ofNullable(image);
    }

    @Override
    @Transactional
    public void deleteImage(Image image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(image);
    }

    @Override
    @Transactional
    public Image updateItem(Image image) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(image);
        return image;
    }
}
