package com.util;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 
 *   计算MD5值	
 * 
 * @author gaoxian
 *
 */
public class MD5Utils {
	
	public static String generate(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "UTF-8"));
    }
	
	public static String generateGBK(String text) {
        return DigestUtils.md5Hex(getContentBytes(text, "GBK"));
    }
	
	public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
	
	public static void main(String[] args){
		System.out.println(generate("bank_segment=1001&card_type=1&channel=1&cur_type=1&encode_type=MD5&memo=123&money=1&notify_url=http://pay.yafupay.com/bank_allingateway_notify.do&return_url=http://pay.yafupay.com/bank_allingateway_callback.do&spbillno=20161021225921237859&spid=1800314099&sp_userid=213&user_type=1&key=12345"));
		System.out.println(generateGBK("bank_segment=1001&card_type=1&channel=1&cur_type=1&encode_type=MD5&memo=123&money=1&notify_url=http://pay.yafupay.com/bank_allingateway_notify.do&return_url=http://pay.yafupay.com/bank_allingateway_callback.do&spbillno=20161021225921237859&spid=1800314099&sp_userid=213&user_type=1&key=12345"));
	}

}