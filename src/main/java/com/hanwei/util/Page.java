package com.hanwei.util;

import java.util.List;

public class Page<T> {
	
	private int pageSize = 10;//每页显示几条记录
	private int totalSize;//总条数
	private int pageNo;//当前页码
	private int start;//开始位置
	private List<T> items;//当前页的记录
	
	public Page(int pageNo, int totalSize) {
		setTotalSize(totalSize);
		setPageNo(pageNo);
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			pageNo = 1;
		} else if (pageNo > getTotalPages()) {
			pageNo = getTotalPages();
		}
		this.pageNo = pageNo;
		this.start = (this.pageNo - 1) * this.pageSize;
	}
	public int getTotalPages() {
		int result = totalSize / pageSize;
		if (totalSize % pageSize != 0) {
			result++;
		}
		return result;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}