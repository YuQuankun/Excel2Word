package com.example.demo.excel.service;

import com.example.demo.excel.model.ExcelData;

import java.io.InputStream;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读Excel方法类
 * @date 2022/8/5 5:48 PM
 */
public interface ReadExcelService {

    /**
     * 获取Excel数据
     *
     * @param inputStream
     * @return
     */
    ExcelData getExcelData(InputStream inputStream);
}
