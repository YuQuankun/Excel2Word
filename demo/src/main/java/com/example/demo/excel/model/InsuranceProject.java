package com.example.demo.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 投保项目
 * @date 2022/8/5 5:13 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceProject {

    /** 项目名称 */
    private String projectName;

    /** 资产类别 */
    private String assetClasses;

    /** 资产保险 */
    private String assetInsurance;

    /** 费率 */
    private Float rate;

    /** 保险金额 */
    private BigDecimal insuranceAmount;

    /** 保险起始时间 */
    private LocalDate startDate;

    /** 保险终止时间 */
    private LocalDate endDate;

    /** 保险天数 */
    private Integer insuranceDays;

    /** 保费 */
    private BigDecimal insurancePremium;
}
