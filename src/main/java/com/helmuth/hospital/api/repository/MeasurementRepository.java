package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.model.Measurement;
import com.helmuth.hospital.api.model.MeasurementType;
import com.helmuth.hospital.api.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    Page<Measurement> findByPatient(Pageable pageable, Patient patient);
    Page<Measurement> findByPatientAndMeasurementType(Pageable pageable, Patient patient, MeasurementType measurementType);
}
