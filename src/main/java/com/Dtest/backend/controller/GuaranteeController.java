package com.Dtest.backend.controller;

import com.Dtest.backend.model.Guarantee;
import com.Dtest.backend.service.GuaranteeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guarantees")
@RequiredArgsConstructor
public class GuaranteeController {

    private final GuaranteeService guaranteeService;

    // ฟังก์ชัน Get Guarantee ทั้งหมด
    @GetMapping
    public List<Guarantee> getAllGuarantees() {
        return guaranteeService.getAllGuarantees();
    }

    // ฟังก์ชัน Get Guarantee ตาม doctorCode
    @GetMapping("/doctor/{doctorCode}")
    public List<Guarantee> getGuaranteesByDoctorCode(@PathVariable String doctorCode) {
        return guaranteeService.getGuaranteesByDoctorCode(doctorCode);  // ค้นหาข้อมูล Guarantee ที่เชื่อมโยงกับ doctorCode
    }

    // ฟังก์ชันสร้าง Guarantee
    @PostMapping
    public ResponseEntity<Guarantee> createGuarantee(@RequestBody Guarantee guarantee) {
        Guarantee createdGuarantee = guaranteeService.createGuarantee(guarantee);
        return ResponseEntity.status(201).body(createdGuarantee);  // ส่งกลับ status 201 พร้อมข้อมูล Guarantee
    }

    // ฟังก์ชันอัปเดต Guarantee ตาม doctorCode
    @PutMapping("/{doctorCode}")
    public ResponseEntity<Guarantee> updateGuarantee(@PathVariable String doctorCode, @RequestBody Guarantee updatedGuarantee) {
        try {
            Guarantee guarantee = guaranteeService.updateGuarantee(doctorCode, updatedGuarantee);
            return ResponseEntity.ok(guarantee);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null);  // ส่งกลับ status 500 ถ้าเกิดข้อผิดพลาด
        }
    }

    // ฟังก์ชันลบ Guarantee ตาม doctorCode
    @DeleteMapping("/{doctorCode}")
    public ResponseEntity<String> deleteGuarantee(@PathVariable String doctorCode) {
        try {
            guaranteeService.deleteGuarantee(doctorCode);
            return ResponseEntity.ok("Guarantee with doctorCode " + doctorCode + " was deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("Failed to delete Guarantee for doctorCode: " + doctorCode);
        }
    }
}
