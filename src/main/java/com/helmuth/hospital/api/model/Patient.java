package com.helmuth.hospital.api.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    private String dni;
    private String phoneNumber;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private Date registerDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private Date dischargeDate;
    private Boolean discharged;
    private String symptoms;
    private String medicalDiagnosis;

    @ManyToOne
    private Triage triage;
    @OneToOne
    private Room room;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<Note> notes;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<Measurement> measurements;
}
