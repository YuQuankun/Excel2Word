package com.example.demo.word.constant;

import lombok.Getter;

/**
 * @author kun_mi
 * @desc 章节枚举值
 */

@Getter
public enum StdKeySection {

    DeductibleExcess("deductibleExcess","免赔额"),
    InsurerAndPremium("insurerAndPremium","保险人及保费"),
    PaymentMethod("paymentMethod","付费方式"),
    InsuranceBroker("insuranceBroker","保险经纪人"),
    SpecialAgreement("specialAgreement","特别约定"),
    AdditionalPerilsClause("additionalPerilsClause","附加险条款")
    ;
    private String keyName;

    private String value;

    StdKeySection(String keyName, String value){
        this.keyName = keyName;
        this.value = value;
    }
}
