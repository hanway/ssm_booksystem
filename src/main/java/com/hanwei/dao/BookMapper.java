package com.hanwei.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hanwei.entity.Book;

public interface BookMapper {

	public Book selectBookById(Integer id);

	public List<Book> findAll();

	public int saveBook(Book book);

	public int delBook(String id);
	
	public int updateBook(Book book);
	
	List<Book> selectBookByBookName(@Param("bookname") String bookname);
}
