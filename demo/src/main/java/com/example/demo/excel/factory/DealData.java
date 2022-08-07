package com.example.demo.excel.factory;

import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 曳戈泰尔
 * @date 2022/8/5 6:53 PM
 */
public interface DealData<T> {

    /**
     * 处理数据
     *
     * @return
     */
    T run(Map<Integer, String> data);

    /**
     * 处理数据
     *
     * @return
     */
    T run(List<Map<Integer, String>> data);
}
