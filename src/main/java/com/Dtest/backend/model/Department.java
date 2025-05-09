package com.Dtest.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

     @Id
     @Column(name = "DEPARTMENT_CODE")
     @JsonProperty("DEPARTMENT_CODE")
     private String departmentCode;

     @JsonProperty("DEPARTMENT_DESC")
     private String departmentDesc;

     @JsonProperty("IS_ACTIVE")
     private boolean isActive;

     // เชื่อมโยงกับ DoctorProfileDetail (Many-to-Many)
     @ManyToMany(mappedBy = "departments")
     @JsonProperty("DOCTORS")
     private List<DoctorProfileDetail> doctors;
}
