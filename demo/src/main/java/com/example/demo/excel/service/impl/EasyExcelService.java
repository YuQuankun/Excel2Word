package com.example.demo.excel.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.excel.factory.StrategySign;
import com.example.demo.excel.model.ExcelData;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读取excel处理类
 * @date 2022/8/5 5:50 PM
 */
@Slf4j
public class EasyExcelService extends AnalysisEventListener<Map<Integer, String>> {

    public ExcelData getExcelData() {
        return excelData;
    }

    /** 最终数据 */
    private ExcelData excelData;

    /** 策略服务 */
    private StrategySign strategySign;

    /**
     * 解析数据调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext context) {

        log.debug("获取数据：{}", data.toString());

        // 获取有效数据的Map
        Map<Integer, String> temp =
                data.entrySet().stream()
                        .filter(map -> map.getValue() != null)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // 数据传入策略模型赋值实体
        strategySign.strategySign(temp, data);
    }

    /**
     * 解析数据第一行，表头
     *
     * @param data
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> data, AnalysisContext context) {

        log.debug("解析表头");

        // 初始化策略服务
        strategySign = new StrategySign();
    }

    /**
     * 解析完数据 调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        log.debug("所有数据解析完成");

        /** 赋值最终数据 */
        excelData = strategySign.getExcelData();
    }
}
