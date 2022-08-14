package com.example.demo.word.utils;

import com.example.demo.word.model.BookMark;
import com.example.demo.word.model.SectionParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读取word章节内容
 * @date 2022/8/10 12:17 AM
 */
@Slf4j
public class ReadXmlParamUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadXmlParamUtil.class);
    /**
     * 输入xml文件路径，提取所需数据到实体
     *
     * @param fileUrlAndName
     * @return
     */
    public static SectionParam readXmlParam(String fileUrlAndName) {

        // 初始化返回实体
        SectionParam sectionParam = new SectionParam();
        // 初始化分割字符串
        BookMark bookMark = ReadXmlSectionUtil.setMark(fileUrlAndName);

        // 初始化参数数组
        List<Float> quotaList = new ArrayList<>(20);

        try {

            // 第一次正则匹配,匹配出第八部分的所有XML数据
            String xmlContent =
                    FileUtils.readFileToString(new File((File) null, fileUrlAndName), "utf-8");
            Pattern pattern =
                    Pattern.compile(
                            bookMark.getBookMark2Deal() + ".*?" + bookMark.getBookMark3Deal());
            Matcher matcher = pattern.matcher(xmlContent);
            String xmlContent8 = "";
            if (matcher.find()) {
                xmlContent8 = matcher.group();
            }
            System.out.println(xmlContent8);

            // 第二次正则,匹配出所有带百分号的数据
            Pattern pattern1 = Pattern.compile("[0-9]+%");
            Matcher matcher1 = pattern1.matcher(xmlContent8);
            int matcher_start = 0;
            while (matcher1.find(matcher_start)) {
                quotaList.add(Float.parseFloat(matcher1.group(0).replace("%", "")) / 100);
                matcher_start = matcher1.end();
            }
            sectionParam.setQuotaList(quotaList);
        } catch (Exception e) {
            LOGGER.error("获取表格参数失败:{}", e.getMessage());
            e.printStackTrace();
        }
        return sectionParam;
    }

    public static SectionParam readXmlParam(File wordFile) {

        // 初始化返回实体
        SectionParam sectionParam = new SectionParam();
        // 初始化分割字符串
        // TODO 增加方法
        BookMark bookMark = ReadXmlSectionUtil.setMark(wordFile);

        // 初始化参数数组
        List<Float> quotaList = new ArrayList<>(20);

        try {

            // 第一次正则匹配,匹配出第八部分的所有XML数据
            String xmlContent = FileUtils.readFileToString(wordFile, "utf-8");
            Pattern pattern =
                    Pattern.compile(
                            bookMark.getBookMark2Deal() + ".*?" + bookMark.getBookMark3Deal());
            Matcher matcher = pattern.matcher(xmlContent);
            String xmlContent8 = "";
            if (matcher.find()) {
                xmlContent8 = matcher.group();
            }
            System.out.println(xmlContent8);

            // 第二次正则,匹配出所有带百分号的数据
            Pattern pattern1 = Pattern.compile("<w:t>[0-9]+.*?</w:t>");
            Matcher matcher1 = pattern1.matcher(xmlContent8);
            int matcher_start = 0;
            String temp = "";
            while (matcher1.find(matcher_start)) {

                // 针对拆分拼接
                temp = temp + matcher1.group(0);
                if (temp.contains("%")) {
                    temp = temp.replace("<w:t>", "").replace("</w:t>", "");
                    quotaList.add(Float.parseFloat(temp.replace("%", "")) / 100);

                    temp = "";
                }

                matcher_start = matcher1.end();
            }
            sectionParam.setQuotaList(quotaList);
        } catch (Exception e) {
            LOGGER.error("获取表格参数失败:{}", e.getMessage());
            e.printStackTrace();
        }
        return sectionParam;
    }
}
