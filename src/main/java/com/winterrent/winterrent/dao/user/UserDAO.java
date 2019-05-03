package com.winterrent.winterrent.dao.user;

import com.winterrent.winterrent.entity.User;

import java.util.Optional;

public interface UserDAO {
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User save(User user);
}
