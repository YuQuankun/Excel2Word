package com.example.demo.excel.factory.impl;

import com.example.demo.excel.factory.DealData;
import com.example.demo.excel.model.InvoiceInformation;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author 曳戈泰尔
 * @version 1.0
 * @description 发票信息 处理类
 * @date 2022/8/5 6:56 PM
 */
@Slf4j
public class InvoiceInformationDeal implements DealData<InvoiceInformation> {

    @Override
    public InvoiceInformation run(Map<Integer, String> data) {

        // 发票内容
        try {
            List<String> dataList = new ArrayList(data.values());
            String content = dataList.size() >= 2 ? dataList.get(1) : null;

            // 根据换行切割内容
            if (content != null) {
                List<String> contentList = Arrays.asList(content.split("\n"));

                // 赋值返回体
                // 根据冒号切割内容，同时处理中英文冒号
                InvoiceInformation invoiceInformation =
                        InvoiceInformation.builder()
                                .invoiceName(
                                        contentList.get(0).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(0).replace(":", "：").split("：")[1]
                                                : null)
                                .taxpayerNumber(
                                        contentList.get(1).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(1).replace(":", "：").split("：")[1]
                                                : null)
                                .addressAndPhone(
                                        contentList.get(2).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(2).replace(":", "：").split("：")[1]
                                                : null)
                                .openingBank(
                                        contentList.get(3).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(3).replace(":", "：").split("：")[1]
                                                : null)
                                .openingAccount(
                                        contentList.get(4).replace(":", "：").split("：").length >= 2
                                                ? contentList.get(4).replace(":", "：").split("：")[1]
                                                : null)
                                .build();

                return invoiceInformation;
            }
        } catch (Exception e) {
            log.error("发票信息处理失败");

            return new InvoiceInformation();
        }

        return new InvoiceInformation();
    }

    @Override
    public InvoiceInformation run(List<Map<Integer, String>> data) {
        return null;
    }
}
