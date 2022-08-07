package com.example.demo.word.constant;

import lombok.Getter;

/**
 * @author kun_mi
 * @desc 保险种类
 */

@Getter
public enum InsuranceType {

    PROPERTY_ALL_INSURANCE("1","财产一切险"),

    MACHINE_BROKE_INSURANCE("2","机器损坏险"),

    DEFAULT("404","未知险种")
    ;

    private String code;

    private String value;

    InsuranceType(String code, String value){
        this.code = code;
        this.value = value;
    }

    public static InsuranceType getEnum(String value){
        for(InsuranceType insuranceType:InsuranceType.values()){
            if(insuranceType.getValue().equals(value)){
                return insuranceType;
            }
        }
        return DEFAULT;
    }
}
