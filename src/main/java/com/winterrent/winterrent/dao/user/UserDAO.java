package com.winterrent.winterrent.dao.user;

import com.winterrent.winterrent.entity.User;

import java.util.List;

public interface UserDAO {
    User findByEmail(String email);
    User save(User user);
    List<User> findAll();
}
