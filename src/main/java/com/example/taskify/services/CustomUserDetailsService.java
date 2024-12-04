package com.example.taskify.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class    CustomUserDetailsService implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();

    public CustomUserDetailsService() {
        users.put("admin", "ROLE_ADMIN");
        users.put("user", "ROLE_USER");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String role = users.get(username);
        if (role == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(username)
                .password("{noop}password") // Для теста используется простой пароль
                .roles(role.replace("ROLE_", ""))
                .build();
    }
}
