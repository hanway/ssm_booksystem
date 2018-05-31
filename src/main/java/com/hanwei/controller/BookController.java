package com.hanwei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.Book;
import com.hanwei.service.BookService;
import com.hanwei.util.StringUtil;

@Controller
@RequestMapping(value="/booksystem")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/book/index")
	public String findAll(HttpServletRequest request, Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/index";
	}
	
	@RequestMapping(value="/book/newOrEditBook")
	public String newOrEditBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Book book = bookService.selectBookById(Integer.parseInt(id));
			model.addAttribute("book", book);
		}
		return "book/newOrEditBook";
	}
	
	@RequestMapping(value="/book/saveOrUpdateBook")
	public String saveOrUpdateBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String isbn = request.getParameter("isbn");
		String total = request.getParameter("total");
		
		if (StringUtil.isEmpty(id)) {
			Book book = new Book();
			book.setBookname(bookname);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setIsbn(isbn);
			book.setTotal(Integer.parseInt(total));
			book.setNownum(Integer.parseInt(total));
			bookService.saveBook(book);
		} else {
			Book book = new Book();
			book.setId(Integer.parseInt(id));
			book.setBookname(bookname);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setIsbn(isbn);
			book.setTotal(Integer.parseInt(total));
			bookService.updateBook(book);
		}
		return "redirect:/booksystem/book/index";
	}
	
	@RequestMapping(value="book/delBook")
	public String delBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		bookService.delBook(id);
		return "redirect:/booksystem/book/index";
	}
}