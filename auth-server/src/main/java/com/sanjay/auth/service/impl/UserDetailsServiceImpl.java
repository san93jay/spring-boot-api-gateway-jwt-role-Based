package com.sanjay.auth.service.impl;

import com.sanjay.auth.entity.User;
import com.sanjay.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email);
        List<GrantedAuthority> authorities = getUserAuthorities(user);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true, true,
                true, authorities);
    }

    private User findUserByEmail(String email) {
        return authRepository.findUserByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("No user found with email: " + email);
        });
    }

    private List<GrantedAuthority> getUserAuthorities(User user) {
        return List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name()));
    }
}
