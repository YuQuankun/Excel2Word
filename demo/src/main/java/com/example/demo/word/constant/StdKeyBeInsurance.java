package com.example.demo.word.constant;

import lombok.Getter;

/**
 * 被保险人信息Key值
 * @author kun_mi
 */
@Getter
public enum StdKeyBeInsurance {

    BE_INSURANCE_NAME("be_insurance_name","被保险人名称"),
    BE_INSURANCE_ADDRESS("be_insurance_address","被保险人地址");
    private String keyName;

    private String value;

    StdKeyBeInsurance(String keyName, String value){
        this.keyName = keyName;
        this.value = value;
    }
}
