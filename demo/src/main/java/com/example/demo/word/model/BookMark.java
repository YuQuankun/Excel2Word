package com.example.demo.word.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 书签分割字符串
 * @date 2022/8/13 11:16 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMark {

    /** 分割书签1 */
    private String bookMark1 =
            "<aml:annotation aml:id=\"0\" w:type=\"Word.Bookmark.Start\" w:name=\"分割1\"/>"
                    + "<aml:annotation aml:id=\"0\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签2 */
    private String bookMark2 =
            "<aml:annotation aml:id=\"1\" w:type=\"Word.Bookmark.Start\" w:name=\"分割2\"/>"
                    + "<aml:annotation aml:id=\"1\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签3 */
    private String bookMark3 =
            "<aml:annotation aml:id=\"2\" w:type=\"Word.Bookmark.Start\" w:name=\"分割3\"/>"
                    + "<aml:annotation aml:id=\"2\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签4 */
    private String bookMark4 =
            "<aml:annotation aml:id=\"3\" w:type=\"Word.Bookmark.Start\" w:name=\"分割4\"/>"
                    + "<aml:annotation aml:id=\"3\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签5 */
    private String bookMark5 =
            "<aml:annotation aml:id=\"4\" w:type=\"Word.Bookmark.Start\" w:name=\"分割5\"/>"
                    + "<aml:annotation aml:id=\"4\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签6 */
    private String bookMark6 =
            "<aml:annotation aml:id=\"5\" w:type=\"Word.Bookmark.Start\" w:name=\"分割6\"/>"
                    + "<aml:annotation aml:id=\"5\" w:type=\"Word.Bookmark.End\"/>";

    /** 分割书签7 */
    private String bookMark7 =
            "<aml:annotation aml:id=\"6\" w:type=\"Word.Bookmark.Start\" w:name=\"分割7\"/>"
                    + "<aml:annotation aml:id=\"6\" w:type=\"Word.Bookmark.End\"/>";
}
