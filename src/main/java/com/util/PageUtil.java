package com.util;

import java.io.Serializable;


public class PageUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int startIdx = 0;
	
	private long pageSize=10;//每页大小
	
	private long totalSize=0; //总大小 一共多少条数
	
	private int pageIndex = 1 ;//当前页码
	
	private long totalPage = 1;//总页数

	public PageUtil(int pageIndex,long totalSize,long pageSize) {
		super();
		this.pageIndex = pageIndex;
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		
		
		
    // 计算总页数
		if (this.totalSize % this.pageSize == 0) {
			this.totalPage = this.totalSize / this.pageSize;
		} else {
			this.totalPage = this.totalSize / this.pageSize + 1;
		}
		
		if(this.totalPage<=0){
			this.totalPage = 1;
		}
		
		if (this.pageIndex <= 0) {
			this.pageIndex = 1;
		}
		
		if(this.pageIndex>this.totalPage){
			this.pageIndex = (int) this.totalPage;
		}
		// 计算当前页首记录号
		this.startIdx =  (int) ((this.pageIndex - 1) * this.pageSize);
		
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = (int) pageIndex;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	public long getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
}
