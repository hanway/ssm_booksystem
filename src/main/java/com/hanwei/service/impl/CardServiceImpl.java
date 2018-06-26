package com.hanwei.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwei.dao.CardMapper;
import com.hanwei.entity.Card;
import com.hanwei.service.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardMapper cardMapper;

	@Override
	public List<Card> findAll() {
		return cardMapper.findAll();
	}

	@Override
	public void saveCard(Card card) {
		cardMapper.saveCard(card);
	}

	@Override
	public Card findCardById(String id) {
		return cardMapper.findCardById(id);
	}

	@Override
	public void updateCard(Card card) {
		cardMapper.updateCard(card);
	}

	@Override
	public void delCard(String id) {
		cardMapper.delCard(id);
	}


}
