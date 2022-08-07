package com.example.demo.excel.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 投保人名称 处理类
 * @date 2022/8/5 6:56 PM
 */
public class ApplicantNameDeal implements DealData<String> {

    @Override
    public String run(Map<Integer, String> data) {

        // map转list，取值
        List<String> dataList = new ArrayList(data.values());
        String applicantName = dataList.get(1);
        return applicantName;
    }

    @Override
    public String run(List<Map<Integer, String>> data) {
        return null;
    }
}
