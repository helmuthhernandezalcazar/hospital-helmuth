package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
