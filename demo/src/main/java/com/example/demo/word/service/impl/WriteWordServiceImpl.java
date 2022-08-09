package com.example.demo.word.service.impl;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.model.InsuranceProject;
import com.example.demo.word.service.WriteWordService;
import com.example.demo.word.utils.NumUtils;
import com.example.demo.word.utils.WordGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.demo.word.constant.InsuranceType.*;
import static com.example.demo.word.constant.StdKeyBase.CURRENT_DATE;
import static com.example.demo.word.constant.StdKeyBase.YEAR;
import static com.example.demo.word.constant.StdKeyBeInsurance.BE_INSURANCE_ADDRESS;
import static com.example.demo.word.constant.StdKeyBeInsurance.BE_INSURANCE_NAME;
import static com.example.demo.word.constant.StdKeyInsurance.*;
import static com.example.demo.word.constant.StdKeyPropertyInfo.*;

/**
 * @author kun_mi
 */
@Service
@Slf4j
public class WriteWordServiceImpl implements WriteWordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteWordServiceImpl.class);

    private static final int SIZE = 50;

    private static final String DEFAULT_STR = "DEFAULT_STR";

    @Override
    public void writeToWord(ExcelData excelData, String targetName) {
        try {
            // 构造初始Map
            Map<String, String> initialMap = new HashMap<>(SIZE);
            // 根据险种获取模板名称
            String templateName =
                    getTemplateName(excelData.getInsuranceInformation().getInsuranceProjectList());
            // 调用Util方法
            WordGeneratorUtil.createDoc(
                    templateName, targetName + ".doc", buildMap(initialMap, excelData));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("输出为Word文档出错");
        }
    }

    public Map<String, String> buildMap(Map<String, String> emptyMap, ExcelData excelData) {
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

        // 基本信息
        Calendar calendar = Calendar.getInstance();
        emptyMap.put(YEAR.getKeyName(), String.valueOf(calendar.get(Calendar.YEAR)));

        // 处理签发日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(new Date());
        String lowerNumNow = "";
        for (int i = 0; i < now.length(); i++) {
            lowerNumNow =
                    lowerNumNow + NumUtils.getLowerNum(Character.getNumericValue(now.charAt(i)));
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
        lowerNumNow = lowerNumNow.substring(0, 5) + lowerNumNow.substring(5).replace("○", "");
        emptyMap.put(CURRENT_DATE.getKeyName(), lowerNumNow);

        return emptyMap;
    }

    public String getTemplateName(List<InsuranceProject> list) {
        String res = "t_";
        for (int i = 0; i < list.size(); i++) {
            switch (getEnum(list.get(i).getAssetClasses())) {
                case PROPERTY_ALL_INSURANCE:
                    res = res + PROPERTY_ALL_INSURANCE.getCode();
                    if (i != list.size() - 1) {
                        res = res + "_";
                    }
                    break;
                case MACHINE_BROKE_INSURANCE:
                    res = res + MACHINE_BROKE_INSURANCE.getCode();
                    if (i != list.size() - 1) {
                        res = res + "_";
                    }
                    break;
                default:
                    LOGGER.error("出现未知险种,现有模板无法覆盖");
            }
        }
        return res;
    }
}
