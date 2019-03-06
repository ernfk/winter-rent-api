package com.winterrent.winterrent.service.user;

import com.winterrent.winterrent.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    User save(User user);
    List<User> findAll();
}
