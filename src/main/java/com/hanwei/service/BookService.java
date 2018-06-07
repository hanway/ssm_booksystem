package com.hanwei.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hanwei.entity.Book;

public interface BookService {

	public Book selectBookById(Integer id);
	
	public List<Book> findAll();

	public int saveBook(Book book);

	public int delBook(String id);
	
	public int updateBook(Book book);
	
	PageInfo<Book> queryByPage(String bookname, Integer pageNo, Integer pageSize);

	public void saveImportBook(List<Book> bookList);
}
