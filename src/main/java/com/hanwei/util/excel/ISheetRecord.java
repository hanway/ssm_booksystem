package com.hanwei.util.excel;

public interface ISheetRecord{

	/**
	 * 获取Sheet索引
	 */
	int getSheetIndex();
	
	/**
	 * 赋值
	 */
	void setData(String[] v);
}
