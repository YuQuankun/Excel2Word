package com.example.demo.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 发票信息
 * @date 2022/8/5 5:00 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceInformation {

    /** 开票名称 */
    private String invoiceName;

    /** 纳税人识别号 */
    private String taxpayerNumber;

    /** 地址电话 */
    private String addressAndPhone;

    /** 开户行 */
    private String openingBank;

    /** 开户账号 */
    private String openingAccount;
}
