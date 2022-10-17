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
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private Double measurementValue;
    private String unit;
    private Date date;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Employee employee;
}
