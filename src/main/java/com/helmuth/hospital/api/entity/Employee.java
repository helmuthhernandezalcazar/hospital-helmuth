package com.helmuth.hospital.api.entity;

import lombok.*;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String dni;
    private String email;
    private String employeeType;

    @OneToMany(mappedBy = "employee")
    private List<Measurement> measurements;

    @OneToMany(mappedBy = "employee")
    private List<Note> notes;
}
