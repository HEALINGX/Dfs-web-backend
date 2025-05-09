package com.Dtest.backend.service;

import com.Dtest.backend.model.Department;
import com.Dtest.backend.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Update an existing department
    public Department updateDepartment(String departmentCode, Department departmentDetails) {
        Department existingDepartment = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with departmentCode: " + departmentCode));

        existingDepartment.setDepartmentDesc(departmentDetails.getDepartmentDesc());
        existingDepartment.setActive(departmentDetails.isActive());

        return departmentRepository.save(existingDepartment);
    }

    // Delete a department by departmentCode
    @Transactional
    public void deleteDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found with departmentCode: " + departmentCode));

        departmentRepository.delete(department);
    }
}
