package com.winterrent.winterrent.service.authentication;

import com.winterrent.winterrent.entity.User;
import com.winterrent.winterrent.rest.auth.payload.LoginRequest;
import com.winterrent.winterrent.rest.auth.payload.SignUpRequest;

public interface AuthenticationService {
    String getJWT(LoginRequest loginRequest);
    User registerUser(SignUpRequest signUpRequest);
}
