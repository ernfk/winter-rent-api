package com.winterrent.winterrent.rest.auth;

import com.winterrent.winterrent.dao.role.RoleDAO;
import com.winterrent.winterrent.dao.user.UserDAO;
import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleName;
import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.entity.UserProfile;
import com.winterrent.winterrent.rest.auth.payload.ApiResponse;
import com.winterrent.winterrent.rest.auth.payload.JwtAuthenticationResponse;
import com.winterrent.winterrent.rest.auth.payload.LoginRequest;
import com.winterrent.winterrent.rest.auth.payload.SignUpRequest;
import com.winterrent.winterrent.rest.exceptions.AppException;
import com.winterrent.winterrent.security.JwtTokenProvider;
import com.winterrent.winterrent.rest.exceptions.EmailAlreadyExists;
import com.winterrent.winterrent.rest.exceptions.UserAlreadyExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDAO userDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userDAO.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExists("Username already in use!");
        }

        if (userDAO.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyExists("Email address already in use!");
        }

        User user = createUser(signUpRequest);

        Role userRole = roleDAO
                .findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userDAO.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    private User createUser(SignUpRequest request) {
        LOGGER.info("Creating new user");

        UserProfile userProfile = createUserProfile();
        userProfile.setName(request.getName());

        User user = new User();
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setUserProfile(userProfile);

        userProfile.setUser(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return user;
    }

    private UserProfile createUserProfile() {
        LOGGER.info("Creating user profile");

        UserProfile userProfile = new UserProfile();
        userProfile.setLastName("");
        userProfile.setCity("");
        userProfile.setStreet("");
        userProfile.setPhoneNo(0);
        userProfile.setPostalCode("");
        userProfile.setFlatNo(0);

        return userProfile;
    }
}