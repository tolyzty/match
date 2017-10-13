package com.util.excel;

import java.util.Map;

/**
 * excel列模型
 * @author xiaoC
 *
 */
public class ColumnModel {

	/**
	 * 列对应的属性名称
	 */
	private String columnName;
	/**
	 * 列显示标题
	 */
	private String columnTitle;
	
	/**
	 * 当前列标题组map
	 */
	private Map<String,Object> columnMap;
	
	private ColumnFormat format;
	
	public ColumnModel(){
		
	}
	
	public ColumnModel(String columnTitle){
		this.columnTitle = columnTitle;
	}
	
	public ColumnModel(String columnTitle,ColumnFormat format){
		this.columnTitle = columnTitle;
		this.format = format;
	}
	
	public ColumnModel(String columnName,String columnTitle){
		this.columnName = columnName;
		this.columnTitle = columnTitle;
	}
	
	public ColumnModel(String columnName,String columnTitle,ColumnFormat format){
		this.columnName = columnName;
		this.columnTitle = columnTitle;
		this.format = format;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnTitle() {
		return columnTitle;
	}

	public void setColumnTitle(String columnTitle) {
		this.columnTitle = columnTitle;
	}

	public Map<String, Object> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, Object> columnMap) {
		this.columnMap = columnMap;
	}

	public ColumnFormat getFormat() {
		return format;
	}

	public void setFormat(ColumnFormat format) {
		this.format = format;
	}
	
	public Object dataFormat(Object value){
		if(this.getFormat() != null){
			return this.getFormat().dataFormat(value);
		}
		return value;
	}
	
	public Object parsingFormat(Object cellValue){
		if(this.getFormat() != null){
			return this.getFormat().parsingFormat(cellValue);
		}
		return cellValue;
	}

	public Object dataFormat(int i, Object pojo, Object value) {
		if(this.getFormat() != null){
			return this.getFormat().dataFormat(i,pojo,value);
		}
		return value;
	}
	
	
	
}
