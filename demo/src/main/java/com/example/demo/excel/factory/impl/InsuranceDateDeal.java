package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 投保日期 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class InsuranceDateDeal implements DealData<LocalDate> {

    @Override
    public LocalDate run(Map<Integer, String> data) {

        try {
            List<String> dataList = new ArrayList(data.values());
            String insuranceDateString = dataList.get(0);

            if (insuranceDateString != null) {

                // 冒号切割字符串，获取日期字符串
                List<String> contentList =
                        Arrays.asList(insuranceDateString.replace(":", "：").split("："));
                String dateString = contentList.get(2).replace(" ", "");

                // 处理年月日为标准字符串
                String year = dateString.split("年")[0];
                String month = dateString.split("月")[0].split("年")[1];
                String day = dateString.split("月")[1].split("日")[0];
                dateString =
                        year
                                + "年"
                                + (month.length() != 2 ? "0" + month : month)
                                + "月"
                                + (day.length() != 2 ? "0" + day : day)
                                + "日";

                // 格式化日期字符串为LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
                LocalDate localDate = LocalDate.parse(dateString, formatter);

                return localDate;
            }
        } catch (Exception e) {
            log.error("投保日期处理失败");

            return null;
        }

        return null;
    }

    @Override
    public LocalDate run(List<Map<Integer, String>> data) {
        return null;
    }
}
