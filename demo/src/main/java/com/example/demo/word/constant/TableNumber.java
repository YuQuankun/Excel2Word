package com.example.demo.word.constant;

import lombok.Getter;

/**
 * @author kun_mi
 * @desc 表格中占位符对应的数字
 */

@Getter
public enum TableNumber {

    ONE(0,"${one}"),
    TWO(1,"${two}"),
    THREE(2,"${three}"),
    FOUR(3,"${four}"),
    FIVE(4,"${five}"),
    SIX(5,"${six}"),
    SEVEN(6,"${seven}"),
    EIGHT(7,"${eight}"),
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
