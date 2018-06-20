package com.hanwei.entity.vo;

import java.util.List;

import com.hanwei.entity.Book;

public class BookVo {
	
	private List<Book> list;
	private Integer count;
	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
