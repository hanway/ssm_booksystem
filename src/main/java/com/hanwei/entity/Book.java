package com.hanwei.entity;

import com.hanwei.util.excel.AbstractSheetRecord;
import com.hanwei.util.excel.IFieldSequence;

public class Book extends AbstractSheetRecord implements IFieldSequence {
	
	private Integer id;//id
	private String bookname;//书籍名称
	private String author;//作者
	private String publisher;//出版社
	private String isbn;//isbn码
	private Integer total;//总数量
	private Integer nownum;//当前数量
	private String createtime;//创建时间
	private String updatetime;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getNownum() {
		return nownum;
	}
	public void setNownum(Integer nownum) {
		this.nownum = nownum;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	@Override
	public int getSheetIndex() {
		return 0;
	}
	@Override
	public String getFieldSequence() {
		return "id, bookname, author, publisher, isbn, total, nownum, createtime";
	}
}
