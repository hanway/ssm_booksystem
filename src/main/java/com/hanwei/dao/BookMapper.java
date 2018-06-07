package com.hanwei.dao;

import java.util.List;

import com.hanwei.entity.Book;

public interface BookMapper {

	public Book selectBookById(Integer id);

	public List<Book> findAll();

	public int saveBook(Book book);

	public int delBook(String id);
	
	public int updateBook(Book book);
	
	public void saveImportBook(List<Book> bookList);
}
