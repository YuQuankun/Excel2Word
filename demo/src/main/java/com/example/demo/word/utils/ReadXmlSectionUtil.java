package com.example.demo.word.utils;

import com.example.demo.word.model.BookMark;
import com.example.demo.word.model.SectionParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读取word章节内容
 * @description word设置书签标记，不解析xml内容，通过书签标记，切割文本字符串即可
 * @date 2022/8/10 12:17 AM
 */
@Slf4j
public class ReadXmlSectionUtil {

    /**
     * 输入xml文件路径，提取所需数据到实体
     *
     * @param fileUrlAndName
     * @return
     */
    public static SectionParam readXmlSection(String fileUrlAndName) {

        // 初始化返回实体
        SectionParam sectionParam = new SectionParam();
        // 初始化分割字符串
        BookMark bookMark = setMark(fileUrlAndName);

        try {

            String xmlContent = FileUtils.readFileToString(new File((File) null,fileUrlAndName),"utf-8");
            // 书签x位置(第一个字符开始位置)
            int bookMark1Index = xmlContent.indexOf(bookMark.getBookMark1());
            int bookMark2Index = xmlContent.indexOf(bookMark.getBookMark2());
            int bookMark3Index = xmlContent.indexOf(bookMark.getBookMark3());
            int bookMark4Index = xmlContent.indexOf(bookMark.getBookMark4());
            int bookMark5Index = xmlContent.indexOf(bookMark.getBookMark5());
            int bookMark6Index = xmlContent.indexOf(bookMark.getBookMark6());
            int bookMark7Index = xmlContent.indexOf(bookMark.getBookMark7());

            // 提取 四、免赔额（人民币）
            sectionParam.setDeductibleExcess(
                    xmlContent.substring(
                            bookMark1Index + bookMark.getBookMark1().length(), bookMark2Index));

            // 提取 八、保险人及保险费(含税)
            sectionParam.setInsurerAndPremium(
                    xmlContent.substring(
                            bookMark2Index + bookMark.getBookMark2().length(), bookMark3Index));

            // 提取 九、付费方式
            sectionParam.setPaymentMethod(
                    xmlContent.substring(
                            bookMark3Index + bookMark.getBookMark3().length(), bookMark4Index));

            // 提取 十、保险经纪人
            sectionParam.setInsuranceBroker(
                    xmlContent.substring(
                            bookMark4Index + bookMark.getBookMark4().length(), bookMark5Index));

            // 提取 十二、特别约定
            sectionParam.setSpecialAgreement(
                    xmlContent.substring(
                            bookMark5Index + bookMark.getBookMark5().length(), bookMark6Index));

            // 提取 十三、附加险条款
            sectionParam.setAdditionalPerilsClause(
                    xmlContent.substring(
                            bookMark6Index + bookMark.getBookMark6().length(), bookMark7Index));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("提取XML文件章节内容失败：{}", e.getMessage());
        }

        return sectionParam;
    }

    public static BookMark setMark(String fileUrlAndName){
        BookMark bookMark = new BookMark();
        try {
            String xmlContent = FileUtils.readFileToString(new File((File) null,fileUrlAndName),"utf-8");
            Pattern pattern = Pattern.compile("<w:p.*?</w:p>");
            Matcher m = pattern.matcher(xmlContent);
            List<String> bookMarkList  = new ArrayList<>();
            int matcher_start = 0;
            while (m.find(matcher_start)){
                bookMarkList.add(m.group(0));
                matcher_start = m.end();
            }
            for(String value : bookMarkList){
                if(value.contains("分割1")){
                    bookMark.setBookMark1(value);
                }
                if(value.contains("分割2")){
                    bookMark.setBookMark2(value);
                    bookMark.setBookMark2Deal(value);
                }
                if(value.contains("分割3")){
                    bookMark.setBookMark3(value);
                    bookMark.setBookMark3Deal(value);
                }
                if(value.contains("分割4")){
                    bookMark.setBookMark4(value);
                }
                if(value.contains("分割5")){
                    bookMark.setBookMark5(value);
                }
                if(value.contains("分割6")){
                    bookMark.setBookMark6(value);
                }
                if(value.contains("分割7")){
                    bookMark.setBookMark7(value);
                }
            }
        }catch (Exception e){
             log.error("匹配标签失败:{}",e.getMessage());
             e.printStackTrace();
        }
        return bookMark;
    }

}
