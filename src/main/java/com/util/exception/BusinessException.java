package com.util.exception;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 业务异常类
 * @author xiejinzhong
 *
 */
public class BusinessException extends TranException{

	private static Logger log = LoggerFactory.getLogger(BusinessException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8979493489032631821L;
	
	
	private String code = ExcepCode.EX900001;
	private String msg = "";
	private Throwable e;
	private String[] args = null;
	
	public BusinessException(){
		super();
		this.println();
	}
	
	public BusinessException(String code){
		super(code);
		this.code = code;
		this.println();
	}
	public BusinessException(String code,Throwable e){
		super(code,e);
		this.code = code;
		this.e = e;
		this.println();
	}
	public BusinessException(String code,String msg){
		super(code,msg);
		this.code = code;
		this.msg = msg;
		this.println();
	}
	public BusinessException(String code,String msg,Throwable e){
		super(code,msg,e);
		this.code = code;
		this.msg = msg;
		this.e = e;
		this.println();
	}
	
	public BusinessException(String code,String msg,String ... args){
		super(code,msg,args);
		this.code = code;
		this.msg = msg;
		this.args = args;
		this.println();
	}
	
	public void println(){
//		if(StringUtils.isEmpty(msg)){
//			msg = MSGCODE.GET(this.getCode());
//		}
		if(msg != null && this.args != null && this.args.length > 0){
			for (String value : this.args) {
				msg = msg.replaceFirst("\\{}", value);
			}
		}
		
		log.error("错误代码:{} {}",this.getCode(),msg);
		
		if(this.e != null){
			log.error(e.getMessage(),e);
		}
	}
	
	public String getCode() {
		if(StringUtils.isEmpty(code)){
			code = ExcepCode.EX900001;
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		
		return msg;
	}
	
	public String getLogMsg(){
		
		return getMsg();
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}
	
}
