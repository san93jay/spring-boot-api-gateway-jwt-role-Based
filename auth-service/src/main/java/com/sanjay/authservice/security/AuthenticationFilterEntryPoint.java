package com.sanjay.authservice.security;

import com.sanjay.authservice.config.JwtUtilService;
import com.sanjay.authservice.util.Constants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthenticationFilterEntryPoint extends OncePerRequestFilter {

    private final JwtUtilService jwtService;

    public AuthenticationFilterEntryPoint(JwtUtilService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
     try{
         authenticateRequest(request);
         filterChain.doFilter(request, response);

     }catch (Exception e){
         e.fillInStackTrace();
     }
    }

    private void authenticateRequest(HttpServletRequest request) {
        String requestJwtToken = getRequestToken(request);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(requestJwtToken!=null){
            String userName=jwtService.getUsernameFromToken(requestJwtToken);
            UserDetails userDetails = createUserWithAuthority(grantedAuthorities, requestJwtToken, userName);
            setUserAuthenticated(userDetails, request);
        }
    }

    private String getRequestToken(HttpServletRequest request) {
        String token = null;
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith(Constants.KWTDetails.AUTH_TOKEN_PREFIX)) {
            token = authorizationHeader.substring(Constants.KWTDetails.AUTH_TOKEN_PREFIX.length());
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

    private User createUserWithAuthority(List<GrantedAuthority> grantedAuthorities, String token, String userId) {
       /* String role = jwtService.getUserRole(token);
        if(role.equalsIgnoreCase("1")) {
            grantedAuthorities.add(new SimpleGrantedAuthority("INIT_USER"));
        } else if(role.equalsIgnoreCase("2")){
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }*/
        return new User(userId, "", grantedAuthorities);
    }
}
