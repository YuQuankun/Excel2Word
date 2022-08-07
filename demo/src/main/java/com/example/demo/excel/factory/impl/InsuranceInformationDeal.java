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
import java.util.stream.Collectors;

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

                // 获取有效数据的Map
                Map<Integer, String> validMap =
                        map.entrySet().stream()
                                .filter(e -> e.getValue() != null)
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                // Map转List
                // 有效数据
                List<String> dataValidList = new ArrayList(validMap.values());

                // 不处理row
                if ("投保信息".equals(dataValidList.get(0).replace("\n", ""))
                        || "资产清单".equals(dataValidList.get(0).replace("\n", ""))
                        || "投保人申明".equals(dataValidList.get(0).replace("\n", ""))) {
                    continue;
                }

                // 处理财产坐落地址
                if ("财产坐落地址".equals(dataValidList.get(0).replace("\n", ""))) {
                    String propertyAddress =
                            dataValidList.size() >= 2 ? dataValidList.get(1) : null;
                    insuranceInformation.setPropertyAddress(propertyAddress);
                    continue;
                }

                // 开始时间，字符串标准化
                String startDate = null;
                if (map.get(6) != null) {
                    List<String> startDateList = Arrays.asList(map.get(6).split("/"));
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
                if (map.get(7) != null) {
                    List<String> endDateList = Arrays.asList(map.get(7).split("/"));
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
                                .projectName(map.get(1) != null ? map.get(1) : null)
                                .assetInsurance(map.get(2) != null ? map.get(2) : null)
                                .assetClasses(map.get(3) != null ? map.get(3) : null)
                                .rate(map.get(4) != null ? Float.valueOf(map.get(4)) : null)
                                .insuranceAmount(
                                        map.get(5) != null
                                                ? new BigDecimal(map.get(5).replace(",", ""))
                                                : null)
                                .startDate(
                                        map.get(6) != null
                                                ? LocalDate.parse(
                                                        startDate,
                                                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                : null)
                                .endDate(
                                        map.get(7) != null
                                                ? LocalDate.parse(
                                                        endDate,
                                                        DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                                : null)
                                .insuranceDays(
                                        map.get(8) != null
                                                ? (Float.valueOf(map.get(8))).intValue()
                                                : null)
                                .insurancePremium(
                                        map.get(9) != null
                                                ? new BigDecimal(map.get(9).replace(",", ""))
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
