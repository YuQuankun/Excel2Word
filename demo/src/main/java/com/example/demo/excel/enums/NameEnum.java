package com.example.demo.excel.enums;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description Excel名称 枚举类
 * @date 2022/8/5 7:07 PM
 */
public enum NameEnum {
    AN(1, "投保人名称"),
    SUN(2, "所属机构二级单位名称"),
    IVI(3, "发票信息"),
    MA(4, "邮寄地址"),
    UN(5, "单位名称"),
    UA(6, "地址"),
    ISI(7, "投保信息"),
    ID(8, "投保人签章"),
    ;

    public Integer code;
    public String name;

    NameEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 根据 name 包含查询 获取 枚举
     *
     * @param name
     * @return
     */
    public static NameEnum getNameEnum(String name) {
        for (NameEnum nameEnum : NameEnum.values()) {
            if (name.contains(nameEnum.name)) {
                return nameEnum;
            }
        }

        return null;
    }
}
