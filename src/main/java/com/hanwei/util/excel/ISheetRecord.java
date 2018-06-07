package com.hanwei.util.excel;

public interface ISheetRecord{

	/**
	 * 获取Sheet索引
	 * @return
	 */
	int getSheetIndex();
	
	/**
	 * 赋值
	 * @param v
	 */
	void setData(String[] v);
}
