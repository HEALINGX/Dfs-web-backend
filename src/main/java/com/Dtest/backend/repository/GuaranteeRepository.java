package com.Dtest.backend.repository;

import com.Dtest.backend.model.Guarantee;
import com.Dtest.backend.model.DoctorProfileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuaranteeRepository extends JpaRepository<Guarantee, Long> {

    // ค้นหาข้อมูล Guarantee ที่เชื่อมโยงกับ DoctorProfileDetail
    List<Guarantee> findByDoctorProfileDetail(DoctorProfileDetail doctorProfileDetail);

    // ค้นหาข้อมูล Guarantee ที่เชื่อมโยงกับ doctorCode
    List<Guarantee> findByDoctorProfileDetail_DoctorCode(String doctorCode);
}
