package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 所属机构二级单位名称 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class SecondaryUnitsNameDeal implements DealData<String> {

    @Override
    public String run(Map<Integer, String> data) {

        // map转list，取值
        try {
            List<String> dataList = new ArrayList(data.values());
            String secondaryUnitsName = dataList.size() >= 2 ? dataList.get(1) : null;
            return secondaryUnitsName;
        } catch (Exception e) {
            log.error("所属机构二级单位名称处理失败");

            return null;
        }
    }

    @Override
    public String run(List<Map<Integer, String>> data) {
        return null;
    }
}
