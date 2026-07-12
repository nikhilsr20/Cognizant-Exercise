package com.cognizant.Auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${spring.security.jwt.secret}")
    private String secret;

    @Value("${spring.security.jwt.validity-in-ms}")
    private long validityInMs;

    public String getSecret() {
        return secret;
    }

    public long getValidityInMs() {
        return validityInMs;
    }
}
