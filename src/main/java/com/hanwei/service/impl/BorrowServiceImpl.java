package com.hanwei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwei.dao.BookMapper;
import com.hanwei.dao.BorrowMapper;
import com.hanwei.dao.CardMapper;
import com.hanwei.entity.Book;
import com.hanwei.entity.Borrow;
import com.hanwei.entity.Card;
import com.hanwei.service.BorrowService;
import com.hanwei.util.DateUtil;

@Service
public class BorrowServiceImpl implements BorrowService {
	
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private BookMapper bookMapper;

	@Override
	public void savaBorrow(Borrow borrow) {
		borrowMapper.saveBorrow(borrow);
	}

	@Override
	public int borrowNewBook(String bookId, String cardId) {
		Book book = bookMapper.selectBookById(Integer.parseInt(bookId));
		Card card = cardMapper.findCardById(cardId);
		if (card == null) {
			//δ��ѯ������֤
			return -1;
		} else {
			Borrow borrow = findByCidAndBid(cardId, bookId);
			if (borrow != null) {
				//�ѽ��Ĺ�����
				return -2;
			} else {
				//������ļ�¼
				borrow = new Borrow();
				borrow.setCid(card.getId().toString());
				borrow.setBid(book.getId().toString());
				borrow.setCreatetime(DateUtil.getNowDate());
				borrowMapper.saveBorrow(borrow);
				
				//���ĳɹ��������鼮ʣ������
				book.setNownum(book.getNownum() - 1);
				book.setUpdatetime(DateUtil.getNowDate());
				bookMapper.updateBook(book);
				return 0;
			}
		}
	}

	@Override
	public Borrow findByCidAndBid(String cardId, String bookId) {
		return borrowMapper.findByCidAndBid(cardId, bookId);
	}

	@Override
	public void deleteBorrow(Integer id) {
		borrowMapper.deleteBorrow(id);
	}
}