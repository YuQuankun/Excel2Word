package com.example.demo.excel.factory;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.model.InsuranceInformation;
import com.example.demo.excel.model.InvoiceInformation;
import com.example.demo.excel.model.MailingAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 曳戈泰尔
 * @date 2022/8/5 7:00 PM
 */
@Slf4j
@Service
public class StrategySign {

    public ExcelData getExcelData() {
        return excelData;
    }
    /** 最终数据 */
    private ExcelData excelData = new ExcelData();

    /** 策略模型接口 */
    private DealData delData;

    /** 记录投保信息所有数据 */
    private List<Map<Integer, String>> insuranceInformationMapList = new ArrayList<>();
    /** 投保信息code */
    private Integer insuranceInformationCode;
    /** 投保信息flag */
    private Integer insuranceInformationFlag = 0;

    public void strategySign(Map<Integer, String> data) {

        // 名称
        // 通过名称关键词获取策略code
        String name = data.get(0) == null ? "" : data.get(0).replace("\n", "");
        NameEnum nameEnum = NameEnum.getNameEnum(name);
        int code = nameEnum == null ? 0 : nameEnum.code;

        // 处理投保信息合并单元格数据
        if (code == NameEnum.ISI.code || insuranceInformationFlag == 1) {
            insuranceInformationMapList.add(data);

            if (insuranceInformationFlag == 0) {
                insuranceInformationFlag = 1;
                insuranceInformationCode = code;
            }
        }
        // 投保信息记录结束
        if (name.contains("投保人申明")) {
            if (insuranceInformationFlag == 1) {
                insuranceInformationFlag = 0;
                code = insuranceInformationCode;
            }
        }

        // 策略选择器
        switch (code) {
                // 投保人名称
            case 1:
                this.delData = new ApplicantNameDeal();
                String applicantName = (String) delData.run(data);
                excelData.setApplicantName(applicantName);
                break;

                // 所属机构二级单位名称
            case 2:
                this.delData = new SecondaryUnitsNameDeal();
                String secondaryUnitsName = (String) delData.run(data);
                excelData.setSecondaryUnitsName(secondaryUnitsName);
                break;

                // 发票信息
            case 3:
                this.delData = new InvoiceInformationDeal();
                InvoiceInformation invoiceInformation = (InvoiceInformation) delData.run(data);
                excelData.setInvoiceInformation(invoiceInformation);
                break;

                // 邮寄地址
            case 4:
                this.delData = new MailingAddressDeal();
                MailingAddress mailingAddress = (MailingAddress) delData.run(data);
                excelData.setMailingAddress(mailingAddress);
                break;

                // 单位名称
            case 5:
                this.delData = new UnitNameDeal();
                String unitName = (String) delData.run(data);
                excelData.setUnitName(unitName);
                break;

                // 单位地址
            case 6:
                this.delData = new UnitAddressDeal();
                String unitAddress = (String) delData.run(data);
                excelData.setUnitAddress(unitAddress);
                break;

                // 投保信息
            case 7:
                if (insuranceInformationFlag == 0) {
                    this.delData = new InsuranceInformationDeal();
                    InsuranceInformation insuranceInformation =
                            (InsuranceInformation) delData.run(insuranceInformationMapList);
                    excelData.setInsuranceInformation(insuranceInformation);
                }
                break;

                // 投保日期
            case 8:
                this.delData = new InsuranceDateDeal();
                LocalDate insuranceDate = (LocalDate) delData.run(data);
                excelData.setInsuranceDate(insuranceDate);
                break;

                // 其他
            default:
                break;
        }
    }
}
