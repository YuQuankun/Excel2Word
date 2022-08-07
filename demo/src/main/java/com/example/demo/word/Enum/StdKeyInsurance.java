package com.example.demo.word.Enum;

import lombok.Getter;

/**
 * 投保人信息Key值
 * @author kun_mi
 */
@Getter
public enum StdKeyInsurance {

    INSURANCE("insurance_name","投保人名称"),//此处需要截取字符串，去掉”有限公司“后缀

    INSURANCE_FULL("insurance_name_full","投保人名称全称"),

    INSURANCE_NUMBER("insurance_number","保单号"),

    SECONDARY_COMPANY_NAME("secondary_company_name","所属机构二级单位名称"),

    ISSUE_NAME("issue_name","开票名称"),

    TAX_IDENTIFY_NUM("tax_identify_num","纳税人识别号"),

    TAX_ADDRESS("tax_address","纳税人地址"),

    TAX_PHONE("tax_phone","纳税人电话"),

    TAX_BANK("tax_bank","开户行"),

    TAX_ACCOUNT("tax_account","开户账户"),

    DELI_PERSON("deli_person","邮寄联系人"),

    DELI_NUMBER("deli_phone","邮寄电话"),

    DELI_ADDRESS("deli_address","邮寄地址"),

    DELI_EMAIL("deli_email","邮寄邮箱")
    ;
    private String keyName;

    private String value;

    StdKeyInsurance(String keyName, String value){
        this.keyName = keyName;
        this.value = value;
    }
}
