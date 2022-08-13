package com.example.demo.word.param;

import lombok.Data;
//import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author kun_mi
 */
@Data
public class QueryParam {

//    @Schema(description = "Excel表格读取路径",required = true)
    private String excelFilePath;

//    @Schema(description = "Word文件输出路径",required = true)
    private String wordFilePath;
}
