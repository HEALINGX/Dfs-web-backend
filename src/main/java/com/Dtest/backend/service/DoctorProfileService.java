package com.Dtest.backend.service;

import com.Dtest.backend.model.DoctorProfileDetail;
import com.Dtest.backend.model.Department;
import com.Dtest.backend.repository.DoctorProfileRepository;
import com.Dtest.backend.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorProfileService {

    private final DoctorProfileRepository doctorProfileRepository;
    private final DepartmentRepository departmentRepository; // เพิ่มตัวแปรสำหรับ DepartmentRepository

    private static final Logger logger = LoggerFactory.getLogger(DoctorProfileService.class);

    // Get all doctors
    public List<DoctorProfileDetail> getAllDoctors() {
        return doctorProfileRepository.findAll();
    }

    // Create a doctor profile
    public DoctorProfileDetail createDoctor(DoctorProfileDetail doctor) {
        // เชื่อมโยง Department ที่ส่งมาด้วย DoctorProfileDetail
        List<Department> departments = new ArrayList<>();
        for (Department department : doctor.getDepartments()) {
            // ค้นหา Department ในฐานข้อมูล
            Department existingDepartment = departmentRepository.findByDepartmentCode(department.getDepartmentCode())
                    .orElseThrow(() -> new RuntimeException("Department not found with departmentCode: " + department.getDepartmentCode()));
            departments.add(existingDepartment);
        }

        // ตั้งค่าฟิลด์ departments ใน doctor
        doctor.setDepartments(departments);

        logger.info("Inserting doctor into the database: {}", doctor);
        return doctorProfileRepository.save(doctor);
    }

    // Delete doctor by doctorCode
    @Transactional
    public void deleteDoctor(String doctorCode) {
        DoctorProfileDetail doctor = doctorProfileRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found with doctorCode: " + doctorCode));

        logger.info("Deleting doctor with doctorCode: {}", doctorCode);
        doctorProfileRepository.delete(doctor);
    }

    // Update doctor profile by doctorCode
    public DoctorProfileDetail updateDoctor(String doctorCode, DoctorProfileDetail doctorProfileDetail) {
        DoctorProfileDetail existingDoctor = doctorProfileRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found with doctorCode: " + doctorCode));

        // ตรวจสอบการเชื่อมโยงกับ Department
        List<Department> departments = new ArrayList<>();
        for (Department department : doctorProfileDetail.getDepartments()) {
            Department existingDepartment = departmentRepository.findByDepartmentCode(department.getDepartmentCode())
                    .orElseThrow(() -> new RuntimeException("Department not found with departmentCode: " + department.getDepartmentCode()));
            departments.add(existingDepartment);
        }
        existingDoctor.setDepartments(departments);

        // อัปเดตฟิลด์อื่นๆ
        existingDoctor.setDescription(doctorProfileDetail.getDescription());
        // (เพิ่มฟิลด์อื่นๆ ที่ต้องการอัปเดต)

        return doctorProfileRepository.save(existingDoctor);
    }

    // ฟังก์ชันดึงข้อมูล DoctorProfileDetail โดยใช้ doctorCode
    public DoctorProfileDetail getDoctorById(String doctorCode) {
        return doctorProfileRepository.findByDoctorCode(doctorCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found with doctorCode: " + doctorCode));
    }

}
