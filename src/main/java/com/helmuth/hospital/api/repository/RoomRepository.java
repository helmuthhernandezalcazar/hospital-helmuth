package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin("*")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
