package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

public class BCryptPasswordEncoderTest
{
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void testCorrectPassword(){
        String rawPassword = "Password123";
        String hashPassword = passwordEncoder.encode(rawPassword);
        assertTrue(passwordEncoder.matches("Password123",hashPassword));
    }

    @Test
    void testIncorrectPassword(){

        String rawPassword = "Password123";
        String hashPassword = passwordEncoder.encode(rawPassword);
        assertFalse(passwordEncoder.matches("Password456",hashPassword));
    }

    @Test
    void testDifferentSalts(){
        String password = "Password123";
        String hash1 = passwordEncoder.encode(password);
        String hash2 = passwordEncoder.encode(password);

        assertNotEquals(hash1,hash2);

        assertTrue(passwordEncoder.matches(password,hash1));
        assertTrue(passwordEncoder.matches(password,hash2));

    }
}
