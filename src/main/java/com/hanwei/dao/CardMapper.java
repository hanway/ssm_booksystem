package com.hanwei.dao;

import java.util.List;

import com.hanwei.entity.Card;

public interface CardMapper {

	List<Card> findAll();

	void saveCard(Card card);

	Card findCardById(String id);

	void updateCard(Card card);

	void delCard(String id);

    List<Card> findByBookId(String bookId);

	Card findCardByCardnum(String cardnum);
}
