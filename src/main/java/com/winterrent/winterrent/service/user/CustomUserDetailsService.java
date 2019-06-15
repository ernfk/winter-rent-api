package com.winterrent.winterrent.service.user;

import com.winterrent.winterrent.dao.user.UserDAO;
import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.entity.UserProfile;
import com.winterrent.winterrent.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userDAO.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        return UserPrincipal.create(user);
    }

    // For JWTAuthenticationFilter
    public UserDetails loadUserById(Long id) {
        User user = userDAO.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));

        return UserPrincipal.create(user);
    }

    public UserProfile getUserProfileByUserId(Long userId) {
        Optional<UserProfile> opt = this.userDAO.getUserProfileByUserId(userId);

        return opt.orElse(null);
    }
}
