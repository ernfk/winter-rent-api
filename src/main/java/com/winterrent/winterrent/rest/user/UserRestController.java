package com.winterrent.winterrent.rest.user;

import com.winterrent.winterrent.entity.UserProfile;
import com.winterrent.winterrent.service.user.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private CustomUserDetailsService userDetailsService;

    @Autowired
    UserRestController(CustomUserDetailsService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @GetMapping("/profile/{username}")
        //TODO: PreAuthorized
    UserProfile getUserProfileByUsername(@PathVariable String username) {
        LOGGER.info("Getting user profile by username: {}", username);
        return userDetailsService.getUserProfileByUsername(username);
    }

}
