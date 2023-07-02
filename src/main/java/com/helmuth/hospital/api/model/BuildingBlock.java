package com.helmuth.hospital.api.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class BuildingBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private int numberOfFloors;
    private String city;
    private String address;

    @OneToMany(mappedBy = "buildingBlock", cascade = CascadeType.ALL)
    private List<MedicalFloor> medicalFloors;
}
