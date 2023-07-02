package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.model.BuildingBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface BuildingBlockRepository extends JpaRepository<BuildingBlock, Long> {
}
