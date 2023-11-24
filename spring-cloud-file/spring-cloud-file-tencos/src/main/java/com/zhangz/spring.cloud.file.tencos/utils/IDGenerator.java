package com.zhangz.springclouddemocloudalibabafiletencos.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.UUID;

/**
 *
 * @author bird
 * @date create by in 2019-06-17 14:52
 */
public class IDGenerator {

    private static final int INVOICE_ID_LENGTH = 19;
    private static final int INVOICE_CODE_LENGTH = 8;
    private static final int INVOICE_NUMBER_LENGTH = 10;

    /**
     * 票生成主键规则
     *
     * @param eInvoiceNumber
     * @param eInvoiceCode
     * @return
     */
    public static String generateInvoiceId(String eInvoiceNumber, String eInvoiceCode) {
        StringBuilder sb = new StringBuilder(eInvoiceCode).append("_").append(eInvoiceNumber);
        return sb.reverse().toString().trim();
    }

    /**
     * 解析主键
     * @param id 主键
     * @return 0-代码，1-号码
     */
    public static String[] getCodeAndNumber(String id){
        StringBuilder sb = new StringBuilder(id);
        return sb.reverse().toString().split("_");
    }

     

    /**
     * 根据票id 提取票代码票号 pair 的left为票代码right为票号码
     *
     * @param invoiceId
     * @return pair left invoiceCode, right invoiceNumber
     */
    public static Pair<String, String> extractCodeAndNumberFrom(String invoiceId) {
        if (invoiceId == null) {
            throw new IllegalArgumentException("无效的票id");
        }

        StringBuilder builder = new StringBuilder(invoiceId);
        builder.reverse();
        int i = builder.indexOf("_");
        String invoiceCode = builder.substring(0, i);
        String invoiceNumber = builder.substring(i + 1, builder.length());
        return Pair.of(invoiceCode, invoiceNumber);
    }

    public static String generate32GUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 接收人生成主键规则
     *
     * @param value
     * @param type
     * @return
     */
    public static String generateReceiverKey(String value, String type, String pid) {
        return new StringBuilder(value).append("_").append(type).append("_").append(pid).toString();
    }


    public static String generateRedisStatusId(String id) {
        return "invoice_status:" + id;
    }
    public static String generateRedisVoucherStatusId(String id,String agencyId) {
        return new StringBuilder("voucher_status:").append(id).append(agencyId).toString();
    }

    /**
     * 生成连接字符串
     *
     * @param map
     * @param linkFlag
     * @return
     */
    public static String generateLinkStrByReg(Map<String, String> map, String linkFlag) {
        StringBuilder sb = new StringBuilder();
        map.forEach((key, val) -> {
            sb.append(key + ":" + val + linkFlag);
        });
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
