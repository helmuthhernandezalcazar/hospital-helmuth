package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.MedicalFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface MedicalFloorRepository extends JpaRepository<MedicalFloor, Long> {
}
