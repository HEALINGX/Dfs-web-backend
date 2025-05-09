package com.Dtest.backend.service;

import com.Dtest.backend.model.Guarantee;
import com.Dtest.backend.model.DoctorProfileDetail;
import com.Dtest.backend.repository.GuaranteeRepository;
import com.Dtest.backend.repository.DoctorProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuaranteeService {

    private final GuaranteeRepository guaranteeRepository;
    private final DoctorProfileRepository doctorProfileRepository;

    // ฟังก์ชันการสร้าง Guarantee
    public Guarantee createGuarantee(Guarantee guarantee) {
        // ค้นหาข้อมูล DoctorProfileDetail โดยใช้ doctorCode จาก Guarantee
        DoctorProfileDetail doctorProfileDetail = doctorProfileRepository.findByDoctorCode(guarantee.getDoctorProfileDetail().getDoctorCode())
                .orElseThrow(() -> new RuntimeException("Doctor not found with doctorCode: " + guarantee.getDoctorProfileDetail().getDoctorCode()));

        // เชื่อมโยง Guarantee กับ DoctorProfileDetail
        guarantee.setDoctorProfileDetail(doctorProfileDetail);

        return guaranteeRepository.save(guarantee);  // บันทึกข้อมูล Guarantee
    }

    // ฟังก์ชันการดึงข้อมูลทั้งหมดของ Guarantee
    public List<Guarantee> getAllGuarantees() {
        return guaranteeRepository.findAll();
    }

    // ฟังก์ชันการดึงข้อมูล Guarantee ตาม doctorCode
    public List<Guarantee> getGuaranteesByDoctorCode(String doctorCode) {
        return guaranteeRepository.findByDoctorProfileDetail_DoctorCode(doctorCode);  // ค้นหาข้อมูล Guarantee ที่เชื่อมโยงกับ doctorCode
    }

    // ฟังก์ชันการอัปเดต Guarantee ตาม doctorCode
    public Guarantee updateGuarantee(String doctorCode, Guarantee updatedGuarantee) {
        List<Guarantee> guarantees = guaranteeRepository.findByDoctorProfileDetail_DoctorCode(doctorCode);

        if (guarantees.isEmpty()) {
            throw new RuntimeException("Guarantee not found for doctorCode: " + doctorCode);
        }

        Guarantee existingGuarantee = guarantees.get(0);

        existingGuarantee.setIsLumpSum(updatedGuarantee.getIsLumpSum());
        existingGuarantee.setGuaranteeMethod(updatedGuarantee.getGuaranteeMethod());
        existingGuarantee.setContractStartDate(updatedGuarantee.getContractStartDate());
        existingGuarantee.setContractExpireDate(updatedGuarantee.getContractExpireDate());
        existingGuarantee.setGuaranteeAmountHour(updatedGuarantee.getGuaranteeAmountHour());
        existingGuarantee.setExtraHour(updatedGuarantee.getExtraHour());
        existingGuarantee.setInGuaranteeAllocate(updatedGuarantee.getInGuaranteeAllocate());
        existingGuarantee.setOverGuaranteeAllocate(updatedGuarantee.getOverGuaranteeAllocate());
        existingGuarantee.setIncludeRevenueToDr(updatedGuarantee.getIncludeRevenueToDr());
        existingGuarantee.setDepartment(updatedGuarantee.getDepartment());
        existingGuarantee.setRevenueForGuarantee(updatedGuarantee.getRevenueForGuarantee());
        existingGuarantee.setGuaranteeByDay(updatedGuarantee.getGuaranteeByDay());
        existingGuarantee.setAbsorbExtraTaxType(updatedGuarantee.getAbsorbExtraTaxType());
        existingGuarantee.setGuaranteeCategory(updatedGuarantee.getGuaranteeCategory());
        existingGuarantee.setEarlyTime(updatedGuarantee.getEarlyTime());
        existingGuarantee.setLateTime(updatedGuarantee.getLateTime());
        existingGuarantee.setActive(updatedGuarantee.getActive());

        return guaranteeRepository.save(existingGuarantee);
    }

    // ฟังก์ชันการลบ Guarantee ตาม doctorCode
    public void deleteGuarantee(String doctorCode) {
        List<Guarantee> guarantees = guaranteeRepository.findByDoctorProfileDetail_DoctorCode(doctorCode);

        if (guarantees.isEmpty()) {
            throw new RuntimeException("No guarantee found for doctorCode: " + doctorCode);
        }

        guaranteeRepository.delete(guarantees.get(0));  // ลบตัวแรกที่พบ
    }
}
