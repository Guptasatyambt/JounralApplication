package com.example.demoo.services;

import com.example.demoo.entities.userEntity;
import com.example.demoo.exceptions.UserNotFound;
import com.example.demoo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserDetailsService {
    @Autowired
    userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
