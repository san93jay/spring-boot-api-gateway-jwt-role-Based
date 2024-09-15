package com.sanjay.auth.controller;

import com.sanjay.auth.dto.AuthTokenDto;
import com.sanjay.auth.dto.AuthenticationRequestDto;
import com.sanjay.auth.dto.UserDto;
import com.sanjay.auth.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticateUser;


    @PostMapping("/login")
    public AuthTokenDto getLogin(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
    return authenticateUser.authenticateUser(authenticationRequestDto);
    }

    @PostMapping(path = "/register")
    public UserDto registerUser(@Valid @RequestBody UserDto userDto) {
        return authenticateUser.registerUser(userDto);
    }
}
