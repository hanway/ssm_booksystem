package com.hanwei.util.excel;

public interface ISheetRecord{

	/**
	 * ��ȡSheet����
	 * @return
	 */
	int getSheetIndex();
	
	/**
	 * ��ֵ
	 * @param v
	 */
	void setData(String[] v);
}
