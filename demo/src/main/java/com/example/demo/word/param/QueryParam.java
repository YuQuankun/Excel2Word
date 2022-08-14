package com.example.demo.word.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
//import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author kun_mi
 */
@Data
public class QueryParam {

    private MultipartFile file1;

//    @Schema(description = "Excel表格读取路径",required = true)
    private String excelFilePath;

//    @Schema(description = "Word文件输出路径",required = true)
    private String wordFilePath;
}
