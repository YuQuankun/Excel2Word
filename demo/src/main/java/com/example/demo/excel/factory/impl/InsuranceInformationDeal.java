package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import com.example.demo.excel.model.InsuranceInformation;
import com.example.demo.excel.model.InsuranceProject;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 投保信息 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class InsuranceInformationDeal implements DealData<InsuranceInformation> {

    @Override
    public InsuranceInformation run(Map<Integer, String> data) {
        return null;
    }

    @Override
    public InsuranceInformation run(List<Map<Integer, String>> data) {

        // log.info(data.toString());

        // 返回实体
        InsuranceInformation insuranceInformation = new InsuranceInformation();
        // 记录投保项目List
        List<InsuranceProject> insuranceProjectList = new ArrayList<>();

        try {

            // 遍历处理所有Map
            for (Map<Integer, String> map : data) {
                // Map转List
                List<String> dataList = new ArrayList(map.values());

                // 不处理row
                if ("投保信息".equals(dataList.get(0).replace("\n", ""))
                        || "资产清单".equals(dataList.get(0).replace("\n", ""))
                        || "投保人申明".equals(dataList.get(0).replace("\n", ""))) {
                    continue;
                }

                // 处理财产坐落地址
                if ("财产坐落地址".equals(dataList.get(0).replace("\n", ""))) {
                    String propertyAddress = dataList.size() >= 2 ? dataList.get(1) : null;
                    insuranceInformation.setPropertyAddress(propertyAddress);
                    continue;
                }

                // 开始时间，字符串标准化
                String startDate = null;
                if (dataList.size() >= 6) {
                    List<String> startDateList = Arrays.asList(dataList.get(5).split("/"));
                    startDate =
                            ""
                                    + startDateList.get(0)
                                    + "-"
                                    + (startDateList.get(1).length() != 2
                                            ? "0" + startDateList.get(1)
                                            : startDateList.get(1))
                                    + "-"
                                    + (startDateList.get(2).length() != 2
                                            ? "0" + startDateList.get(2)
                                            : startDateList.get(2));
                }

                // 结束时间，字符串标准化
                String endDate = null;
                if (dataList.size() >= 7) {
                    List<String> endDateList = Arrays.asList(dataList.get(6).split("/"));
                    endDate =
                            ""
                                    + endDateList.get(0)
                                    + "-"
                                    + (endDateList.get(1).length() != 2
                                            ? "0" + endDateList.get(1)
                                            : endDateList.get(1))
                                    + "-"
                                    + (endDateList.get(2).length() != 2
                                            ? "0" + endDateList.get(2)
                                            : endDateList.get(2));
                }

                // 处理项目数据
                InsuranceProject insuranceProject =
                        InsuranceProject.builder()
                                .projectName(dataList.size() >= 1 ? dataList.get(0) : null)
                                .assetInsurance(dataList.size() >= 2 ? dataList.get(1) : null)
                                .assetClasses(dataList.size() >= 3 ? dataList.get(2) : null)
                                .rate(dataList.size() >= 4 ? Float.valueOf(dataList.get(3)) : null)
                                .insuranceAmount(
                                        dataList.size() >= 5
                                                ? new BigDecimal(dataList.get(4).replace(",", ""))
                                                : null)
                                .startDate(
                                        dataList.size() >= 6
                                                ? LocalDate.parse(
                                                        startDate,
                                                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                : null)
                                .endDate(
                                        dataList.size() >= 7
                                                ? LocalDate.parse(
                                                        endDate,
                                                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                : null)
                                .insuranceDays(
                                        dataList.size() >= 8
                                                ? (Float.valueOf(dataList.get(7))).intValue()
                                                : null)
                                .insurancePremium(
                                        dataList.size() >= 9
                                                ? new BigDecimal(dataList.get(8).replace(",", ""))
                                                        .setScale(2, RoundingMode.HALF_UP)
                                                : null)
                                .build();

                // 项目数据记录到项目List
                insuranceProjectList.add(insuranceProject);
            }

            // 返回体设置项目数据List
            insuranceInformation.setInsuranceProjectList(insuranceProjectList);

            return insuranceInformation;
        } catch (Exception e) {
            log.error("投保信息处理失败");

            return new InsuranceInformation();
        }
    }
}
