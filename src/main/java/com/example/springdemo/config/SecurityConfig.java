package com.example.springdemo.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder,
                                                 @Value("${app.security.user-password}") String userPassword,
                                                 @Value("${app.security.admin-password}")String adminPassword)
    {
        UserDetails regularUser = User.builder()
                .username("sreya")
                .password(passwordEncoder.encode(userPassword))
                .roles("USER")
                .build();

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode(adminPassword))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(regularUser, adminUser);

    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http
                .csrf(csrf-> csrf.ignoringRequestMatchers("/students","/students/**"))

                .authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET,"/students","/students/*").permitAll()

                        .requestMatchers(HttpMethod.POST,"/students").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT,"/students/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE,"/students/*").hasRole("ADMIN")

                        .requestMatchers("/auth/me").authenticated()

                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
