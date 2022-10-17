package com.helmuth.hospital.api.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class MedicalFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int floor;

    @ManyToOne()
    private BuildingBlock buildingBlock;

    @ManyToOne()
    private MedicalSpecialty medicalSpecialty;
}
