package com.helmuth.hospital.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Column(unique = true)
    @Pattern(regexp = "[0-9]{8}[a-zA-Z]")
    private String dni;
    private String phoneNumber;
    private String email;
    private String employeeType;

    @OneToMany(mappedBy = "employee")
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "employee")
    private List<Note> notes;
}
