package com.hanwei.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.Card;
import com.hanwei.service.CardService;
import com.hanwei.util.DateUtil;
import com.hanwei.util.StringUtil;

@Controller
@RequestMapping(value="/booksystem/card")
public class CardController {

	@Autowired
	private CardService cardService;

	/**
	 * 借书证首页列表
	 */
	@RequestMapping(value="/list")
	public String list(Model model) {
		List<Card> cardList = cardService.findAll();
		model.addAttribute("cardList", cardList);
		return "card/list";
	}

	/**
	 * 新增或修改借书证表单页
	 */
	@RequestMapping(value="/newOrEditCard")
	public String newOrEditCard(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Card card = cardService.findCardById(id);
			model.addAttribute("card", card);
		}
		return "card/newOrEditCard";
	}

	/**
	 * 新增或修改借书证
	 */
	@RequestMapping(value="saveOrUpdateCard")
	public String saveOrUpdateCard(HttpServletRequest request) {
		String id = request.getParameter("id");
		String cardnum = request.getParameter("cardnum");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");

		//判断id是否为空，如果为空做新增操作，不为空做修改操作
		if (StringUtil.isEmpty(id)) {
			Card card = new Card();
			card.setCardnum(cardnum);
			card.setName(name);
			card.setTel(tel);
			card.setCreatetime(DateUtil.getNowDate());
			cardService.saveCard(card);
		} else {
			Card card = new Card();
			card.setId(Integer.parseInt(id));
			card.setCardnum(cardnum);
			card.setName(name);
			card.setTel(tel);
			cardService.updateCard(card);
		}
		return "redirect:/booksystem/card/list";
	}

	@RequestMapping(value="/delCard")
	public String delCard(HttpServletRequest request) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			cardService.delCard(id);
		}
		return "redirect:/booksystem/card/list";
	}
}