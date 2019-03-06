package com.winterrent.winterrent.rest.user;

import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService theUserService) {
        this.userService = theUserService;
    }

    @GetMapping("/users")
    List<User> findAll() {
        return this.userService.findAll();
    }
}
