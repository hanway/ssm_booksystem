package com.hanwei.util.excel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelParser {

	/**
	 * 获取Workbook
	 * @param multipartFile
	 * @return
	 * @throws IOException
	 */
	public static Workbook getWorkbook(MultipartFile multipartFile) throws IOException {
		String fileType = "";
		try {
			String fileName = multipartFile.getOriginalFilename();
			fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		if (!fileType.equals("xls") || !fileType.equals("xlsx")) {
			throw new RuntimeException("导入文件格式错误，需上传excel文件");
		}
		if (multipartFile.getSize() / 1024 / 1024 > 50) {
			throw new RuntimeException("文件大小不能超过50MB");
		}
		Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
		/*if (fileType.equals("xls") ) { 
			workbook = new HSSFWorkbook(multipartFile.getInputStream()); 
		} else { 
			workbook = new XSSFWorkbook(multipartFile.getInputStream()); 
		}*/
		return workbook;
	}

	/**
	 * 获取Sheet内容
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IOException
	 * @Param:dataRowIndex表示从第几行开始读取数据（包含列头）
	 */
	public static <T> List<T> getRecords(MultipartFile multipartFile, Class<T> clazz, int dataRowIndex) throws InstantiationException, IllegalAccessException, IOException {
		Workbook workbook = ExcelParser.getWorkbook(multipartFile);
		return ExcelParser.getRecords(workbook, clazz, dataRowIndex);
	}

	/**
	 * 获取Sheet内容
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getRecords(Workbook workBook, Class<T> clazz, int dataRowIndex) throws InstantiationException, IllegalAccessException {
		if (!ISheetRecord.class.isAssignableFrom(clazz)) {
			throw new RuntimeException("该方法clazz参数必须实现ISheetRecord接口");
		}
		List<ISheetRecord> records = new ArrayList<ISheetRecord>();
		if (workBook == null || clazz == null) {
			System.out.println("参数不能为空");
			return (List<T>) records;
		}
		ISheetRecord sheetRecord = (ISheetRecord) clazz.newInstance();
		Sheet sheet = workBook.getSheetAt(sheetRecord.getSheetIndex());
		if (sheet == null) {
			return (List<T>) records;
		}
		int firstRow = sheet.getFirstRowNum();
		int lastRow = sheet.getPhysicalNumberOfRows();
		// 第一行为标题，从第二行获取数据
		for (int i = firstRow + dataRowIndex; i <= lastRow; i++) {
			ISheetRecord record = (ISheetRecord) clazz.newInstance();
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			record.setData(getValues(row));
			records.add(record);
		}
		return (List<T>) records;
	}

	/**
	 * 获取excel数据
	 * @param row
	 * @return
	 */
	private static String[] getValues(Row row) {
		int cellSize = row.getLastCellNum();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < cellSize; i++) {
			list.add(getStringValueFromCell(row.getCell(i)));
		}
		 /*Iterator<Cell> iterator = row.cellIterator();
		 List<String> list = new ArrayList<String>();
		 while (iterator.hasNext()) {
		 Cell cell = iterator.next();
		 list.add(getStringValueFromCell(cell));
		 }*/
		String[] values = new String[list.size()];
		list.toArray(values);
		return values;
	}

	/**
	 * cell 值转换
	 * @param cell
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getStringValueFromCell(Cell cell) {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat decimalFormat = new DecimalFormat("#.#");
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			cellValue = cell.getStringCellValue();
		} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				double d = cell.getNumericCellValue();
				Date date = HSSFDateUtil.getJavaDate(d);
				cellValue = sFormat.format(date);
			} else {
				cellValue = decimalFormat.format((cell.getNumericCellValue()));
			}
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			cellValue = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			cellValue = String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
			cellValue = "";
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			cellValue = cell.getCellFormula().toString();
		}
		return cellValue;
	}
}
