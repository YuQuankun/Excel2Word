package com.example.demo.word.utils;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.model.InsuranceProject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.example.demo.word.constant.StdKeyBase.CURRENT_DATE;
import static com.example.demo.word.constant.StdKeyBase.YEAR;
import static com.example.demo.word.constant.StdKeyBeInsurance.BE_INSURANCE_ADDRESS;
import static com.example.demo.word.constant.StdKeyBeInsurance.BE_INSURANCE_NAME;
import static com.example.demo.word.constant.StdKeyInsurance.*;
import static com.example.demo.word.constant.StdKeyPropertyInfo.*;

/**
 * @author kun_mi
 */
public class PutData2WordUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PutData2WordUtil.class);

    private static final String DEFAULT_STR = "DEFAULT_STR";

    /**
     * 插入投保人信息,被保险人信息
     *
     * @param emptyMap 结果Map
     * @param excelData excel数据
     */
    public static void putInsuranceInfoExcel2Map(
            Map<String, String> emptyMap, ExcelData excelData) {
        // 投保人信息、被保险人信息
        emptyMap.put(
                INSURANCE.getKeyName(),
                null == excelData.getApplicantName() ? DEFAULT_STR : excelData.getApplicantName());
        emptyMap.put(
                INSURANCE_FULL.getKeyName(),
                StringUtils.defaultString(excelData.getApplicantName(), DEFAULT_STR));
        emptyMap.put(INSURANCE_NUMBER.getKeyName(), "000000000000000000000");
        emptyMap.put(
                SECONDARY_COMPANY_NAME.getKeyName(),
                StringUtils.defaultString(excelData.getSecondaryUnitsName(), DEFAULT_STR));
        emptyMap.put(
                ISSUE_NAME.getKeyName(),
                StringUtils.defaultString(
                        excelData.getInvoiceInformation().getInvoiceName(), DEFAULT_STR));
        emptyMap.put(
                TAX_IDENTIFY_NUM.getKeyName(),
                StringUtils.defaultString(
                        excelData.getInvoiceInformation().getTaxpayerNumber(), DEFAULT_STR));
        int len =
                excelData
                        .getInvoiceInformation()
                        .getAddressAndPhone()
                        .replaceAll(" ", "")
                        .replaceAll("\n", "")
                        .length();
        emptyMap.put(
                TAX_ADDRESS.getKeyName(),
                StringUtils.defaultString(
                        StringUtils.trim(excelData.getInvoiceInformation().getAddressAndPhone())
                                .replaceAll(" ", "")
                                .replaceAll("\n", "")
                                .substring(0, len - 11),
                        DEFAULT_STR));
        emptyMap.put(
                TAX_PHONE.getKeyName(),
                StringUtils.defaultString(
                        StringUtils.trim(excelData.getInvoiceInformation().getAddressAndPhone())
                                .replaceAll(" ", "")
                                .replaceAll("\n", "")
                                .substring(len - 11, len),
                        DEFAULT_STR));
        emptyMap.put(
                TAX_BANK.getKeyName(),
                StringUtils.defaultString(
                        excelData.getInvoiceInformation().getOpeningBank(), DEFAULT_STR));
        emptyMap.put(
                TAX_ACCOUNT.getKeyName(),
                StringUtils.defaultString(
                        excelData.getInvoiceInformation().getOpeningAccount(), DEFAULT_STR));
        emptyMap.put(
                DELI_PERSON.getKeyName(),
                StringUtils.defaultString(
                        excelData.getMailingAddress().getMailContacts(), DEFAULT_STR));
        emptyMap.put(
                DELI_NUMBER.getKeyName(),
                StringUtils.defaultString(
                        excelData.getMailingAddress().getMailPhone(), DEFAULT_STR));
        emptyMap.put(
                DELI_ADDRESS.getKeyName(),
                StringUtils.defaultString(
                        excelData.getMailingAddress().getMailAddress(), DEFAULT_STR));
        emptyMap.put(
                DELI_EMAIL.getKeyName(),
                StringUtils.defaultString(
                        excelData.getMailingAddress().getMailNumber(), DEFAULT_STR));
        emptyMap.put(
                BE_INSURANCE_NAME.getKeyName(),
                StringUtils.defaultString(excelData.getUnitName(), DEFAULT_STR));
        emptyMap.put(
                BE_INSURANCE_ADDRESS.getKeyName(),
                StringUtils.defaultString(excelData.getUnitAddress(), DEFAULT_STR));
    }

    public static void putPropertyInfoExcel2Map(Map<String, String> emptyMap, ExcelData excelData) {
        // 保险财产信息
        emptyMap.put(
                PROPERTY_ADDRESS.getKeyName(),
                StringUtils.defaultString(
                        excelData.getInsuranceInformation().getPropertyAddress(), DEFAULT_STR));

        List<InsuranceProject> insuranceProjectList =
                excelData.getInsuranceInformation().getInsuranceProjectList();
        if (!CollectionUtils.isEmpty(insuranceProjectList)) {
            for (int i = 0; i < insuranceProjectList.size(); i++) {
                emptyMap.put(
                        PROJECT_NAME.getKeyName() + (i + 1),
                        insuranceProjectList.get(i).getProjectName());
                emptyMap.put(
                        PROPERTY_TYPE_FIRST.getKeyName() + (i + 1),
                        insuranceProjectList.get(i).getAssetInsurance());
                emptyMap.put(
                        PROPERTY_TYPE_SECOND.getKeyName() + (i + 1),
                        insuranceProjectList.get(i).getAssetClasses());
                emptyMap.put(
                        PREMIUM_RATE.getKeyName() + (i + 1),
                        String.valueOf(insuranceProjectList.get(i).getRate()));
                emptyMap.put(
                        INSURANCE_AMOUNT.getKeyName() + (i + 1),
                        String.valueOf(insuranceProjectList.get(i).getInsuranceAmount()));
                emptyMap.put(
                        INSURANCE_START_TIME.getKeyName() + (i + 1),
                        insuranceProjectList
                                .get(i)
                                .getStartDate()
                                .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                emptyMap.put(
                        INSURANCE_END_TIME.getKeyName() + (i + 1),
                        insuranceProjectList
                                .get(i)
                                .getEndDate()
                                .format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
                emptyMap.put(
                        INSURANCE_DAYS.getKeyName() + (i + 1),
                        String.valueOf(insuranceProjectList.get(i).getInsuranceDays()));
                emptyMap.put(
                        INSURANCE_COST.getKeyName() + (i + 1),
                        String.valueOf(insuranceProjectList.get(i).getInsurancePremium()));
            }
        } else {
            LOGGER.info("保险财产信息数组为空");
        }
    }

    public static void putDateExcel2Map(Map<String, String> emptyMap, ExcelData excelData) {
        // 基本信息
        Calendar calendar = Calendar.getInstance();
        emptyMap.put(YEAR.getKeyName(), String.valueOf(calendar.get(Calendar.YEAR)));

        // 处理签发日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(new Date());
        String lowerNumNow = "";
        for (int i = 0; i < now.length(); i++) {
            lowerNumNow =
                    lowerNumNow + NumUtil.getLowerNum(Character.getNumericValue(now.charAt(i)));
            switch (i) {
                case 3:
                    lowerNumNow = lowerNumNow + "年";
                    break;

                case 5:
                    lowerNumNow = lowerNumNow + "月";
                    break;

                case 7:
                    lowerNumNow = lowerNumNow + "日";
                    break;

                default:
                    break;
            }
        }
        emptyMap.put(CURRENT_DATE.getKeyName(), lowerNumNow);
    }
}
