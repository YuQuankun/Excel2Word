package com.example.demo.word.Enum;

import lombok.Getter;

/**
 * @author kun_mi
 * @dexs 基本信息枚举
 */

@Getter
public enum StdKeyBase {

    YEAR("year","年份"),
    CURRENT_DATE("date","当前日期")
    ;
    private String keyName;

    private String value;

    StdKeyBase(String keyName, String value){
        this.keyName = keyName;
        this.value = value;
    }
}
