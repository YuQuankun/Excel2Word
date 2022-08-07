package com.example.demo.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 邮寄地址
 * @date 2022/8/5 5:04 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailingAddress {

    /** 邮寄联系人 */
    private String mailContacts;

    /** 邮寄电话 */
    private String mailPhone;

    /** 地址 */
    private String mailAddress;

    /** 接收邮箱 */
    private String mailNumber;
}
