package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
@CrossOrigin("*")
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Waiting room
    Page<Patient> findByRoomNullAndDischargeDateNull(Pageable pageable);

    // Hospitalized
    Page<Patient> findByRoomNotNull(Pageable pageable);

    //  Discharged
    Page<Patient> findByDischargeDateNotNull(Pageable pageable);

    List<Patient> findByDni(String dni);

}
