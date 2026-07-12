package com.cognizant.Auth.controller;

import com.cognizant.authsso.dto.TokenResponse;
import com.cognizant.authsso.jwt.JwtTokenProvider;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/auth/token")
    public TokenResponse token(@RequestParam(defaultValue = "cognizant-user") String username) {
        return new TokenResponse(username, "Bearer", jwtTokenProvider.createToken(username));
    }

    @GetMapping("/jwt/secure")
    public Map<String, Object> jwtSecure(Authentication authentication) {
        return Map.of(
                "exercise", "Exercise 3: Using JSON Web Tokens (JWT) for Secure Communication",
                "message", "JWT authentication successful",
                "user", authentication.getName(),
                "authenticated", authentication.isAuthenticated()
        );
    }
}
