package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.model.Note;
import com.helmuth.hospital.api.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findByPatient(Pageable pageable, Patient patient);
}
