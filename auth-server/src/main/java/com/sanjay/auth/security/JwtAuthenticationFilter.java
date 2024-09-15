package com.sanjay.auth.security;

import com.sanjay.auth.config.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTH_TOKEN_PREFIX = "Bearer ";
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        authenticateRequest(request);
        filterChain.doFilter(request, response);
    }

    private void authenticateRequest(HttpServletRequest request) {
        String requestToken = getRequestToken(request);

        if(requestToken != null) {
            String username = jwtService.getUsernameFromToken(requestToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            setUserAuthenticated(userDetails, request);
        }
    }

    public String getRequestToken(HttpServletRequest request) {
        String token = null;
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authorizationHeader != null && authorizationHeader.startsWith(AUTH_TOKEN_PREFIX)) {
            token = authorizationHeader.substring(AUTH_TOKEN_PREFIX.length());
        }

        return token;
    }

    private void setUserAuthenticated(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = createAuthentication(userDetails, request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UsernamePasswordAuthenticationToken createAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return token;
    }
}

