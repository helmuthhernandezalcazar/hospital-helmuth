package com.helmuth.hospital.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeResponseDto {
    private String firstName;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String email;
    private String employeeType;
}
