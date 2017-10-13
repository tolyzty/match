package com.util.excel;

import java.util.List;


public class ExcelDataBox {
	/**
	 * 列配置
	 */
	private List<ColumnModel> columnModelList;
	
	private List<? extends Object> dataList;


	public List<ColumnModel> getColumnModelList() {
		return columnModelList;
	}

	public void setColumnModelList(List<ColumnModel> columnModelList) {
		this.columnModelList = columnModelList;
	}

	public List<? extends Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<? extends Object> dataList) {
		this.dataList = dataList;
	}

	
}
