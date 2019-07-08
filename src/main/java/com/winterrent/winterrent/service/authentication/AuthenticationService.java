package com.winterrent.winterrent.service.authentication;

import com.winterrent.winterrent.rest.auth.payload.LoginRequest;

public interface AuthenticationService {
    String getJWT(LoginRequest loginRequest);
}
