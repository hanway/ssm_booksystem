package com.hanwei.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.hanwei.entity.Book;
import com.hanwei.service.BookService;
import com.hanwei.util.ExcelExportUtil;
import com.hanwei.util.StringUtil;
import com.hanwei.util.excel.ExcelParser;

@Controller
@RequestMapping(value="/booksystem")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * ��ҳ�б�
	 */
	@RequestMapping(value="/book/index")
	public String findAll(HttpServletRequest request, Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/index";
	}
	
	/**
	 * �������޸ı�ҳ
	 */
	@RequestMapping(value="/book/newOrEditBook")
	public String newOrEditBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Book book = bookService.selectBookById(Integer.parseInt(id));
			model.addAttribute("book", book);
		}
		return "book/newOrEditBook";
	}
	
	/**
	 * �������޸����ݳ־û�
	 */
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
			book.setCreatetime(sdf.format(new Date()));
			bookService.saveBook(book);
		} else {
			Book book = new Book();
			book.setId(Integer.parseInt(id));
			book.setBookname(bookname);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setIsbn(isbn);
			book.setTotal(Integer.parseInt(total));
			book.setUpdatetime(sdf.format(new Date()));
			bookService.updateBook(book);
		}
		return "redirect:/booksystem/book/index";
	}
	
	/**
	 * ɾ��һ������
	 */
	@RequestMapping(value="book/delBook")
	public String delBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		bookService.delBook(id);
		return "redirect:/booksystem/book/index";
	}
	
	/**
	 * ����Excel
	 */
	@RequestMapping(value="book/export")
	public void export(HttpServletRequest request, Model model, HttpServletResponse response) {
		List<Book> bookList = bookService.findAll();
		String titleName = "�鼮�嵥";
        String[] headers = new String[]{"�鼮����", "����", "������", "ISBN��", "������", "ʣ������"};
        String sheetName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String[] fieldNames = {"bookname", "author", "publisher", "isbn", "total", "nownum"};
        HSSFWorkbook workbook = ExcelExportUtil.exportExcel(sheetName, titleName, headers, bookList, fieldNames);
        ExcelExportUtil.FzUtil(response, titleName, workbook);
	}
	
	/**
	 * ����Excel��ҳ
	 */
	@RequestMapping(value="book/importExcel")
	public String importExcel(HttpServletRequest request, Model model) {
		return "book/import";
	}
	
	/**
	 * ����Excel�־û�
	 * @param dataFile���ϴ����ļ�
	 */
	@RequestMapping(value="book/importSave")
	public String importSave(MultipartFile dataFile) {
		List<Book> bookList = new ArrayList<Book>();
		try {
			List<Book> importBookList = ExcelParser.getRecords(dataFile, Book.class, 1);
			int resultCount = importBookList.size();
			if (resultCount > 0) {
				for (Book bk : importBookList) {
					Book book = new Book();
					book.setBookname(bk.getBookname());
					book.setAuthor(bk.getAuthor());
					book.setPublisher(bk.getPublisher());
					book.setIsbn(bk.getIsbn());
					book.setTotal(bk.getTotal());
					book.setNownum(bk.getNownum());
					book.setCreatetime(sdf.format(new Date()));
					bookList.add(book);
				}
				bookService.saveImportBook(bookList);
			} else {
				throw new RuntimeException("�����Excelû�����ݣ�");
			}
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:/booksystem/book/index";
	}
}