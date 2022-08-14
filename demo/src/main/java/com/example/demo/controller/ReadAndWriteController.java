package com.example.demo.controller;

import com.example.demo.excel.model.ExcelData;
import com.example.demo.excel.service.ReadExcelService;
import com.example.demo.word.service.WriteWordService;
import com.example.demo.word.utils.Word2XmlUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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

    @PostMapping("/download/xml")
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
                name = "xmlFile",
                value = "xmlFile",
                required = true,
                dataType = "MultipartFile",
                allowMultiple = true,
                paramType = "form")
    })
    public void readAndWriteAndDownloadByXML(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestPart("excelFile") MultipartFile excelFile,
            @RequestPart("xmlFile") MultipartFile xmlFile)
            throws IOException {

        String excelSuffix =
                Objects.requireNonNull(excelFile.getOriginalFilename())
                        .substring(excelFile.getOriginalFilename().lastIndexOf(".") + 1);
        String newExcelFileName = "./temp/" + UUID.randomUUID() + "." + excelSuffix;
        File newExcelFile = new File((File) null, newExcelFileName);
        FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newExcelFile);
        ExcelData excelData = excelService.getExcelData(new FileInputStream(newExcelFile));

        String xmlSuffix =
                Objects.requireNonNull(excelFile.getOriginalFilename())
                        .substring(excelFile.getOriginalFilename().lastIndexOf(".") + 1);
        String newXmlFileName = "./temp/" + UUID.randomUUID() + "." + xmlSuffix;
        File newXmlFile = new File((File) null, newXmlFileName);
        FileUtils.copyInputStreamToFile(xmlFile.getInputStream(), newXmlFile);

        String wordFilePath = "./temp/" + UUID.randomUUID() + ".doc";
        writeWordService.writeToWord(excelData, newXmlFile, wordFilePath);

        writeWordService.downloadFile(request, response, wordFilePath);
    }

    @PostMapping("/download/word")
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
    public void readAndWriteAndDownloadByWord(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestPart("excelFile") MultipartFile excelFile,
            @RequestPart("wordFile") MultipartFile wordFile)
            throws IOException {

        String excelSuffix =
                Objects.requireNonNull(excelFile.getOriginalFilename())
                        .substring(excelFile.getOriginalFilename().lastIndexOf(".") + 1);
        String newExcelFileName = "./temp/" + UUID.randomUUID() + "." + excelSuffix;
        File newExcelFile = new File((File) null, newExcelFileName);
        FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newExcelFile);
        ExcelData excelData = excelService.getExcelData(new FileInputStream(newExcelFile));

        String wordSuffix =
                Objects.requireNonNull(excelFile.getOriginalFilename())
                        .substring(excelFile.getOriginalFilename().lastIndexOf(".") + 1);
        String newWordFileName = "./temp/" + UUID.randomUUID() + "." + wordSuffix;
        File newWordFile = new File((File) null, newWordFileName);
        FileUtils.copyInputStreamToFile(wordFile.getInputStream(), newWordFile);

        // word转xml
        String newXmlFileName = Word2XmlUtil.wordToXml(new FileInputStream(newWordFile));
        assert newXmlFileName != null;
        File newXmlFile = new File(newXmlFileName);

        String wordFilePath = "./temp/" + UUID.randomUUID() + ".doc";
        writeWordService.writeToWord(excelData, newXmlFile, wordFilePath);

        writeWordService.downloadFile(request, response, wordFilePath);
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
