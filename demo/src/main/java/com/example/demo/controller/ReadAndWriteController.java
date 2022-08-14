package com.example.demo.controller;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.ReadExcelService;
import com.example.demo.word.param.QueryParam;
import com.example.demo.word.service.WriteWordService;
//import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

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
    public boolean readAndWrite(@RequestParam("excelFile")MultipartFile excelFile,
                                @RequestParam("wordFile") MultipartFile wordFile,
                                @RequestParam("wordOutPath") String wordFilePath) throws IOException {

        File newExcelFile = new File((File)null,"demo/src/main/resource/tempExcel");
        FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newExcelFile);
        ExcelData excelData = excelService.getExcelData(new FileInputStream(newExcelFile));

        File newWordFile = new File((File)null,"demo/src/main/resource/tempWord");
        FileUtils.copyInputStreamToFile(wordFile.getInputStream(), newWordFile);
        return writeWordService.writeToWord(excelData,newWordFile,wordFilePath);
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
