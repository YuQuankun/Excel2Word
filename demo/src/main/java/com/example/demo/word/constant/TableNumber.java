package com.example.demo.word.constant;

import lombok.Getter;

/**
 * @author kun_mi
 * @desc 表格中占位符对应的数字
 */

@Getter
public enum TableNumber {

    ONE(0,"壹"),
    TWO(1,"贰"),
    THREE(2,"叁"),
    FOUR(3,"肆"),
    FIVE(4,"伍"),
    SIX(5,"陆"),
    SEVEN(6,"柒"),
    EIGHT(7,"捌"),
    DEFAULT(9999,"")
    ;
    private Integer index;

    private String value;

    TableNumber(Integer index, String value){
        this.index = index;
        this.value = value;
    }
    public static TableNumber getEnum(Integer index){
        for(TableNumber tableNumber:TableNumber.values()){
            if(tableNumber.getIndex().equals(index)){
                return tableNumber;
            }
        }
        return DEFAULT;
    }
}
