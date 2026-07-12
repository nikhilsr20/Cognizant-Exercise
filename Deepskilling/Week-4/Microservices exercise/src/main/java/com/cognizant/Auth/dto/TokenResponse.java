package com.cognizant.Auth.dto;

public record TokenResponse(String username, String tokenType, String accessToken) {
}
