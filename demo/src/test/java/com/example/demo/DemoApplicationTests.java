package com.example.demo;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.impl.ReadExcelServiceImpl;
import com.example.demo.excel.service.impl.WriteWordServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;

@SpringBootTest
class DemoApplicationTests {

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
        writeWordServiceImpl.writeToWord(excelData,"2");
    }
}
