package com.winterrent.winterrent.rest.user;

import com.winterrent.winterrent.entity.UserProfile;
import com.winterrent.winterrent.service.user.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);
    private CustomUserDetailsService userDetailsService;

    @Autowired
    UserRestController(CustomUserDetailsService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;
    }


    @GetMapping("/profile/{username}")
        //TODO: PreAuthorized
    UserProfile getUserProfileByUsername(@PathVariable String username) {
        LOGGER.info("Getting user profile by username: {}", username);
        return userDetailsService.getUserProfileByUsername(username);
    }

    @PutMapping("/profile/{username}")
    // TODO: Preauthorized
    UserProfile updateUserProfile(@RequestBody UserProfile userProfile, @PathVariable String username ) {
        LOGGER.info("Updating user profile for: {}", username);
        return userDetailsService.updateUserProfile(userProfile, username);
    }

    @GetMapping("/profile/{username}/role")
    public boolean isUserAdmin(@PathVariable String username) {
        LOGGER.info("Checking if user: {} is admin", username);
        return userDetailsService.isUserAdmin(username);
    }
}
