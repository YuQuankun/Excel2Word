package com.example.demo.word.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToFoConverter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.UUID;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description word转XML，暂存临时目录，使用完删除
 * @date 2022/8/10 12:18 AM
 */
@Slf4j
public class Word2XmlUtil {

    public static final String tempUrl = "./temp/";

    /**
     * @param in
     * @return 文件名
     */
    public static String wordToXml(InputStream in) {

        try {
            String fileName = UUID.randomUUID() + ".xml";

            HWPFDocument wordDocument = new HWPFDocument(in);
            WordToFoConverter converter =
                    new WordToFoConverter(
                            DocumentBuilderFactory.newInstance()
                                    .newDocumentBuilder()
                                    .newDocument());
            // 对HWPFDocument进行转换
            converter.processDocument(wordDocument);

            Writer writer = new FileWriter(tempUrl + fileName);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            // 是否添加空格
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(new DOMSource(converter.getDocument()), new StreamResult(writer));

            return fileName;
        } catch (Exception e) {
            log.error("Word转XML失败：{}", e.getMessage());

            return null;
        }
    }
}
