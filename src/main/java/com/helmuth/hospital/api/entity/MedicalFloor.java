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
                name = "medicalFloor_buildingBlockAndFloor_uniqueConstraint",
                columnNames = { "building_block_id", "floor" }
        )
)
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
