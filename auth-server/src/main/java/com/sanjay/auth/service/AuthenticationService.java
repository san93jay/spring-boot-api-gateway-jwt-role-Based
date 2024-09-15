package com.sanjay.auth.service;

import com.sanjay.auth.dto.AuthTokenDto;
import com.sanjay.auth.dto.AuthenticationRequestDto;
import com.sanjay.auth.dto.UserDto;

public interface AuthenticationService {
    AuthTokenDto authenticateUser(AuthenticationRequestDto authenticationRequestDto);

    UserDto registerUser(UserDto userDto);
}
