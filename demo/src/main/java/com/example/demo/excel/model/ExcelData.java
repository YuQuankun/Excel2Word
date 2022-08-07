package com.example.demo.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description Excel表格所有需要的数据
 * @date 2022/8/5 4:54 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExcelData {

    /** 投保人名称 */
    private String applicantName;

    /** 所属机构二级单位名称 */
    private String secondaryUnitsName;

    /** 发票信息 */
    private InvoiceInformation invoiceInformation;

    /** 邮寄地址 */
    private MailingAddress mailingAddress;

    /** 单位名称 */
    private String unitName;

    /** 单位地址 */
    private String unitAddress;

    /** 投保信息 */
    private InsuranceInformation insuranceInformation;

    /** 投保日期 */
    private LocalDate insuranceDate;
}
