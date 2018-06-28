package com.hanwei.service;

import java.util.List;

import com.hanwei.entity.Card;

public interface CardService {

	List<Card> findAll();

	void saveCard(Card card);

	Card findCardById(String id);

	void updateCard(Card card);

	void delCard(String id);

    List<Card> findByBookId(String bookId);
}
