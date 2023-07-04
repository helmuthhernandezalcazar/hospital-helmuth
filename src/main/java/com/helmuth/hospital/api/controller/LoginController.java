package com.helmuth.hospital.api.controller;

import com.helmuth.hospital.api.dto.EmployeeResponseDto;
import com.helmuth.hospital.api.model.Employee;
import com.helmuth.hospital.api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
public class LoginController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/login")
    ResponseEntity<EmployeeResponseDto> loginget(Authentication authentication){
        Employee employee =  employeeRepository.findByEmail(authentication.getName());
        return ResponseEntity.status(HttpStatus.OK).body(this.mapToResponse(employee));
    }

    EmployeeResponseDto mapToResponse(Employee employee){
        return EmployeeResponseDto.builder()
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .dni(employee.getDni())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .employeeType(employee.getEmployeeType())
                .build();
    }
}
