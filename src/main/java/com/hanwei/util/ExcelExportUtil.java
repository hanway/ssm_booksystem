package com.hanwei.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;

public class ExcelExportUtil {

	/**
	 * 功能: 导出为Excel工作簿
	 * 参数: sheetName[工作簿中的一张工作表的名称]
	 * 参数: titleName[表格的标题名称]
	 * 参数: headers[表格每一列的列名]
	 * 参数: dataSet[要导出的数据源]
	 * 参数: resultUrl[导出的excel文件地址]
	 * 参数: pattern[时间类型数据的格式]
	 */
	public static HSSFWorkbook exportExcel(String sheetName,String titleName,String[] headers,Collection<?> dataSet,String[] fieldNames) {

		return doExportExcel(sheetName,titleName,headers,dataSet,fieldNames);

	}

	/**
	 * 功能:真正实现导出
	 */
	@SuppressWarnings("deprecation")
	private static HSSFWorkbook doExportExcel(String sheetName,String titleName,String[] headers,Collection<?> dataSet,String[] fieldNames) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个工作表
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置工作表默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		//在工作表中合并首行并居中
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length-1));

		// 创建[标题]样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		// 设置[标题]样式
		titleStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//创建[标题]字体
		HSSFFont titleFont = workbook.createFont();
		//设置[标题]字体
		titleFont.setColor(HSSFColor.WHITE.index);
		titleFont.setFontHeightInPoints((short) 24);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把[标题字体]应用到[标题样式]
		titleStyle.setFont(titleFont);

		// 创建[列首]样式
		HSSFCellStyle headersStyle = workbook.createCellStyle();
		// 设置[列首]样式
		headersStyle.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
		headersStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		headersStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		headersStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		headersStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		//创建[列首]字体
		HSSFFont headersFont = workbook.createFont();
		//设置[列首]字体
		headersFont.setColor(HSSFColor.VIOLET.index);
		headersFont.setFontHeightInPoints((short) 12);
		headersFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把[列首字体]应用到[列首样式]
		headersStyle.setFont(headersFont);

		// 创建[表中数据]样式
		HSSFCellStyle dataSetStyle = workbook.createCellStyle();
		// 设置[表中数据]样式
		dataSetStyle.setFillForegroundColor(HSSFColor.GOLD.index);
		dataSetStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		dataSetStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		dataSetStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		dataSetStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 创建[表中数据]字体
		HSSFFont dataSetFont = workbook.createFont();
		// 设置[表中数据]字体
		dataSetFont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		dataSetFont.setColor(HSSFColor.BLUE.index);
		// 把[表中数据字体]应用到[表中数据样式]
		dataSetStyle.setFont(dataSetFont);

		//创建标题行-增加样式-赋值
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell titleCell = titleRow.createCell(0);
		titleCell.setCellStyle(titleStyle);
		titleCell.setCellValue(titleName);

		// 创建列首-增加样式-赋值
		HSSFRow row = sheet.createRow(1);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(headersStyle);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);

		}

		// 创建表中数据行-增加样式-赋值
		Iterator<?> it = dataSet.iterator();
		int index = 1;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);   
			Object t = it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fieldNames.length; i++) {
				for (short j = 0; j < fields.length; j++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(dataSetStyle);
					Field field = fields[j];
					String fieldName = field.getName();
					if(fieldNames[i].equals(fieldName)){
						String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase()+ fieldName.substring(1);
						try {
							@SuppressWarnings("rawtypes")
							Class tCls = t.getClass();
							@SuppressWarnings("unchecked")
							Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
							Object value = getMethod.invoke(t, new Object[] {});
							
							// 如果是时间类型,按照格式转换
							String textValue = null;
							if (value instanceof Date) {
								Date date = (Date) value;
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								textValue = sdf.format(date);
							} else if (value instanceof Boolean) {
								if((boolean) value){
									textValue = "是";
								}else{
									textValue = "否";
								}
							} else {
								// 其它数据类型都当作字符串简单处理
								if(value != null){
									textValue = value.toString();
								}else{
									textValue = "";
								}
							}
							cell.setCellValue(textValue);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			}
		}
		return workbook;
	}

	public static void FzUtil(HttpServletResponse response, String titleName, HSSFWorkbook workbook) {
		try {
			response.reset();
			response.setContentType("application/msexcel;charset=utf-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((titleName + ".xls").getBytes("GBK"), "ISO8859_1"));
			workbook.write(response.getOutputStream());
			response.getOutputStream().flush();
			response.getOutputStream().close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
