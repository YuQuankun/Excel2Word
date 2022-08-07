package com.example.demo.excel.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.ReadExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读Excel方法类
 * @date 2022/8/5 4:49 PM
 */
@Slf4j
@Service
public class ReadExcelServiceImpl implements ReadExcelService {

    /**
     * 获取Excel数据
     *
     * @param inputStream
     * @return
     */
    @Override
    public ExcelData getExcelData(InputStream inputStream) {

        /** 初始化读取Excel类 */
        EasyExcelService easyExcelService = new EasyExcelService();

        /** 读取Excel */
        EasyExcel.read(inputStream, easyExcelService).sheet().doRead();

        /** 获取返回结果 */
        /** 不存在数据，解析失败数据，返回null */
        return easyExcelService.getExcelData();
    }


}
