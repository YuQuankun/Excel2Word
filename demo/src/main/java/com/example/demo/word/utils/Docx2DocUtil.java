package com.example.demo.word.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 曳戈泰尔
 * @date 2022/8/10 11:01 PM
 */
@Slf4j
public class Docx2DocUtil {

    public static final String tempUrl = "./temp/";

    /**
     * @param in
     * @return 文件名
     */
    public static String docxToDoc(InputStream in) {

        try {
            String fileName = UUID.randomUUID() + ".doc";

            return fileName;
        } catch (Exception e) {
            log.error("Word转XML失败：{}", e.getMessage());

            return null;
        }
    }
}
