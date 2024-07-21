package com.clrobur.spring_test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화
                .authorizeRequests(requests -> requests
                        .antMatchers("/public/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/users").permitAll() // 추가된 부분
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/swagger-ui.html", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .permitAll());
    }
}
