package com.example.demo.word.service;

import com.example.demo.excel.model.ExcelData;
import org.springframework.stereotype.Service;

/**
 * @author kun_mi
 * @desc 写word
 */
public interface WriteWordService {

    void writeToWord(ExcelData excelData,String targetName);
}
