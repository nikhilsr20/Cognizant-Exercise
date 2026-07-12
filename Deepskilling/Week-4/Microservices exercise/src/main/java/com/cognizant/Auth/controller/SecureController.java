package com.cognizant.Auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecureController {

    @GetMapping("/secure")
    public Map<String, Object> secure(Authentication authentication) {
        return Map.of(
                "exercise", "Exercise 2: Configuring Authorization Servers and Resource Servers",
                "message", "This is a secure endpoint",
                "user", authentication.getName(),
                "authenticated", authentication.isAuthenticated()
        );
    }
}
