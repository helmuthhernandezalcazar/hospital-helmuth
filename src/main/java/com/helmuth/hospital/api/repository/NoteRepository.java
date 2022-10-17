package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
