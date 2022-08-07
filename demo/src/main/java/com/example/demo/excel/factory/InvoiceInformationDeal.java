package com.example.demo.excel.factory;

import com.example.demo.excel.model.InvoiceInformation;

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
public class InvoiceInformationDeal implements DealData<InvoiceInformation> {

    @Override
    public InvoiceInformation run(Map<Integer, String> data) {

        // 发票内容
        List<String> dataList = new ArrayList(data.values());
        String content = dataList.get(1);

        // 根据换行切割内容
        if (content != null) {
            List<String> contentList = Arrays.asList(content.split("\n"));

            // 赋值返回体
            // 根据冒号切割内容，同时处理中英文冒号
            InvoiceInformation invoiceInformation =
                    InvoiceInformation.builder()
                            .invoiceName(contentList.get(0).replace(":", "：").split("：")[1])
                            .taxpayerNumber(contentList.get(1).replace(":", "：").split("：")[1])
                            .addressAndPhone(contentList.get(2).replace(":", "：").split("：")[1])
                            .openingBank(contentList.get(3).replace(":", "：").split("：")[1])
                            .openingAccount(contentList.get(4).replace(":", "：").split("：")[1])
                            .build();

            return invoiceInformation;
        }

        return null;
    }

    @Override
    public InvoiceInformation run(List<Map<Integer, String>> data) {
        return null;
    }
}
