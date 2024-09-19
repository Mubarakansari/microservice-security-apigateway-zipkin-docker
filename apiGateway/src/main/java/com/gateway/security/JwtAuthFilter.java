package com.gateway.security;

import com.gateway.exception.GenericException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JwtAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new GenericException("Missing token from header");
                }
                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    System.out.println("validateToken>>" + authHeader);
                    jwtTokenUtil.validateToken(authHeader);
                } catch (SignatureException e) {
                    throw new GenericException(e.getMessage());
                } catch (ExpiredJwtException e) {
                    throw new GenericException(e.getMessage());
                } catch (UnsupportedJwtException e) {
                    throw new GenericException(e.getMessage());
                } catch (MalformedJwtException e) {
                    throw new GenericException(e.getMessage());
                } catch (Exception e) {
                    throw new GenericException(e.getMessage());
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config {

    }
}
