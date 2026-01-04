package com.example.demoo.controllers;

import com.example.demoo.dto.LoginRequest;
import com.example.demoo.entities.userEntity;
import com.example.demoo.services.userService;
import com.example.demoo.utils.authUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    userService userService;


    @GetMapping()
    public ResponseEntity<userEntity> getUserFromEmail(){
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserfromEmail(email));
    }

}
