package com.example.springdemo.practice;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptDemo {
    public static void main(String[]args){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "Password123";
        String hashedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("BCrypt hash: "+ hashedPassword);

        boolean correctPassword = passwordEncoder.matches("Password123",hashedPassword);

        boolean wrongPassword = passwordEncoder.matches("Password456",hashedPassword);

        System.out.println("Correct Password Matches: " + correctPassword);

        System.out.println("Wrong Password Matches: " + wrongPassword);

    }
}
