package com.hanwei.service;

import com.hanwei.entity.Borrow;

public interface BorrowService {

	void savaBorrow(Borrow borrow);

	int borrowNewBook(String bookId, String cardId);

}
