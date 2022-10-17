package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.MedicalFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalFloorRepository extends JpaRepository<MedicalFloor, Long> {
}
