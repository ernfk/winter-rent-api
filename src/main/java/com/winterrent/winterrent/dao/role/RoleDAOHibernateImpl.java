package com.winterrent.winterrent.dao.role;

import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleDAOHibernateImpl implements RoleDAO {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOHibernateImpl(EntityManager theEntityManager) {
        this.entityManager = theEntityManager;
    }

    @Override
    public Role findByRole(RoleType roleType) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Role> theQuery = currentSession.createQuery("from Role AS r where r.roleType=:roleType", Role.class);
        theQuery.setParameter("roleType", roleType);
        return theQuery.getSingleResult();
    }
}
