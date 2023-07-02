package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("*")
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByPatientNull();
}
