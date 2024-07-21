package com.clrobur.spring_test;


import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clrobur.spring_test.user.UserRepository;
import com.clrobur.spring_test.user.UserService;
import com.clrobur.spring_test.user.entity.Role;
import com.clrobur.spring_test.user.entity.User;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(UserRepository userRepository, UserService userService) {
        return args -> {

            // 사용자 초기화
            if (userRepository.findByUsername("admin1").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin1");
                admin.setPassword("admin1234");
                admin.setEmail("admin1@example.com");
                admin.setRoles(Set.of(Role.ROLE_ADMIN));  // 역할 설정


                userService.save(admin);
            }

            if (userRepository.findByUsername("user1").isEmpty()) {
                User user = new User();
                user.setUsername("user1");
                user.setPassword("user1234");
                user.setEmail("user1@example.com");
                user.setRoles(Set.of(Role.ROLE_USER));  // 역할 설정

                userService.save(user);
            }
        };
    }
}