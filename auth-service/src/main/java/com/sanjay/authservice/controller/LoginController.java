package com.sanjay.authservice.controller;

import com.sanjay.authservice.dto.AuthTokenDto;
import com.sanjay.authservice.dto.LoginRequestDto;
import com.sanjay.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanjay Vishwakarma
 */
@RestController
@RequestMapping("/v/auth")
public class LoginController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public AuthTokenDto getAuth(LoginRequestDto loginRequestDto){
        return userService.authenticateUser(loginRequestDto);
    }
}
