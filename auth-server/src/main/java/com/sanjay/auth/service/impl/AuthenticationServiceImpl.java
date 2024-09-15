package com.sanjay.auth.service.impl;

import com.sanjay.auth.config.jwt.JwtService;
import com.sanjay.auth.dto.AuthTokenDto;
import com.sanjay.auth.dto.AuthenticationRequestDto;
import com.sanjay.auth.dto.UserDto;
import com.sanjay.auth.entity.User;
import com.sanjay.auth.entity.UserRole;
import com.sanjay.auth.repository.AuthRepository;
import com.sanjay.auth.repository.UserRoleRepository;
import com.sanjay.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sanjay.auth.common.Role;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    @Override
    public AuthTokenDto authenticateUser(AuthenticationRequestDto authenticationRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword())
        );

        String token = jwtService.createToken(authenticationRequestDto.getEmail());
        return new AuthTokenDto(token);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = populatedUserEntity(new User(), userDto);
        authRepository.save(user);
        return userEntityToUserDto(user);
    }

    private UserDto userEntityToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setEnglishName(user.getEnglishName());
        userDto.setTelephone(user.getTelephone());
        userDto.setAddress(user.getAddress());
        userDto.setRole(user.getRole());
        return userDto;
    }

    private User populatedUserEntity(User user, UserDto userDto) {
        user.setEmail(userDto.getEmail());

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        user.setEnglishName(userDto.getEnglishName());
        user.setTelephone(userDto.getTelephone());
        user.setAddress(userDto.getAddress());

        UserRole studentRole = userRoleRepository.findByRoleName(userDto.getRole().getRoleName().name().equals("ROLE_USER")?Role.ROLE_USER : Role.ROLE_ADMIN);
        user.setRole(studentRole);
        return user;
    }
}
