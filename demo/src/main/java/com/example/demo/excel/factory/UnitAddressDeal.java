package com.example.demo.excel.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 单位地址 处理类
 * @date 2022/8/5 6:56 PM
 */
public class UnitAddressDeal implements DealData<String> {

    @Override
    public String run(Map<Integer, String> data) {

        // map转list，取值
        List<String> dataList = new ArrayList(data.values());
        String unitAddress = dataList.get(1);
        return unitAddress;
    }

    @Override
    public String run(List<Map<Integer, String>> data) {
        return null;
    }
}
