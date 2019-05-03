package com.winterrent.winterrent.dao.user;

import com.winterrent.winterrent.entity.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOHibernateImpl implements UserDAO{

    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOHibernateImpl.class);

    @Autowired
    public UserDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from User u where u.email = :email OR u.username = :username");
        query.setParameter("email", email);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        Session currentSession = entityManager.unwrap(Session.class);
        User user = currentSession.get(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public boolean existsByUsername(String username) {
        LOGGER.info("Checking if user already exists with username: {}", username);
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from User u where u.username = :username");
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        return !userList.isEmpty();
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        LOGGER.info("Checking if user already exists with email: {}", email);
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        List<User> userList = query.getResultList();
        return !userList.isEmpty();
    }

    @Override
    @Transactional
    public User save(User user) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
        return user;
    }
}
