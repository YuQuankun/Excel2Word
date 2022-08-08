package com.example.demo.word.utils;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 数字转化汉子类
 * @date 2022/8/8 6:22 PM
 */
public class NumUtils {

    /** num 表示数字，lower表示小写 */
    private static final String[] num_lower = {"○", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    public static String getLowerNum(int num) {
        // 遍历寻找值
        return num_lower[num];
    }
}
