package com.hanwei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
	public PageInfo<Book> queryByPage(String bookname, Integer pageNo, Integer pageSize) {
	    pageNo = pageNo == null ? 1 : pageNo;
	    pageSize = pageSize == null ? 10 : pageSize;
	    PageHelper.startPage(pageNo, pageSize);
	    List<Book> list = bookMapper.selectBookByBookName(bookname);
	    //用PageInfo对结果进行包装
	    PageInfo<Book> page = new PageInfo<Book>(list);
	    //测试PageInfo全部属性
	    System.out.println(page.getPageNum());
	    System.out.println(page.getPageSize());
	    System.out.println(page.getStartRow());
	    System.out.println(page.getEndRow());
	    System.out.println(page.getTotal());
	    System.out.println(page.getPages());
	    System.out.println(page.getFirstPage());
	    System.out.println(page.getLastPage());
	    System.out.println(page.isHasNextPage());
	    System.out.println(page.isHasPreviousPage());
	    return page;
	}

}
