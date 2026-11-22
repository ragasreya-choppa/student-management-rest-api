package com.example.springdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map;

@RestController
public class AuthController {
    @GetMapping("/auth/me")
    public Map<String,Object> getCurrentUser(Authentication authentication){

        List<String> authorities = authentication.getAuthorities()
                .stream()
                .map(authority-> authority.getAuthority())
                .toList();


        return Map.of(
                "username",authentication.getName(),
                "authenticated", authentication.isAuthenticated(),
                "authorities", authorities
        );
    }
}
