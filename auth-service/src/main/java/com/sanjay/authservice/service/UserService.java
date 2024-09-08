package com.sanjay.authservice.service;

import com.sanjay.authservice.dto.AuthTokenDto;
import com.sanjay.authservice.dto.LoginRequestDto;

public interface UserService {
    AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto);
}
