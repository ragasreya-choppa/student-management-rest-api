//package com.example.springdemo.setup;
//
//import com.example.springdemo.entity.AppUser;
//import com.example.springdemo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class DevUserSeeder {
//
//    @Bean
//    @ConditionalOnProperty(name = "app.security.seed-users",havingValue = "true")
//    public ApplicationRunner seedUsers(
//            UserRepository userRepository,
//            PasswordEncoder passwordEncoder,
//            @Value("${APP_USER_PASSWORD}") String userPassword,
//            @Value("${APP_ADMIN_PASSWORD}") String adminPassword){
//
//        return args -> {
//            if(userRepository.findByUsername("sreya").isEmpty())
//            {
//                AppUser user = new AppUser("sreya",passwordEncoder.encode(userPassword),"USER");
//                userRepository.save(user);
//            }
//
//            if(userRepository.findByUsername("admin").isEmpty())
//            {
//                AppUser admin = new AppUser("admin" ,passwordEncoder.encode(adminPassword), "ADMIN");
//                userRepository.save(admin);
//            }
//
//            System.out.println("Development user seeding completed");
//        };
//    }
//}
