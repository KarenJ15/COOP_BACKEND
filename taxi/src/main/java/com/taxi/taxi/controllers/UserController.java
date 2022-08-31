package com.taxi.taxi.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.taxi.taxi.DTO.JwtDto;
import com.taxi.taxi.models.User;
import com.taxi.taxi.services.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;
    private final BCryptPasswordEncoder passwordEncoder;
  
    public UserController(UserService srv, BCryptPasswordEncoder pass){
        this.service =srv;
        this.passwordEncoder = pass;
    }
    
    @PostMapping()
    public ResponseEntity<String> create(@Valid @RequestBody User newUser){
        String passwordEncode = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(passwordEncode);        
        service.create(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");        
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody User user){
        
        return null;
    }
}
