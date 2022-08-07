package com.example.demo.word.Enum;

import lombok.Getter;

/**
 * 财产信息
 * @author kun_mi
 */
@Getter
public enum StdKeyPropertyInfo {

    PROJECT_NAME("project_name","项目名称"),
    PROPERTY_TYPE_FIRST("property_type_first","资产类别1-固定资产"),
    PROPERTY_TYPE_SECOND("property_type_second","资产类别2-险类型"),
    PREMIUM_RATE("premium_rate","费率"),
    INSURANCE_AMOUNT("insurance_amount","保险金额"),
    INSURANCE_START_TIME("insurance_start_time","保险起始时间"),
    INSURANCE_END_TIME("insurance_end_time","保险终止时间"),
    INSURANCE_DAYS("insurance_days","保险天数"),
    INSURANCE_COST("insurance_cost","保费"),
    PROPERTY_LIST("property_list","资产清单"),
    PROPERTY_ADDRESS("property_address","财产坐落地址")
    ;
    private String keyName;

    private String value;

    StdKeyPropertyInfo(String keyName, String value){
        this.keyName = keyName;
        this.value = value;
    }
}
