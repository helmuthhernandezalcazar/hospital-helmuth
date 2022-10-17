package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
