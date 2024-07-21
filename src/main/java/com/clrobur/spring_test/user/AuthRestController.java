package com.clrobur.spring_test.user;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.clrobur.spring_test.user.dto.LoginRequestDto;
import com.clrobur.spring_test.user.entity.Role;
import com.clrobur.spring_test.user.entity.User;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @Deprecated
    public User login(@RequestBody LoginRequestDto loginRequest) {
    	System.out.println("LoginRequestDto: with" + loginRequest.getUsername());
        Optional<User> user = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (user.isPresent()) {
            Set<Role> roles = user.get().getRoles();
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.get().getUsername(), null, user.get().getRoles())
            );
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @GetMapping("/logout")
    @Deprecated
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
