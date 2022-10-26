package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
