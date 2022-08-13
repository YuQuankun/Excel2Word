package com.example.demo.word.utils;

import com.example.demo.word.model.BookMark;
import com.example.demo.word.model.SectionParam;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;

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
        BookMark bookMark = new BookMark();

        try {
            // 读取 xml 文件
            File fileInput = new File(fileUrlAndName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fileInput);
            DOMSource domSource = new DOMSource(doc);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            // 将转换过的xml的String 样式打印到控制台
            System.out.println(writer.toString());

            // 提取 四、免赔额（人民币）
            // sectionParam.setDeductibleExcess();
            // 提取 八、保险人及保险费(含税)
            // sectionParam.setInsurerAndPremium();
            // 提取 九、付费方式
            // sectionParam.setPaymentMethod();
            // 提取 十、保险经纪人
            // sectionParam.setInsuranceBroker();
            // 提取 十二、特别约定
            // sectionParam.setSpecialAgreement();
            // 提取 十三、附加险条款
            // sectionParam.setAdditionalPerilsClause();

        } catch (Exception e) {
            log.error("提取XML文件章节内容失败：{}", e.getMessage());
        }

        return sectionParam;
    }
}
