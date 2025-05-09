package com.Dtest.backend.repository;

import com.Dtest.backend.model.DoctorProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfileDetail, String> {

    Optional<DoctorProfileDetail> findByDoctorCode(String doctorCode);  // ใช้ findByDoctorCode แทน findByCode
}
