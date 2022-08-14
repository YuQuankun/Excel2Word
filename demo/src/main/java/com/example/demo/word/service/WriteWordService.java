package com.example.demo.word.service;

import com.example.demo.excel.model.ExcelData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author kun_mi
 * @desc 写word
 */
public interface WriteWordService {

    boolean writeToWord(ExcelData excelData, String targetName);

    boolean writeToWord(ExcelData excelData, File wordFile, String wordOutPath);

    void downloadFile(HttpServletRequest request, HttpServletResponse response, String fullPath);
}
