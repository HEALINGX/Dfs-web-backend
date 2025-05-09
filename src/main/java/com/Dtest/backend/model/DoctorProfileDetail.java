package com.Dtest.backend.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DoctorProfileDetail")
public class DoctorProfileDetail {

    @Id
    @Column(name = "DOCTOR_CODE")
    @JsonProperty("DOCTOR_CODE")
    private String doctorCode;

    @JsonProperty("DOCTOR_PROFILE_CODE")
    private String doctorProfileCode;

    @JsonProperty("HOSPITAL_CODE")
    private String hospitalCode;

    @JsonProperty("DESCRIPTION")
    private String description;

    @JsonProperty("RESIGN")
    private String resign;

    @JsonProperty("NAME_ENG")
    private String nameEng;

    @JsonProperty("NAME_THAI")
    private String nameThai;

    @JsonProperty("PERSON")
    private String person;

    @JsonProperty("ACTIVE")
    private Boolean active;

    @JsonProperty("ADDRESS_NUMBER")
    private String addressNumber;

    @JsonProperty("DISTRICT")
    private String district;

    @JsonProperty("PROVINCE")
    private String province;

    @JsonProperty("POSTAL_CODE")
    private String postalCode;

    @JsonProperty("EMAIL")
    private String email;

    @JsonProperty("LICENSE_ID")
    private String licenseId;

    @JsonProperty("SALARY")
    private String salary;

    @JsonProperty("LICENSE_ISSUE_DATE")
    private String licenseIssueDate;

    @JsonProperty("START_WORKING_DATE")
    private String startWorkingDate;

    @JsonProperty("DOCTOR_TYPE")
    private String doctorType;

    @JsonProperty("DOCTOR_GROUP")
    private String doctorGroup;

    @JsonProperty("DOCTOR_CATEGORY")
    private String doctorCategory;

    @JsonProperty("HOSPITAL_UNIT")
    private String hospitalUnit;

    @JsonProperty("SPECIALTY")
    private String specialty;

    @JsonProperty("SPECIALTY_DESC")
    private String specialtyDesc;

    @JsonProperty("PAYMENT_MODE")
    private String paymentMode;

    @JsonProperty("PAYMENT_REVENUE_CODE")
    private String paymentRevenueCode;

    @JsonProperty("ADVANCE_PAYMENT")
    private String advancePayment;

    @JsonProperty("TIME_TO_PAYMENT")
    private String timeToPayment;

    @JsonProperty("TAX_ID")
    private String taxId;

    @JsonProperty("PAT_TAX_BY")
    private String patTaxBy;

    @JsonProperty("INCLUDE_40_2_REVENUE_TO_CODE")
    private String include402RevenueToCode;

    @JsonProperty("TAX_40_2_CALCULATION")
    private String tax402Calculation;

    @JsonProperty("INCLUDE_40_6_REVENUE_TO_CODE")
    private String include406RevenueToCode;

    @JsonProperty("TAX_40_6_CALCULATION")
    private String tax406Calculation;

    @ManyToMany
    @JoinTable(
            name = "doctor_department",
            joinColumns = @JoinColumn(name = "DOCTOR_CODE", referencedColumnName = "DOCTOR_CODE"),
            inverseJoinColumns = @JoinColumn(name = "DEPARTMENT_CODE", referencedColumnName = "DEPARTMENT_CODE")
    )
    @JsonProperty("DEPARTMENTS")
    private List<Department> departments;

    @OneToMany(mappedBy = "doctorProfileDetail")
    private List<Guarantee> guarantees;

}
