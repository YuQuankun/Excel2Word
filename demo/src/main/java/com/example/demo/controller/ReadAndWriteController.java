package com.example.demo.controller;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.ReadExcelService;
import com.example.demo.word.service.WriteWordService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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
    @ApiImplicitParams({
        @ApiImplicitParam(
                name = "excelFile",
                value = "excelFile",
                required = true,
                dataType = "MultipartFile",
                allowMultiple = true,
                paramType = "form"),
        @ApiImplicitParam(
                name = "wordFile",
                value = "wordFile",
                required = true,
                dataType = "MultipartFile",
                allowMultiple = true,
                paramType = "form")
    })
    public boolean readAndWrite(
            @RequestPart("excelFile") MultipartFile excelFile,
            @RequestPart("wordFile") MultipartFile wordFile,
            @RequestParam("wordOutPath") String wordFilePath)
            throws IOException {

        File newExcelFile = new File((File) null, "demo/src/main/resource/tempExcel");
        FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newExcelFile);
        ExcelData excelData = excelService.getExcelData(new FileInputStream(newExcelFile));

        File newWordFile = new File((File) null, "demo/src/main/resource/tempWord");
        FileUtils.copyInputStreamToFile(wordFile.getInputStream(), newWordFile);
        return writeWordService.writeToWord(excelData, newWordFile, wordFilePath);
    }

    @PostMapping("/download")
    @ResponseBody
    @ApiImplicitParams({
        @ApiImplicitParam(
                name = "excelFile",
                value = "excelFile",
                required = true,
                dataType = "MultipartFile",
                allowMultiple = true,
                paramType = "form"),
        @ApiImplicitParam(
                name = "wordFile",
                value = "wordFile",
                required = true,
                dataType = "MultipartFile",
                allowMultiple = true,
                paramType = "form")
    })
    public boolean readAndWriteAndDownload(
            @RequestPart("excelFile") MultipartFile excelFile,
            @RequestPart("wordFile") MultipartFile wordFile,
            @RequestParam("wordOutPath") String wordFilePath)
            throws IOException {

        File newExcelFile = new File((File) null, "demo/src/main/resource/tempExcel");
        FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newExcelFile);
        ExcelData excelData = excelService.getExcelData(new FileInputStream(newExcelFile));

        File newWordFile = new File((File) null, "demo/src/main/resource/tempWord");
        FileUtils.copyInputStreamToFile(wordFile.getInputStream(), newWordFile);
        return writeWordService.writeToWord(excelData, newWordFile, wordFilePath);
    }

    @Autowired
    public void setExcelService(ReadExcelService excelService) {
        this.excelService = excelService;
    }

    @Autowired
    public void setWriteWordService(WriteWordService writeWordService) {
        this.writeWordService = writeWordService;
    }
}
