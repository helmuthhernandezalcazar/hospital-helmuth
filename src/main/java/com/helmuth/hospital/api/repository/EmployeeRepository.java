package com.helmuth.hospital.api.repository;

import com.helmuth.hospital.api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
