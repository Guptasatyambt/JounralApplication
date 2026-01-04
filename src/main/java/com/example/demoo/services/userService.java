package com.example.demoo.services;

import com.example.demoo.entities.userEntity;
import com.example.demoo.exceptions.UserNotFound;
import com.example.demoo.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    userRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<userEntity> getAllUser(){
        return userRepository.findAll();
    }

    public userEntity getUserfromEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFound("User not found with email: " + email));
    }

    public userEntity createNewUser(userEntity newUser){
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

}
