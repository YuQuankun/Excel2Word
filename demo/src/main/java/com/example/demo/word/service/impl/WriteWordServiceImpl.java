package com.example.demo.word.service.impl;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.model.InsuranceProject;
import com.example.demo.word.constant.TableNumber;
import com.example.demo.word.model.SectionParam;
import com.example.demo.word.service.WriteWordService;
import com.example.demo.word.utils.PutData2WordUtil;
import com.example.demo.word.utils.ReadXmlParamUtil;
import com.example.demo.word.utils.ReadXmlSectionUtil;
import com.example.demo.word.utils.WordGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static com.example.demo.word.constant.InsuranceType.*;
/**
 * @author kun_mi
 */
@Service
@Slf4j
public class WriteWordServiceImpl implements WriteWordService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteWordServiceImpl.class);

    private static final int SIZE = 50;


    @Override
    public boolean writeToWord(ExcelData excelData, String targetPath) {
        try {
            // 构造初始Map
            Map<String, String> initialMap = new HashMap<>(SIZE);
            // 根据险种获取模板名称
            String templateName =
                    getTemplateName(excelData.getInsuranceInformation().getInsuranceProjectList());
            // 调用Util方法
            WordGeneratorUtil.createDoc(
                    templateName, targetPath, buildMap(initialMap, excelData,"demo/src/main/resources/static/财产一切险4.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("输出为Word文档出错");
            return false;
        }
        return true;
    }

    @Override
    public boolean writeToWord(ExcelData excelData, File wordFile, String wordOutPath) {
        try {
            // 构造初始Map
            Map<String, String> initialMap = new HashMap<>(SIZE);
            // 根据险种获取模板名称
            String templateName =
                    getTemplateName(excelData.getInsuranceInformation().getInsuranceProjectList());
            // 调用Util方法
            WordGeneratorUtil.createDoc(
                    templateName, wordOutPath, buildMap(initialMap, excelData,wordFile));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("输出为Word文档出错");
            return false;
        }
        return true;
    }


    public Map<String, String> buildMap(Map<String, String> emptyMap, ExcelData excelData, String wordFileUrl) {
        //插入Excel表格数据
        PutData2WordUtil.putInsuranceInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putPropertyInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putDateExcel2Map(emptyMap,excelData);
        BigDecimal money = new BigDecimal(1);
        for(InsuranceProject insuranceProject:excelData.getInsuranceInformation().getInsuranceProjectList()){
            if("财产一切险".equals(insuranceProject.getAssetClasses())){
                money = insuranceProject.getInsuranceAmount();
            }
        }
        //插入Word模板中的数据
        List<Float> quotaList  = ReadXmlParamUtil.readXmlParam(wordFileUrl).getQuotaList();
        SectionParam sectionParam = ReadXmlSectionUtil.readXmlSection(wordFileUrl);
        String section8 = sectionParam.getInsurerAndPremium();
        for(int i=0; i<quotaList.size()-1;i++){
            String s =String.valueOf( money.multiply(BigDecimal.valueOf(quotaList.get(i))).setScale(2,BigDecimal.ROUND_HALF_UP));
            switch (TableNumber.getEnum(i)){
                case ONE:
                    section8 = section8.replace(TableNumber.ONE.getValue(),s);
                    break;
                case TWO:
                    section8 = section8.replace(TableNumber.TWO.getValue(),s);
                    break;
                case THREE:
                    section8 = section8.replace(TableNumber.THREE.getValue(),s);
                    break;
                case FOUR:
                    section8 = section8.replace(TableNumber.FOUR.getValue(),s);
                    break;
                case FIVE:
                    section8 = section8.replace(TableNumber.FIVE.getValue(),s);
                    break;
                case SIX:
                    section8 = section8.replace(TableNumber.SIX.getValue(),s);
                    break;
                default:
                    break;
            }
        }
        section8 = section8.replace("${all}",String.valueOf(money));
        sectionParam.setInsurerAndPremium(section8);
        PutData2WordUtil.putSectionToMap(emptyMap,sectionParam);
        return emptyMap;
    }

    public Map<String, String> buildMap(Map<String, String> emptyMap, ExcelData excelData, File wordFile){
        //插入Excel表格数据
        PutData2WordUtil.putInsuranceInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putPropertyInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putDateExcel2Map(emptyMap,excelData);
        BigDecimal money = new BigDecimal(1);
        for(InsuranceProject insuranceProject:excelData.getInsuranceInformation().getInsuranceProjectList()){
            if("财产一切险".equals(insuranceProject.getAssetClasses())){
                money = insuranceProject.getInsuranceAmount();
            }
        }
        //插入Word模板中的数据
        List<Float> quotaList  = ReadXmlParamUtil.readXmlParam(wordFile).getQuotaList();
        SectionParam sectionParam = ReadXmlSectionUtil.readXmlSection(wordFile);
        String section8 = sectionParam.getInsurerAndPremium();
        for(int i=0; i<quotaList.size()-1;i++){
            String s =String.valueOf( money.multiply(BigDecimal.valueOf(quotaList.get(i))).setScale(2,BigDecimal.ROUND_HALF_UP));
            switch (TableNumber.getEnum(i)){
                case ONE:
                    section8 = section8.replace(TableNumber.ONE.getValue(),s);
                    break;
                case TWO:
                    section8 = section8.replace(TableNumber.TWO.getValue(),s);
                    break;
                case THREE:
                    section8 = section8.replace(TableNumber.THREE.getValue(),s);
                    break;
                case FOUR:
                    section8 = section8.replace(TableNumber.FOUR.getValue(),s);
                    break;
                case FIVE:
                    section8 = section8.replace(TableNumber.FIVE.getValue(),s);
                    break;
                case SIX:
                    section8 = section8.replace(TableNumber.SIX.getValue(),s);
                    break;
                default:
                    break;
            }
        }
        section8 = section8.replace("${all}",String.valueOf(money));
        sectionParam.setInsurerAndPremium(section8);
        PutData2WordUtil.putSectionToMap(emptyMap,sectionParam);
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
