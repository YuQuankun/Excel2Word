package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import com.example.demo.excel.model.MailingAddress;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 邮寄地址 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class MailingAddressDeal implements DealData<MailingAddress> {

    @Override
    public MailingAddress run(Map<Integer, String> data) {

        // 邮件内容
        try {
            List<String> dataList = new ArrayList(data.values());
            String content = dataList.size() >= 2 ? dataList.get(1) : null;

            // 根据换行切割内容
            if (content != null) {
                List<String> contentList = Arrays.asList(content.split("\n"));

                // 赋值返回体
                // 根据冒号切割内容，同时处理中英文冒号
                MailingAddress mailingAddress =
                        MailingAddress.builder()
                                .mailContacts(
                                        contentList.get(0).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(0).replace(":", "：").split("：")[1]
                                                : null)
                                .mailPhone(
                                        contentList.get(1).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(1).replace(":", "：").split("：")[1]
                                                : null)
                                .mailAddress(
                                        contentList.get(2).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(2).replace(":", "：").split("：")[1]
                                                : null)
                                .mailNumber(
                                        contentList.get(3).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(3).replace(":", "：").split("：")[1]
                                                : null)
                                .build();

                return mailingAddress;
            }
        } catch (Exception e) {
            log.error("邮寄地址处理失败");

            return new MailingAddress();
        }

        return new MailingAddress();
    }

    @Override
    public MailingAddress run(List<Map<Integer, String>> data) {
        return null;
    }
}
