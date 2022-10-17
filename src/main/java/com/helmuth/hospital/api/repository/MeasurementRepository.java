package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
