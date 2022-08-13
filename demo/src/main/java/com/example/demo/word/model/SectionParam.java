package com.example.demo.word.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 章节内容
 * @date 2022/8/10 12:59 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionParam {

    /** 免赔额 */
    private String deductibleExcess;

    /** 保险人及保险费 */
    private String insurerAndPremium;

    /** 份额比例参数数组 */
    private List<Float> quotaList = new ArrayList<>(20);

    /** 付费方式 */
    private String paymentMethod;

    /** 保险经纪人 */
    private String insuranceBroker;

    /** 特别约定 */
    private String specialAgreement;

    /** 附加险条款 */
    private String additionalPerilsClause;
}
