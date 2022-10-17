package com.helmuth.hospital.api.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "Room_nameAndMedicalFloor_uniqueConstraint",
                columnNames = {  "name", "medical_floor_id" }
        ))
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToOne
    private MedicalFloor medicalFloor;
}
