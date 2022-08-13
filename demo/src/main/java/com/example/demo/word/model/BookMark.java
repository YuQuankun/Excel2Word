package com.example.demo.word.model;

import lombok.Data;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 书签分割字符串
 * @date 2022/8/13 11:16 AM
 */
@Data
// @Builder
// @AllArgsConstructor
// @NoArgsConstructor
public class BookMark {

    /** 分割书签1 */
    private String bookMark1 ="<w:p w:rsidR=\"00693224\" w:rsidRPr=\"00693224\" w:rsidRDefault=\"00693224\" w:rsidP=\"00693224\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"0\" w:name=\"分割1\"/><w:bookmarkEnd w:id=\"0\"/></w:p>";

    /** 分割书签2 */
    private String bookMark2 ="<w:p w:rsidR=\"004D462D\" w:rsidRDefault=\"004D462D\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"1\" w:name=\"分割2\"/><w:bookmarkEnd w:id=\"1\"/></w:p>";

    /** 分割书签2 标记提取数据开始 */
    private String bookMark2Deal ="<w:p w:rsidR=\"004D462D\" w:rsidRDefault=\"004D462D\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"1\" w:name=\"分割2\"/><w:bookmarkEnd w:id=\"1\"/></w:p>";

    /** 分割书签3 */
    private String bookMark3 = "<w:p w:rsidR=\"004D462D\" w:rsidRDefault=\"004D462D\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"2\" w:name=\"分割3\"/><w:bookmarkEnd w:id=\"2\"/></w:p>";

    /** 分割书签3 标记提取数据结束 */
    private String bookMark3Deal = "<w:p w:rsidR=\"004D462D\" w:rsidRDefault=\"004D462D\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"2\" w:name=\"分割3\"/><w:bookmarkEnd w:id=\"2\"/></w:p>";

    /** 分割书签4 */
    private String bookMark4 ="<w:p w:rsidR=\"00AD18F4\" w:rsidRDefault=\"00AD18F4\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"3\" w:name=\"分割4\"/><w:bookmarkEnd w:id=\"3\"/></w:p>";

    /** 分割书签5 */
    private String bookMark5 = "<w:p w:rsidR=\"00AD18F4\" w:rsidRDefault=\"00AD18F4\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"4\" w:name=\"分割5\"/><w:bookmarkEnd w:id=\"4\"/></w:p>";

    /** 分割书签6 */
    private String bookMark6 = "<w:p w:rsidR=\"004D138E\" w:rsidRDefault=\"004D138E\" w:rsidP=\"00011AFE\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"5\" w:name=\"分割6\"/><w:bookmarkEnd w:id=\"5\"/></w:p>";

    /** 分割书签7 */
    private String bookMark7 = "<w:p w:rsidR=\"009D7460\" w:rsidRPr=\"00E745B7\" w:rsidRDefault=\"009D7460\" w:rsidP=\"00E745B7\"><w:pPr><w:rPr><w:rFonts w:hint=\"default\"/></w:rPr></w:pPr><w:bookmarkStart w:id=\"7\" w:name=\"分割7\"/><w:bookmarkEnd w:id=\"7\"/></w:p>";

}
