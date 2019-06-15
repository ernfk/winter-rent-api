package com.winterrent.winterrent.dao.user;

import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.entity.UserProfile;
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
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);

    @Autowired
    public UserDAOImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public Optional<User> findByUsernameOrEmail(String username, String email) {
        LOGGER.info("Finding user by username or email: {}", username);
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from User u where u.email = :email OR u.username = :username");
        query.setParameter("email", email);
        query.setParameter("username", username);
        List<User> userList = query.getResultList();
        return userList.isEmpty() ? Optional.empty() : Optional.ofNullable(userList.get(0));
    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        LOGGER.info("Finding user by id: {}", id);
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
        LOGGER.info("Saving new user.");
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(user);
        return user;
    }

    @Override
    @Transactional
    public Optional<UserProfile> getUserProfileByUserId(long userId) {
        LOGGER.info("Getting user profile by id:{}", userId);
        Session currentSession = entityManager.unwrap(Session.class);
        return currentSession
                .createQuery("from UserProfile as up where up.user_id=:userId")
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();

    }
}
