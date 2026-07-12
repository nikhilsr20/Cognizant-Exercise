package com.cognizant.Auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "application", "Centralized Authentication and SSO Exercises",
                "packageName", "com.cognizant.authsso",
                "status", "running"
        );
    }

    @GetMapping("/public")
    public Map<String, String> publicEndpoint() {
        return Map.of("message", "This endpoint is public");
    }
}
