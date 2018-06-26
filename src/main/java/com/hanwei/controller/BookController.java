package com.hanwei.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.hanwei.util.DateUtil;
import com.hanwei.util.ExcelExportUtil;
import com.hanwei.util.Page;
import com.hanwei.util.StringUtil;
import com.hanwei.util.excel.ExcelParser;

@Controller
@RequestMapping(value="/booksystem/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 首页列表
	 */
	@RequestMapping(value="/index")
	public String findAll(HttpServletRequest request, Model model) {
		String bookname = request.getParameter("bookname");
		String p = request.getParameter("p");
		try {
			if (StringUtil.isNotEmpty(bookname)) {
				//处理Get方式提交的中文
				bookname = new String(bookname.getBytes("ISO8859-1"),"UTF-8");
			}
			int pageNo = 1;
			if (StringUtil.isNotEmpty(p)) {
				pageNo = Integer.parseInt(p);
			}
			int totalSize = bookService.findByCount();
			Page<Book> page = new Page<Book>(pageNo, totalSize);
			List<Book> list = bookService.findByPage(page.getStart(), page.getPageSize(), bookname);
			page.setItems(list);
			model.addAttribute("page", page);
			model.addAttribute("keyword", bookname);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "book/index";
	}
	
	/**
	 * 新增或修改表单页
	 */
	@RequestMapping(value="/newOrEditBook")
	public String newOrEditBook(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Book book = bookService.selectBookById(Integer.parseInt(id));
			model.addAttribute("book", book);
		}
		return "book/newOrEditBook";
	}
	
	/**
	 * 新增或修改数据持久化
	 */
	@RequestMapping(value="/saveOrUpdateBook")
	public String saveOrUpdateBook(HttpServletRequest request) {
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
			book.setCreatetime(DateUtil.getNowDate());
			bookService.saveBook(book);
		} else {
			Book book = new Book();
			book.setId(Integer.parseInt(id));
			book.setBookname(bookname);
			book.setAuthor(author);
			book.setPublisher(publisher);
			book.setIsbn(isbn);
			book.setTotal(Integer.parseInt(total));
			book.setUpdatetime(DateUtil.getNowDate());
			bookService.updateBook(book);
		}
		return "redirect:/booksystem/book/index";
	}
	
	/**
	 * 删除一条数据
	 */
	@RequestMapping(value="/delBook")
	public String delBook(HttpServletRequest request) {
		String id = request.getParameter("id");
		bookService.delBook(id);
		return "redirect:/booksystem/book/index";
	}
	
	/**
	 * 导出Excel
	 */
	@RequestMapping(value="/export")
	public void export(HttpServletResponse response) {
		List<Book> bookList = bookService.findAll();
		String titleName = "书籍清单";
        String[] headers = new String[]{"书籍名称", "作者", "出版社", "ISBN码", "总数量", "剩余数量"};
        String sheetName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String[] fieldNames = {"bookname", "author", "publisher", "isbn", "total", "nownum"};
        HSSFWorkbook workbook = ExcelExportUtil.exportExcel(sheetName, titleName, headers, bookList, fieldNames);
        ExcelExportUtil.FzUtil(response, titleName, workbook);
	}
	
	/**
	 * 导入Excel表单页
	 */
	@RequestMapping(value="/importExcel")
	public String importExcel() {
		return "book/import";
	}
	
	/**
	 * 导入Excel持久化
	 * @param dataFile：上传的文件
	 */
	@RequestMapping(value="/importSave")
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
					book.setCreatetime(DateUtil.getNowDate());
					bookList.add(book);
				}
				bookService.saveImportBook(bookList);
			} else {
				throw new RuntimeException("导入的Excel没有数据！");
			}
		} catch (InstantiationException | IllegalAccessException | IOException e) {
			e.printStackTrace();
		}
		return "redirect:/booksystem/book/index";
	}
}