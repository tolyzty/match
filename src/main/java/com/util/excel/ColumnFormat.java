package com.util.excel;

/**
 * 列格式化
 * @author xiejinzhong
 *
 */
public interface ColumnFormat {

	public Object dataFormat(Object value);
	
	public Object dataFormat(int index,Object obj,Object value);
	
	public Object parsingFormat(Object cellValue);
	
}
