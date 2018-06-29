package com.hanwei.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.Book;
import com.hanwei.entity.Borrow;
import com.hanwei.entity.Card;
import com.hanwei.service.BookService;
import com.hanwei.service.BorrowService;
import com.hanwei.service.CardService;
import com.hanwei.util.DateUtil;
import com.hanwei.util.StringUtil;

@Controller
@RequestMapping(value="/booksystem/revert")
public class RevertController {
	
	@Autowired
	private CardService cardService;
	@Autowired
	private BookService bookService;
	@Autowired
	private BorrowService borrowService;

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model) {
		String cardnum = request.getParameter("cardnum");
		if (StringUtil.isNotEmpty(cardnum)) {
			Card card = cardService.findCardByCardnum(cardnum);
			if (card != null) {
				List<Book> bookList = bookService.findByCardId(card.getId());
				model.addAttribute("bookList", bookList);
				model.addAttribute("card", card);
			}
		}
		return "revert/index";
	}
	
	@RequestMapping(value = "/back")
	public String back(HttpServletRequest request, Model model) {
		String bookId = request.getParameter("bid");
		String cardId = request.getParameter("cid");
		
		Card card = cardService.findCardById(cardId);
		if (StringUtil.isNotEmpty(bookId) && StringUtil.isNotEmpty(cardId)) {
			Borrow borrow = borrowService.findByCidAndBid(cardId, bookId);
			if (borrow != null) {
				borrowService.deleteBorrow(borrow.getId());
				
				Book book = bookService.selectBookById(Integer.parseInt(bookId));
				book.setNownum(book.getNownum() + 1);
				book.setUpdatetime(DateUtil.getNowDate());
				bookService.updateBook(book);
			}
		}
		return "redirect:/booksystem/revert/index?cardnum=" + card.getCardnum();
	}
}