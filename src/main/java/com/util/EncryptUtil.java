package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EncryptUtil {
	private final static Logger log = LoggerFactory.getLogger(EncryptUtil.class);
	
	public static final String loginKey	= "4364B80770DE52AF24DEA40E5C1DCB08";
	public static final String payKey   = "F2128D1111CELJS76286QOEIS191f48N";
	
	/**
	 * 登陆密码加密
	 * @param loginname 用户名
	 * @param pwd 密码
	 * @return 密文密码
	 */
	public static String getEncrypPwd(Object loginname,Object pwd) {
		log.debug("加密参数:userName[{}],userPassword[{}]",loginname,pwd);
		return MD5.encryption(loginname+""+pwd+loginKey);
	}
	
	/**
	 * 支付密码加密
	 * @param loginname 用户名
	 * @param pwd 密码
	 * @return 密文密码
	 */
	public static String getEncrypPayPwd(Object loginname,Object pwd) {
		log.debug("加密参数:loginname[{}]pwd[{}]",loginname,pwd);
		return MD5.encryption(loginname+""+pwd+payKey);
	}
	
	
	
	public static void main(String[] args) {
		String pwd = EncryptUtil.getEncrypPwd("admin", "123456");
		System.out.println(pwd);
		/*System.out.println("重置支付密码:"+getEncrypPayPwd("12017","11111111"));
		System.out.println("重置登陆密码:"+getEncrypPwd("bomycs","11111111"));*/
	}
}
