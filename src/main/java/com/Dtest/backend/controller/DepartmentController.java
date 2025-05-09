package com.Dtest.backend.controller;

import com.Dtest.backend.model.Department;
import com.Dtest.backend.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Create a new department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    // Update a department
    @PutMapping("/{departmentCode}")
    public ResponseEntity<Department> updateDepartment(@PathVariable String departmentCode, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(departmentCode, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Delete a department
    @DeleteMapping("/{departmentCode}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String departmentCode) {
        departmentService.deleteDepartment(departmentCode);
        return ResponseEntity.ok("Department with code " + departmentCode + " was deleted successfully.");
    }
}
