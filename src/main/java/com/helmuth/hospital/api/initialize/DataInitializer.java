package com.helmuth.hospital.api.initialize;

import com.helmuth.hospital.api.entity.BuildingBlock;
import com.helmuth.hospital.api.entity.MedicalFloor;
import com.helmuth.hospital.api.entity.MedicalSpecialty;
import com.helmuth.hospital.api.repository.BuildingBlockRepository;
import com.helmuth.hospital.api.repository.MedicalFloorRepository;
import com.helmuth.hospital.api.repository.MedicalSpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BuildingBlockRepository buildingBlockRepository;
    @Autowired
    private MedicalSpecialtyRepository medicalSpecialtyRepository;
    @Autowired
    private MedicalFloorRepository medicalFloorRepository;
    @Override
    public void run(String... args) throws Exception {
        MedicalSpecialty urology = MedicalSpecialty.builder().name("Urología").build();
        MedicalSpecialty surgery = MedicalSpecialty.builder().name("Cirugía").build();
        MedicalSpecialty dermatology = MedicalSpecialty.builder().name("Dermatología").build();
        MedicalSpecialty neurology = MedicalSpecialty.builder().name("Neurología").build();
        medicalSpecialtyRepository.saveAll(List.of(urology, surgery, dermatology, neurology));

        BuildingBlock buildingBlockA = BuildingBlock.builder().name("A").address("Calle de Alan Turing 1").numberOfFloors(3).build();
        BuildingBlock buildingBlockB = BuildingBlock.builder().name("B").address("Calle de Alan Turing 3").numberOfFloors(3).build();
        buildingBlockRepository.saveAll(List.of(buildingBlockA, buildingBlockB));

        MedicalFloor medicalFloor1A = MedicalFloor.builder().name("1A").floor(1).buildingBlock(buildingBlockA).medicalSpecialty(urology).build();
        MedicalFloor medicalFloor2A = MedicalFloor.builder().name("2A").floor(2).buildingBlock(buildingBlockA).medicalSpecialty(surgery).build();
        MedicalFloor medicalFloor1B = MedicalFloor.builder().name("1B").floor(1).buildingBlock(buildingBlockB).medicalSpecialty(dermatology).build();
        MedicalFloor medicalFloor2B = MedicalFloor.builder().name("2B").floor(2).buildingBlock(buildingBlockB).medicalSpecialty(neurology).build();
        medicalFloorRepository.saveAll(List.of(medicalFloor1A, medicalFloor2A, medicalFloor1B, medicalFloor2B));

    }
}
