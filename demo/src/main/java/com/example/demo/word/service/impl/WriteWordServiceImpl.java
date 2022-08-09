package com.example.demo.word.service.impl;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.model.InsuranceProject;
import com.example.demo.word.service.WriteWordService;
import com.example.demo.word.utils.PutData2WordUtil;
import com.example.demo.word.utils.WordGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
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

    public Map<String, String> buildMap(Map<String, String> emptyMap, ExcelData excelData) throws IOException {
        PutData2WordUtil.putInsuranceInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putPropertyInfoExcel2Map(emptyMap,excelData);
        PutData2WordUtil.putDateExcel2Map(emptyMap,excelData);
        File file =  new File("C:\\Users\\kun_mi\\Desktop\\curl命令模板.txt");
        String s = FileUtils.readFileToString(file,"utf-8");
        emptyMap.put("zhangjie8",s);
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
