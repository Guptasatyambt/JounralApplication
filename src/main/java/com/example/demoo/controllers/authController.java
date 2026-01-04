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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/auth")
public class authController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private authUtils jwtUtil;
    @Autowired
    userService userService;

    @PostMapping
    public ResponseEntity<userEntity> createUser(@RequestBody userEntity newUser){
        userEntity newUser1 = userService.createNewUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser1);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            String token = jwtUtil.generateToken(request.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getClass().getName() + " : " + e.getMessage());
        }

    }
}
