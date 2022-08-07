package com.example.demo.word.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 从网络上根据资料找到的一个工具类
 * 主要以freemarker 为核心的模板生成word文档的工具类
 * 这里默认配置了固定路径
 * 需要根据路径取到对应模板
 * 请求参数需要设置对应的模板名称
 * @author kun_mi
 * @className: WordGeneratorUtils
 * @description: 文档生成工具类
 * </p>
 * version: V1.0.0
 */
public final class WordGeneratorUtil {
    private static Configuration configuration = null;
    private static Map<String, Template> allTemplates = null;
    private static final String TEMPLATE_URL = "/templates/";

    /**
     * 模板常量类配置
     */
    public static final class FreemarkerTemplate {
        public static final String T1 = "t_1_2";
        public static final String REPORT = "report";
        public static final String REC_RECOMMEND = "recRecommend";

    }

    static {
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(WordGeneratorUtil.class, TEMPLATE_URL);
        allTemplates = new HashMap(4);
        try {
            // 注意初始化要载入对应模板
            allTemplates.put(FreemarkerTemplate.T1, configuration.getTemplate(FreemarkerTemplate.T1 + ".ftl"));
//            allTemplates.put(FreemarkerTemplate.REPORT, configuration.getTemplate(FreemarkerTemplate.REPORT + ".ftl"));
//            allTemplates.put(FreemarkerTemplate.REC_RECOMMEND, configuration.getTemplate(FreemarkerTemplate.REC_RECOMMEND + ".ftl"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private WordGeneratorUtil() {
        throw new AssertionError();
    }

    /**
     * 创建doc 文档
     * dataMap 数据，需要对应模板的占位符，否则会出错
     * @param dataMap 数据
     * @param wordName  word 报表的名称
     * @param freemarkerTemplateName  指定需要使用哪个freemarker模板
     * @return
     */
    public static File createDoc(String freemarkerTemplateName, String wordName, Map<String, String> dataMap) {
        try {
            File f = new File("D:\\"+wordName);
            Template t = allTemplates.get(freemarkerTemplateName);
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
            t.process(dataMap, w);
            w.close();
            return f;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("生成word文档失败");
        }
    }

}