package com.sanjay.dashboardgatway.filter;

import com.sanjay.dashboardgatway.config.GatewayAuthJWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class APIGatewayAuthenticationFilter extends AbstractGatewayFilterFactory<APIGatewayAuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private GatewayAuthJWTUtil gatewayAuthJWTUtil;

    public static final String AUTH_TOKEN_PREFIX = "Bearer ";


    public APIGatewayAuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange,chain)->{
            if(routeValidator.isSecured.test(exchange.getRequest())){
                authenticateRequest(exchange.getRequest());

                //header contains JWT
       /*         if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Authorization header missed");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
        try{
                gatewayAuthJWTUtil.validateToken(authHeader);

            } catch (Exception e) {
                System.out.println("invalid access...!");
                throw new RuntimeException("un-authorized access to application");
            }*/
        }
            return chain.filter(exchange);
        });
    }

    private void authenticateRequest(ServerHttpRequest request) {
        String requestToken = getRequestToken(request);

        if(requestToken != null) {
            try{
                gatewayAuthJWTUtil.validateToken(requestToken);

            } catch (Exception e) {
                System.out.println("invalid access...!");
                throw new RuntimeException("un-authorized access to application");
            }
          /*  gatewayAuthJWTUtil.validateuToken(requestToken);
            *//*String username = jwtService.getUsernameFromToken(requestToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);*//*
            setUserAuthenticated(userDetails, request);*/
        }
    }

    private String getRequestToken(ServerHttpRequest request) {

        String token = null;
        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        if(authorizationHeader != null && authorizationHeader.startsWith(AUTH_TOKEN_PREFIX)) {
            token = authorizationHeader.substring(AUTH_TOKEN_PREFIX.length());
        }
        return token;
    }

    public static class Config{

    }
}
