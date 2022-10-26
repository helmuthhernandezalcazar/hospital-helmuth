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
public class Triage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int level;
    private String name;
    private String timeToBeAttended;
    private String description;
    private String examples;

    @OneToMany(mappedBy = "triage")
    private List<Patient> patients;
}
