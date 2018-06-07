package com.hanwei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwei.dao.BookMapper;
import com.hanwei.entity.Book;
import com.hanwei.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookMapper bookMapper;

	public Book selectBookById(Integer id) {
		return bookMapper.selectBookById(id);
	}

	public List<Book> findAll() {
		return bookMapper.findAll();
	}

	public int saveBook(Book book) {
		return bookMapper.saveBook(book);
	}

	public int delBook(String id) {
		return bookMapper.delBook(id);
	}

	public int updateBook(Book book) {
		return bookMapper.updateBook(book);
	}
	
	@Override
	public void saveImportBook(List<Book> bookList) {
		bookMapper.saveImportBook(bookList);
	}
}
