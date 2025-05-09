package com.Dtest.backend.repository;

import com.Dtest.backend.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    Optional<Department> findByDepartmentCode(String departmentCode);  // ค้นหาจาก DepartmentCode
}
