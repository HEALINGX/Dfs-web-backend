package com.Dtest.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Guarantee")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guarantee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private Long id;

    @JsonProperty("IS_LUMP_SUM")
    private String isLumpSum;

    @JsonProperty("GUARANTEE_TYPE_CODE")
    private String guaranteeTypeCode;

    @JsonProperty("GUARANTEE_METHOD")
    private String guaranteeMethod;

    @JsonProperty("CONTRACT_START_DATE")
    private LocalDate contractStartDate;

    @JsonProperty("CONTRACT_EXPIRE_DATE")
    private LocalDate contractExpireDate;

    @JsonProperty("GUARANTEE_AMOUNT_HOUR")
    private Double guaranteeAmountHour;

    @JsonProperty("EXTRA_HOUR")
    private Double extraHour;

    @JsonProperty("IN_GUARANTEE_ALLOCATE")
    private Double inGuaranteeAllocate;

    @JsonProperty("OVER_GUARANTEE_ALLOCATE")
    private Double overGuaranteeAllocate;

    @JsonProperty("INCLUDE_REVENUE_TO_DR")
    private String includeRevenueToDr;

    @JsonProperty("DEPARTMENT")
    private String department;

    @JsonProperty("REVENUE_FOR_GUARANTEE")
    private Double revenueForGuarantee;

    @JsonProperty("GUARANTEE_BY_DAY")
    private Double guaranteeByDay;

    @JsonProperty("ABSORD_EXTRA_TAX_TYPE")
    private String absorbExtraTaxType;

    @JsonProperty("GUARANTEE_CATEGORY")
    private String guaranteeCategory;

    @JsonProperty("EARLY_TIME")
    private String earlyTime;

    @JsonProperty("LATE_TIME")
    private String lateTime;

    @JsonProperty("ACTIVE")
    private Boolean active;

    // กำหนดความสัมพันธ์ One-to-Many กับ DoctorProfileDetail
    @ManyToOne
    @JoinColumn(name = "DOCTOR_CODE", referencedColumnName = "DOCTOR_CODE", insertable = false, updatable = false)
    private DoctorProfileDetail doctorProfileDetail;
}
