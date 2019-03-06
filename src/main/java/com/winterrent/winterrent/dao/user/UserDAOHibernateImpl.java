package com.winterrent.winterrent.dao.user;

import com.winterrent.winterrent.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOHibernateImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User AS u where u.email = email", User.class);
        theQuery.setParameter("email", email);
        return theQuery.getSingleResult();
    }

    @Override
    @Transactional
    public User save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<User> theQuery = currentSession.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }
}
