package com.example.demo.controller;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.ReadExcelService;
import com.example.demo.word.param.QueryParam;
import com.example.demo.word.service.WriteWordService;
//import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;

/**
 * @author kun_mi
 */

@RestController
@RequestMapping(("/excel"))
public class ReadAndWriteController {

    private ReadExcelService excelService;

    private WriteWordService writeWordService;

//    @Operation(description = "读取指定路径的Excel文件并输出到指定的Word文件中")
    @PostMapping("/write")
    @ResponseBody
    public boolean readAndWrite(@RequestBody QueryParam queryParam){
        ExcelData excelData = excelService.getExcelData(queryParam.getExcelFilePath());
        return writeWordService.writeToWord(excelData,queryParam.getWordFilePath());
    }

    @Autowired
    public void setExcelService(ReadExcelService excelService){
        this.excelService = excelService;
    }
    @Autowired
    public void setWriteWordService(WriteWordService writeWordService){
        this.writeWordService = writeWordService;
    }
}
