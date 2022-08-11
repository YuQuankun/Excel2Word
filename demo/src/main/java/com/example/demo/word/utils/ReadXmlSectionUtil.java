package com.example.demo.word.utils;

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

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 读取word章节内容
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
    public static SectionParam ReadXmlSection(String fileUrlAndName) {

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(fileUrlAndName));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        log.debug(startElement.toString());
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        log.debug(characters.toString());
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        log.debug(endElement.toString());
                        break;

                    default:
                        break;
                }
            }

        } catch (Exception e) {
            log.error("提取XML文件内容失败：{}", e.getMessage());
        }

        return null;
    }
}
