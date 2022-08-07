package com.example.demo.excel.factory;

import com.example.demo.excel.model.MailingAddress;

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
public class MailingAddressDeal implements DealData<MailingAddress> {

    @Override
    public MailingAddress run(Map<Integer, String> data) {

        // 邮件内容
        List<String> dataList = new ArrayList(data.values());
        String content = dataList.get(1);

        // 根据换行切割内容
        if (content != null) {
            List<String> contentList = Arrays.asList(content.split("\n"));

            // 赋值返回体
            // 根据冒号切割内容，同时处理中英文冒号
            MailingAddress mailingAddress =
                    MailingAddress.builder()
                            .mailContacts(contentList.get(0).replace(":", "：").split("：")[1])
                            .mailPhone(contentList.get(1).replace(":", "：").split("：")[1])
                            .mailAddress(contentList.get(2).replace(":", "：").split("：")[1])
                            .mailNumber(contentList.get(3).replace(":", "：").split("：")[1])
                            .build();

            return mailingAddress;
        }

        return null;
    }

    @Override
    public MailingAddress run(List<Map<Integer, String>> data) {
        return null;
    }
}
