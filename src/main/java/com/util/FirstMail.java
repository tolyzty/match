package com.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件Util
 * @author toly
 *
 */

public class FirstMail {
	/**
     * 发送简单邮件方法
     * @param host    发送邮件服务器的IP
     * @param from    发送人地址
     * @param receiver    接收人地址
     * @param subject    邮件主题
     * @param managers    内容
     * @param senderUsername    发送人的账户
     * @param senderPassword    发送人的密码
     * mail.smtp.auth 是否需要身份验证 一般都是需要的
     * @author 13376ACE
     */
     public static void sendMail(Map<String, Object> sendMap){
    	String receiver = (String) sendMap.get("receiver");// 接收人
    	String messages = (String) sendMap.get("messages");// 内容
    	String subject = (String) sendMap.get("subject");// 标题
        Properties props = System.getProperties();
        String host = "smtp.exmail.qq.com";
        String from = "notice@yafupay.com";
        final String senderUsername = "notice@yafupay.com";
        final String senderPassword = "Yafu@808";
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(senderUsername, senderPassword);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(messages);
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
     
    public static void main(String[] args) {
        	
		String BeTime = DateUtils.getCurrentDateTime2();//获取当前时间
		String Format = "yyyy-MM-dd HH:mm:ss";//时间格式
		String StartTime = DateUtils.dateAddDay(BeTime, Format, 30);//日期相减
		System.out.println(StartTime);

    }
}
