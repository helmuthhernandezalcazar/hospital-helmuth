package com.helmuth.hospital.api.initialize;

import com.helmuth.hospital.api.entity.BuildingBlock;
import com.helmuth.hospital.api.entity.MedicalFloor;
import com.helmuth.hospital.api.entity.MedicalSpecialty;
import com.helmuth.hospital.api.entity.Room;
import com.helmuth.hospital.api.repository.BuildingBlockRepository;
import com.helmuth.hospital.api.repository.MedicalFloorRepository;
import com.helmuth.hospital.api.repository.MedicalSpecialtyRepository;
import com.helmuth.hospital.api.repository.RoomRepository;
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
    @Autowired
    private RoomRepository roomRepository;
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

        Room room101 = Room.builder().name("101").medicalFloor(medicalFloor1A).build();
        Room room102 = Room.builder().name("102").medicalFloor(medicalFloor1A).build();
        Room room103 = Room.builder().name("103").medicalFloor(medicalFloor1A).build();
        Room room104 = Room.builder().name("104").medicalFloor(medicalFloor1A).build();

        Room room201 = Room.builder().name("201").medicalFloor(medicalFloor2A).build();
        Room room202 = Room.builder().name("202").medicalFloor(medicalFloor2A).build();
        Room room203 = Room.builder().name("203").medicalFloor(medicalFloor2A).build();
        Room room204 = Room.builder().name("204").medicalFloor(medicalFloor2A).build();

        Room room105 = Room.builder().name("105").medicalFloor(medicalFloor1B).build();
        Room room106 = Room.builder().name("106").medicalFloor(medicalFloor1B).build();
        Room room107 = Room.builder().name("107").medicalFloor(medicalFloor1B).build();
        Room room108 = Room.builder().name("108").medicalFloor(medicalFloor1B).build();

        Room room205 = Room.builder().name("205").medicalFloor(medicalFloor2B).build();
        Room room206 = Room.builder().name("206").medicalFloor(medicalFloor2B).build();
        Room room207 = Room.builder().name("207").medicalFloor(medicalFloor2B).build();
        Room room208 = Room.builder().name("208").medicalFloor(medicalFloor2B).build();
        roomRepository.saveAll(List.of(room101, room102, room103, room104,
                room201, room202, room203, room204,
                room105, room106, room107, room108,
                room205, room206, room207, room208));

    }
}
