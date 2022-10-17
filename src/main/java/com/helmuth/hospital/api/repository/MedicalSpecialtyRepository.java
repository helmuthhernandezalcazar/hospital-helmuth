package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, Long> {
}
