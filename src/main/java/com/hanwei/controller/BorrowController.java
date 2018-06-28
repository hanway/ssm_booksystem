package com.hanwei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.Book;
import com.hanwei.entity.Card;
import com.hanwei.service.BookService;
import com.hanwei.service.BorrowService;
import com.hanwei.service.CardService;
import com.hanwei.util.StringUtil;

@Controller
@RequestMapping(value="/booksystem/borrow")
public class BorrowController {

	@Autowired
	private BookService bookService;
	@Autowired
	private CardService cardService;
	@Autowired
	private BorrowService borrowService;

	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "borrow/list";
	}

	@RequestMapping(value = "/borrowForm")
	public String borrowForm(HttpServletRequest request, Model model) {
		//根据书籍id查询书籍信息
		String bookId = request.getParameter("id");
		Book book = bookService.selectBookById(Integer.parseInt(bookId));

		//查询所有借书证
		List<Card> cardList = cardService.findAll();
		model.addAttribute("book", book);
		model.addAttribute("cardList", cardList);
		return "borrow/newBorrow";
	}

	@RequestMapping(value = "/saveBorrow")
	public String saveBorrow(HttpServletRequest request, Model model) {
		String bookId = request.getParameter("bookid");
		String cardId = request.getParameter("cardid");
		Book book = bookService.selectBookById(Integer.parseInt(bookId));
		//查询所有借书证
		List<Card> cardList = cardService.findAll();
		if (StringUtil.isEmpty(cardId)) {
			model.addAttribute("code", "1003");
			model.addAttribute("book", book);
			model.addAttribute("cardList", cardList);
			return "borrow/newBorrow";
		}
		int result = borrowService.borrowNewBook(bookId, cardId);
		if (result == -1) {
			model.addAttribute("code", "1001");
			model.addAttribute("book", book);
			model.addAttribute("cardList", cardList);
			return "borrow/newBorrow";
		} else if (result == -2) {
			model.addAttribute("code", "1002");
			model.addAttribute("book", book);
			model.addAttribute("cardList", cardList);
			return "borrow/newBorrow";
		}
		return "redirect:/booksystem/borrow/list";
	}

	@RequestMapping(value = "/borrowUsers")
	public String borrowUsers(HttpServletRequest request, Model model) {
		String bookId = request.getParameter("id");
		Book book = bookService.selectBookById(Integer.parseInt(bookId));
		List<Card> cardList = cardService.findByBookId(bookId);
		model.addAttribute("book", book);
		model.addAttribute("cardList", cardList);
		return "borrow/borrowUsers";
	}
}