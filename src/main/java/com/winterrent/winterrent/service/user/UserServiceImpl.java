package com.winterrent.winterrent.service.user;

import com.winterrent.winterrent.dao.role.RoleDAO;
import com.winterrent.winterrent.dao.user.UserDAO;
import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleType;
import com.winterrent.winterrent.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;
    private RoleDAO roleDAO;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO theUserDAO, RoleDAO theRoleDAO
//                           ,BCryptPasswordEncoder theBCryptPasswordEncoder
    ) {
        this.userDAO = theUserDAO;
        this.roleDAO = theRoleDAO;
//        this.bCryptPasswordEncoder = theBCryptPasswordEncoder;
    }

    @Override
    public User findByEmail(String email) {
        LOGGER.info("Finding user by email: {}", email);
        return userDAO.findByEmail(email);
    }

    @Override
    public User save(User user) {
        LOGGER.info("Saving user");
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleDAO.findByRole(RoleType.ADMIN);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userDAO.save(user);
    }

    @Override
    public List<User> findAll() {
        LOGGER.info("Find all users");
        return this.userDAO.findAll();
    }


}
