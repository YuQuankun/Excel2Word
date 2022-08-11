package com.example.demo;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.impl.ReadExcelServiceImpl;
import com.example.demo.word.model.SectionParam;
import com.example.demo.word.service.impl.WriteWordServiceImpl;
import com.example.demo.word.utils.ReadXmlSectionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;

@SpringBootTest
class DemoApplicationTests {

    public static final String tempUrl = "./temp/";

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
        System.out.println(excelData.toString());

        writeWordServiceImpl.writeToWord(excelData, "4");
    }

    @Test
    void readWord() {
        // 获取文件输入流
        //        String fileName = "财产一切险.doc";
        //        InputStream in = this.getClass().getResourceAsStream("/static/" + fileName);
        //        String newFileName = Word2XmlUtil.wordToXml(in);
        //        System.out.println(newFileName);

        String newFileName = "财产一切险.xml";
        String fileUrlAndName = tempUrl + newFileName;
        SectionParam sectionParam = ReadXmlSectionUtil.ReadXmlSection(fileUrlAndName);
        System.out.println(sectionParam.toString());
    }
}
