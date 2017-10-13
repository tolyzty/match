package com.util.bean;

import java.util.Map;

public class Result {
	private String code;
	private String msg;
	private Map<String, Object> map;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Result() {
		
	}
	
	public Result(String code) {
		this.code = code;
	}
	
	public Result(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public Result(String code,String msg,Map<String, Object> map) {
		this.code = code;
		this.msg = msg;
		this.map = map;
	}
}
