package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.MeasurementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementTypeRepository extends JpaRepository<MeasurementType, Long> {
}
