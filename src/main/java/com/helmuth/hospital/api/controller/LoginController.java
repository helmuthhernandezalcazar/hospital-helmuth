package com.helmuth.hospital.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
public class LoginController {

    @PostMapping("/login")

    ResponseEntity<String> login(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/login")

    ResponseEntity<String> loginget(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
