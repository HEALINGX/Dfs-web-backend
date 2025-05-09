package com.Dtest.backend.controller;

import com.Dtest.backend.model.DoctorProfileDetail;
import com.Dtest.backend.service.DoctorProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorProfileController {

    private final DoctorProfileService doctorProfileService;

    // Get all doctors
    @GetMapping
    public List<DoctorProfileDetail> getAllDoctors() {
        return doctorProfileService.getAllDoctors();
    }

    // Create a doctor profile
    @PostMapping
    public DoctorProfileDetail createDoctor(@RequestBody DoctorProfileDetail doctor) {
        return doctorProfileService.createDoctor(doctor);
    }

    @GetMapping("/{doctorCode}")
    public ResponseEntity<DoctorProfileDetail> getDoctorById(@PathVariable String doctorCode) {
        DoctorProfileDetail doctor = doctorProfileService.getDoctorById(doctorCode);
        return ResponseEntity.ok(doctor);
    }


    // Update doctor profile
    @PutMapping("/{doctorCode}")
    public ResponseEntity<DoctorProfileDetail> updateDoctor(@PathVariable String doctorCode, @RequestBody DoctorProfileDetail doctorProfileDetail) {
        DoctorProfileDetail updatedDoctor = doctorProfileService.updateDoctor(doctorCode, doctorProfileDetail);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Delete doctor profile
    @DeleteMapping("/{doctorCode}")
    public ResponseEntity<String> deleteDoctor(@PathVariable String doctorCode) {
        doctorProfileService.deleteDoctor(doctorCode);
        return ResponseEntity.ok("Doctor with code " + doctorCode + " was deleted successfully.");
    }
}
