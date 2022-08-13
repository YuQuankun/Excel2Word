package com.example.demo;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.impl.ReadExcelServiceImpl;
import com.example.demo.word.model.SectionParam;
import com.example.demo.word.service.impl.WriteWordServiceImpl;
import com.example.demo.word.utils.ReadXmlParamUtil;
import com.example.demo.word.utils.ReadXmlSectionUtil;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class DemoApplicationTests {

    public static final String tempUrl = "./temp/";

    public static final String staticUrl = "D:\\new\\Excel2Word\\demo\\src\\main\\resources\\static\\";

    @Autowired ReadExcelServiceImpl readExcelServiceImpl;

    @Autowired WriteWordServiceImpl writeWordServiceImpl;

    @Test
    void contextLoads() {}

    @Test
    void readExcel() {
        // 获取文件输入流
        String fileName = "1.财产类保险投保单-2022元氏.xls";
        InputStream in = this.getClass().getResourceAsStream("/static/" + fileName);
        ExcelData excelData = readExcelServiceImpl.getExcelData(in);
        writeWordServiceImpl.writeToWord(excelData, "D:\\5.doc");
    }

    @Test
    void readWord() throws IOException {
        // 获取文件输入流
        //        String fileName = "财产一切险.doc";
        //        InputStream in = this.getClass().getResourceAsStream("/static/" + fileName);
        //        String newFileName = Word2XmlUtil.wordToXml(in);
        //        System.out.println(newFileName);

        // 记录提取数据
        SectionParam sectionParam = new SectionParam();

        // 临时文件路径
        String newFileName = "财产一切险3.xml";
        String fileUrlAndName = staticUrl + newFileName;

        // 提取章节内容
        SectionParam sectionData = ReadXmlSectionUtil.readXmlSection(fileUrlAndName);
        // System.out.println(sectionParam.toString());
        BeanUtils.copyProperties(sectionData, sectionParam);
        File file  = new File("D:\\4.txt");
        FileUtils.write(file,sectionData.getDeductibleExcess());
        // 提取表格参数
        SectionParam paramData = ReadXmlParamUtil.readXmlParam(fileUrlAndName);
        // System.out.println(sectionParam.toString());
        sectionParam.setQuotaList(paramData.getQuotaList());

    }
}
