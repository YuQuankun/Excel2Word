package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 单位地址 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class UnitAddressDeal implements DealData<String> {

    @Override
    public String run(Map<Integer, String> data) {

        // map转list，取值
        try {
            List<String> dataList = new ArrayList(data.values());
            String unitAddress = dataList.size() >= 2 ? dataList.get(1) : null;
            return unitAddress;
        } catch (Exception e) {
            log.error("单位地址处理失败");

            return null;
        }
    }

    @Override
    public String run(List<Map<Integer, String>> data) {
        return null;
    }
}
