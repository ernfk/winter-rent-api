package com.winterrent.winterrent.dao.role;

import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleName;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class RoleDAOHibernateImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public Optional<Role> findByName(RoleName name) {
        Session currentSession = entityManager.unwrap(Session.class);
        Role role = currentSession.get(Role.class, name);
        return Optional.ofNullable(role);
    }
}
