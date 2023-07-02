package com.helmuth.hospital.api.controller;

import com.helmuth.hospital.api.model.Employee;
import com.helmuth.hospital.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class LoginController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/login")
    ResponseEntity<Employee> loginget(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findByEmail(authentication.getName()));
    }
}
