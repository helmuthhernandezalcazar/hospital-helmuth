package com.helmuth.hospital.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String dni;
    private String phoneNumber;
    private Date hospitalizationDate;
    private Date dischargeDate;
    private String symptoms;
    private String triage;
    private String medicalDiagnosis;

    @OneToOne()
    private Room room;

    @OneToMany(mappedBy = "patient")
    private List<Note> notes;
}
