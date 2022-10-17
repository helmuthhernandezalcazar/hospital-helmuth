package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.BuildingBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingBlockRepository extends JpaRepository<BuildingBlock, Long> {
}
