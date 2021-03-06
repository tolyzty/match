package com.util;

import java.io.Serializable;

public class Mail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5014680246172133619L;

	public static final String ENCODEING = "UTF-8";

	private String host; // 服务器地址
	
	private int port;

	private String sender; // 发件人的邮箱

	private String receiver; // 收件人的邮箱

	private String name; // 发件人昵称

	private String username; // 账号

	private String password; // 密码

	private String subject; // 主题

	private String message; //
	
	private boolean isSSL = true;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getEncodeing() {
		return ENCODEING;
	}

	public boolean isSSL() {
		return isSSL;
	}

	public void setSSL(boolean isSSL) {
		this.isSSL = isSSL;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
