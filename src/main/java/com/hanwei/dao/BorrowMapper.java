package com.hanwei.dao;

import org.apache.ibatis.annotations.Param;

import com.hanwei.entity.Borrow;

public interface BorrowMapper {

	void saveBorrow(Borrow borrow);

	Borrow findByCidAndBid(@Param("cardId") String cardId, @Param("bookId") String bookId);

}