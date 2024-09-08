package com.sanjay.authservice.serviceImpl;

import com.sanjay.authservice.config.JwtUtilService;
import com.sanjay.authservice.dto.AuthTokenDto;
import com.sanjay.authservice.dto.LoginRequestDto;
import com.sanjay.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtUtilService jwtUtilService;
    @Override
    public AuthTokenDto authenticateUser(LoginRequestDto loginRequestDto) {
      /*  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword())
        );*/
        String token = jwtUtilService.createToken(loginRequestDto.getEmail());
        return new AuthTokenDto(token);
    }
}
