package com.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 发送短信
 * @author 13376
 *
 */
public class SendUtil {
	private final static Logger log = LoggerFactory.getLogger(SendUtil.class);
	/**
	 * CpName 短信供应商用户名
	 */
	private static final String CpName = "shyafu";
	/**
	 * CpPassword 短信供应商密码
	 */
	private static final String CpPassword = "yafu0516";
	/**
	 * REURL 请求地址
	 */
	private static final String REURL = "http://qxt.fungo.cn/Recv_center";
	/**
	 * SC_OK 发送成功的编码
	 */
	private static final int SC_OK = 200; 
	
	public static final String CONTENT = "【雅付】您的验证码";
	
	public static final String REMARK = ",有效时间5分钟";
	
	public static final String CASPAY = "恭喜您,提现成功";
	
	public static final String PAYMENT = "恭喜您,支付成功";
	
	
/**
 * 发送短信
 * @param param 封装Map参数
 * @return Map集合
 * @throws Exception
 */
	public static Map<String, Object> sendSMS(Map<String, Object> param){
		Map<String, Object> resmaps = new HashMap<String, Object>();
		if (param.get("telphone")==null ||param.get("telphone")=="") {
			resmaps.put("msg", "手机号空:[phone]空");
			log.info("手机号码:[{}]",param.get("telphone"));
			return resmaps;
		}else if (param.get("Content")==null ||param.get("Content")=="") {
			resmaps.put("msg", "内容是空:[Content]空");
			log.info("短信内容:[{}]",param.get("Content"));
			return resmaps;
		}else{
			PostMethod post = new PostMethod(REURL); 
			NameValuePair[] data = {	
				new NameValuePair("CpName",CpName),	
				new NameValuePair("CpPassword",CpPassword),	
				new NameValuePair("DesMobile",param.get("telphone").toString()),	
				new NameValuePair("Content",param.get("Content").toString()),	
			};
			String result;
			try {
				result = executeMethod(post, data);
				log.info("获得发送短信返回参数:[{}]",result);
				resmaps = JUtil.toMap(result);
				log.info("封装到MAP参数:[{}]",resmaps);
				post.setRequestBody(data);
				log.info("发送手机号:[{}],发送的内容:[{}]",param.get("telphone"),param.get("Content"));
				post.releaseConnection();
				log.info("返回信息:[{}],返回的code:0成功：[{}]",result,resmaps.get("code"));
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resmaps;
		
	
	}
	

	
	/**
	 * 执行发送
	 * @param post
	 * @param data
	 * @return 
	 * @throws HttpException
	 * @throws IOException
	 */
	private static String executeMethod(PostMethod post, NameValuePair[] data) throws HttpException, IOException{
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		post.setRequestBody(data);
		HttpClient client = new HttpClient();
		client.executeMethod(post);
		Header[] headers = post.getRequestHeaders();
		int statusCode = post.getStatusCode();
		log.info("发送短信状态码:[{}]",statusCode);
		if (statusCode!=SC_OK) {
			log.info("发送短信状态码:[{}]",statusCode);
			return "errorCode:["+statusCode+"]";
		}else{
			log.info("发送的请求返回CODE:[{}]",statusCode);
			for (Header h : headers) {
				log.info("所有返回头信息:[{}]",h.toString());
			}
			return new String(post.getResponseBodyAsString().getBytes("UTF-8"));
			
		}
		
		
	}
	
	/**
	 * 获取验证码
	 * @return 
	 */
	public static String getCode(){
		String code = "";
		Random random = new Random(); 
		// 6位验证码
        for(int i=0;i<6;i++){
        	code += random.nextInt(10);    
        }
		return code;
	}
	
	
	/**
	 * 获取发送验证码消息
	 * @param code
	 * @return
	 */
	public static String getMessage(String code){
		String massage = CONTENT+"["+code+"]"+REMARK;
		return massage;
	}
	
	
	public static void main(String[] args) {
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		// 6位验证码
		for (int i = 0; i < 6; i++) {
			code.append(String.valueOf(random.nextInt(10)));
		}
		System.out.println(code);
		String Content = "【雅付】您的验证码 ["+code+"],有效时间5分钟";
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("phone", "17602100029");
		param.put("Content", Content);
		try {
		Map<String, Object> result = SendUtil.sendSMS(param);
		System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
