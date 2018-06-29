package com.hanwei.service;

import java.util.List;

import com.hanwei.entity.Book;

public interface BookService {

	public Book selectBookById(Integer id);
	
	public List<Book> findAll();

	public int saveBook(Book book);

	public int delBook(String id);
	
	public int updateBook(Book book);
	
	public void saveImportBook(List<Book> bookList);

	public List<Book> findByPage(int pageNo, int pageSize, String bookname);
	
	public int findByCount();

	public List<Book> findByCardId(int cardId);
}
