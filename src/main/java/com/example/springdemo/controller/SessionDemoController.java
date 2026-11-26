package com.example.springdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class SessionDemoController {

    @GetMapping("/auth/session/info")
    public Map<String, Object> getSessionInfo(HttpServletRequest request, Authentication authentication){

        HttpSession session = request.getSession(false);

        Map<String, Object> response = new LinkedHashMap<>();

        response.put("username",authentication.getName());

        response.put("authenticated",authentication.isAuthenticated());

        response.put("sessionExists", session!=null);

        return response;
    }
}
