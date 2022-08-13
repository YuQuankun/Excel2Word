package com.example.demo.word.utils;

import com.example.demo.word.model.BookMark;
import com.example.demo.word.model.SectionParam;
import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读取word章节内容
 * @date 2022/8/10 12:17 AM
 */
@Slf4j
public class ReadXmlParamUtil {

    private static String tableStartFlag = "w:t>";
    private static String tableEndFlag = "/w:t>";

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
        BookMark bookMark = new BookMark();

        // 初始化参数数组
        List<Float> quotaList = new ArrayList<>(20);

        // 处理标记
        int dealFlag = 0;
        // 读取数据标记
        int readFlag = 0;

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileUrlAndName));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        // log.info(startElement.toString());
                        // System.out.println("开始：" + startElement.toString());
                        // 提取数据开始
                        if (startElement.toString().contains(bookMark.getBookMark2Deal())) {
                            dealFlag = 1;
                        }
                        // 提取数据结束
                        if (startElement.toString().contains(bookMark.getBookMark3Deal())) {
                            dealFlag = 0;
                            readFlag = 0;
                        }
                        // 读取数据开始
                        if (dealFlag == 1) {
                            if (startElement.toString().contains(tableStartFlag)) {
                                readFlag = 1;
                            }
                        }

                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        // log.info(characters.toString());
                        // 提取数据
                        if (readFlag == 1) {
                            // System.out.println("数据：" + characters.toString());
                            // 判断数据包含百分号
                            if (characters.toString().contains("%")) {
                                System.out.println("数据：" + characters.toString());
                                quotaList.add(
                                        Float.parseFloat(characters.toString().replace("%", ""))
                                                / 100);
                            }
                        }

                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        // log.info(endElement.toString());
                        // 读取数据开始
                        if (dealFlag == 1) {
                            // 读取数据结束
                            if (endElement.toString().contains(tableEndFlag)) {
                                readFlag = 0;
                            }
                        }
                        break;

                    default:
                        break;
                }
            }

        } catch (Exception e) {
            log.error("提取XML文件参数内容失败：{}", e.getMessage());
        }

        sectionParam.setQuotaList(quotaList);
        return sectionParam;
    }
}
