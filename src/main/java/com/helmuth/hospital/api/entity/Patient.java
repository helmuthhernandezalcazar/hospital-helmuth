package com.helmuth.hospital.api.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    private Date hospitalizationDate;
    private Date dischargeDate;
    private String symptoms;
    private String medicalDiagnosis;

    @OneToOne(mappedBy = "patient")
    private Room room;
}
