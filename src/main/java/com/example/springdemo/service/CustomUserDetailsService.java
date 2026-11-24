package com.example.springdemo.service;

import com.example.springdemo.entity.AppUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.springdemo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("DEBUG: Searching for user: " + username);

        AppUser appUser = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Not Found"));

        System.out.println("DEBUG: User found: " + appUser.getUsername());

        System.out.println("DEBUG: Role: " + appUser.getRole());

        System.out.println("DEBUG: Enabled: " + appUser.isEnabled());

        return User.builder().username(appUser.getUsername())
                .password(appUser.getPasswordHash())
                .roles(appUser.getRole())
                .disabled(!appUser.isEnabled())
                .build();


    }
}
