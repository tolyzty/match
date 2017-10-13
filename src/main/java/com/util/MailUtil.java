package com.util;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class MailUtil {

	    // 打印log日志
		protected final Logger logger = LoggerFactory.getLogger(getClass());

		public boolean send(Map<String, Object> sendMap) {
			String subject = (String) sendMap.get("subject");// 标题
			String message = (String) sendMap.get("message");// 内容
			String receiver = (String) sendMap.get("receiver");// 接收人
			
			//ConfigUtils config = new ConfigUtils("/email_config.properties");
			//ConfigUtils config = new ConfigUtils("/email_config.properties");

			//InputStream config = TestProperties.class.getClassLoader().getResourceAsStream("config.properties");
			

			
			Mail mail = new Mail();
			mail.setHost("smtp.exmail.qq.com");
			mail.setSender("notice@yafupay.com");
			mail.setReceiver(receiver); // 接收人
			mail.setUsername("notice@yafupay.com"); // 登录账号,一般都是和邮箱名一样吧
			mail.setPassword("Yafu@808"); // 发件人邮箱的登录密码
			mail.setSubject(subject);
			mail.setMessage(message);
			String port = "465";
			if(StringUtils.isNotEmpty(port)){
				mail.setPort(Integer.parseInt(port));
			}
			
			String isSSL = "true";
			if("false".equalsIgnoreCase(isSSL)){
				mail.setSSL(false);
			}
			
			return send(mail);
		}

		public boolean send(Mail mail) {
			Properties properties = new Properties(); 
		    properties.setProperty("mail.smtp.auth", "true");//服务器需要认证 
		    properties.setProperty("mail.transport.protocol", "smtp");//声明发送邮件使用的端口 
		    
		    int port = mail.getPort();
		    if(mail.isSSL()){
		    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		    	properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		    	properties.put("mail.smtp.ssl.enable", "true");
		    	
		    	if(port <= 0){
			    	port = 465;
			    }
		    }else{
		    	if(port <= 0){
			    	port = 25;
			    }
		    }
		    
//		    MailSSLSocketFactory sf = null;
//		    try {
//		        sf = new MailSSLSocketFactory();
//		    } catch (GeneralSecurityException e) {
//		        e.printStackTrace();
//		    }
//		    sf.setTrustAllHosts(true);
//		    properties.put("mail.smtp.ssl.enable", "true");
//		    properties.put("mail.smtp.ssl.socketFactory", sf);
		    
		    properties.put("mail.smtp.socketFactory.fallback", "false");
		    properties.put("mail.smtp.socketFactory.port",  "" + port);
		      
		    Session session = Session.getInstance(properties); 
		    session.setDebug(true);//同意在当前线程的控制台打印与服务器对话信息 
		      
		    try {
		    	Message message = new MimeMessage(session);//构建发送的信息 
		    	message.setSubject(mail.getSubject());
		    	message.setText(mail.getMessage());//信息内容 
		    	message.setFrom(new InternetAddress(mail.getSender()));//发件人 
		    	
		    	Transport transport = session.getTransport(); 
		    	transport.connect(mail.getHost(), port, mail.getSender(), mail.getPassword());//连接发件人使用发件的服务器 
//		    transport.connect("smtp.163.com", 465, sendUserName, sendPassword);//连接发件人使用发件的服务器 
		    	String receiver = mail.getReceiver();
		    	Address[] address = null;
		    	if(receiver.indexOf(",") >= 0){
		    		String[] rs = receiver.split(",");
		    		int len = rs.length;
		    		//最后一个为空不算
		    		if(StringUtils.isEmpty(rs[len - 1])){
		    			len --;
		    		}
		    		address = new Address[len];
		    		for (int i = 0; i < rs.length; i++) {
		    			if(StringUtils.isNotEmpty(rs[i])){
		    				address[i] = new InternetAddress(rs[i]);
						}
					}
		    	}else{
		    		address = new Address[]{new InternetAddress(receiver)};
		    	}
		    	transport.sendMessage(message, address);//接受邮件 
		    	transport.close();
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		    return true;
		}
		
		public static void main(String[] args) {
			MailUtil mailUtil = new MailUtil();
			Map<String,Object> sendMap = new HashMap<String,Object>();
			sendMap.put("subject", "邮件测试");//邮件标题
			
			sendMap.put("message", "邮件测试");//邮件内容
			sendMap.put("receiver", "40013990@qq.com");//收件人
			mailUtil.send(sendMap);
		}
}


