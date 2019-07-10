package com.winterrent.winterrent.service.authentication;

import com.winterrent.winterrent.dao.role.RoleDAO;
import com.winterrent.winterrent.dao.user.UserDAO;
import com.winterrent.winterrent.entity.Role;
import com.winterrent.winterrent.entity.RoleName;
import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.entity.UserProfile;
import com.winterrent.winterrent.rest.auth.payload.LoginRequest;
import com.winterrent.winterrent.rest.auth.payload.SignUpRequest;
import com.winterrent.winterrent.rest.exceptions.AppException;
import com.winterrent.winterrent.rest.exceptions.EmailAlreadyExists;
import com.winterrent.winterrent.rest.exceptions.UserAlreadyExists;
import com.winterrent.winterrent.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private AuthenticationManager authenticationManager;
    private UserDAO userDAO;
    private RoleDAO roleDAO;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationServiceImpl(AuthenticationManager theAuthenticationManager,
                              UserDAO theUserDAO,
                              RoleDAO theRoleDAO,
                              JwtTokenProvider theJwtTokenProvider,
                              PasswordEncoder thePasswordEncoder) {
        this.authenticationManager = theAuthenticationManager;
        this.userDAO = theUserDAO;
        this.roleDAO = theRoleDAO;
        this.tokenProvider = theJwtTokenProvider;
        this.passwordEncoder = thePasswordEncoder;
    }


    @Override
    public String getJWT(LoginRequest loginRequest) {
        LOGGER.info("Authenticating login request for: {}", loginRequest.getUsernameOrEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }

    @Override
    public User registerUser(SignUpRequest signUpRequest) {
        LOGGER.info("Registering new user: {}", signUpRequest.getUsername());

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

        return userDAO.save(user);
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
