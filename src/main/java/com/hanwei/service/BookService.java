package com.hanwei.service;

import java.util.List;

import com.hanwei.entity.Book;

public interface BookService {

	public Book selectBookById(Integer id);
	
	public List<Book> findAll();

	public int saveBook(Book book);

	public int delBook(String id);
	
	public int updateBook(Book book);
}
