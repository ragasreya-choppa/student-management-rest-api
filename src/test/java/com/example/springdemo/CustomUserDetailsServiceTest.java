package com.example.springdemo;

import com.example.springdemo.entity.AppUser;
import com.example.springdemo.repository.UserRepository;
import com.example.springdemo.service.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    void testLoadExistingUser() {

        AppUser appUser = new AppUser(
                "sreya",
                "stored-bcrypt-hash",
                "USER"
        );

        when(userRepository.findByUsername("sreya"))
                .thenReturn(Optional.of(appUser));

        UserDetails result =
                userDetailsService.loadUserByUsername("sreya");

        assertEquals("sreya", result.getUsername());

        assertEquals("stored-bcrypt-hash", result.getPassword());

        assertTrue(result.getAuthorities()
                .stream()
                .anyMatch(authority ->
                        authority.getAuthority().equals("ROLE_USER")));

        verify(userRepository).findByUsername("sreya");
    }

    @Test
    void testUserNotFound() {

        when(userRepository.findByUsername("unknown"))
                .thenReturn(Optional.empty());

        assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("unknown")
        );
    }

    @Test
    void testDisabledUser() {

        AppUser appUser = new AppUser(
                "sreya",
                "stored-bcrypt-hash",
                "USER"
        );

        appUser.setEnabled(false);

        when(userRepository.findByUsername("sreya"))
                .thenReturn(Optional.of(appUser));

        UserDetails result =
                userDetailsService.loadUserByUsername("sreya");

        assertFalse(result.isEnabled());
    }
}
