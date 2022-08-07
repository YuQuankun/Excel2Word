package com.example.demo.excel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 投保信息
 * @date 2022/8/5 5:11 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceInformation {

    /** 投保项目 */
    private List<InsuranceProject> insuranceProjectList;

    /** 财产坐落地址 */
    private String propertyAddress;
}
