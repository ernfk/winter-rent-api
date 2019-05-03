package com.winterrent.winterrent.dao.role;

import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleName;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class RoleDAOHibernateImpl implements RoleDAO {

    private EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDAOHibernateImpl.class);

    @Autowired
    public RoleDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public Optional<Role> findByName(RoleName name) {
        LOGGER.info("Getting role by name: {}", name);
        Session currentSession = entityManager.unwrap(Session.class);
        Query query = currentSession.createQuery("from Role r where r.name = :name");
        query.setParameter("name", name);
        Role role = (Role) query.getSingleResult();
        return Optional.ofNullable(role);
    }
}
