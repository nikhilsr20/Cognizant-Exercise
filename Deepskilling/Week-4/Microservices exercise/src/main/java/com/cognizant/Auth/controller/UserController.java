package com.cognizant.Auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/user-info")
    public Map<String, Object> userInfo(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("exercise", "Exercise 1: Implementing Centralized Authentication with OAuth 2.1/OIDC");
        response.put("name", authentication.getName());
        response.put("authenticated", authentication.isAuthenticated());
        response.put("authorities", authentication.getAuthorities());
        if (authentication.getPrincipal() instanceof OidcUser oidcUser) {
            response.put("claims", oidcUser.getClaims());
        }
        return response;
    }
}
