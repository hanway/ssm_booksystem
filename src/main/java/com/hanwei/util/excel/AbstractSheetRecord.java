package com.hanwei.util.excel;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractSheetRecord implements ISheetRecord {

	/**
	 * 赋值,当单元格参数为空时使用默认值处理（如需特殊处理，子类重写该方法）
	 */
	@Override
	public void setData(String[] v) {
		if (IFieldSequence.class.isAssignableFrom(this.getClass())) {
			IFieldSequence fieldSequence = (IFieldSequence) this;
			String sequence = fieldSequence.getFieldSequence();
			String[] fieldNames = null;
			if (sequence != null) {
				fieldNames = sequence.split(",");
				for (int i = 0; i < fieldNames.length; i++) {
					String fieldName = fieldNames[i].trim();
					if (v.length <= i || v[i] == null || v[i].equals("")) {
						continue;
					}
					try {
						Field field = this.getField(this.getClass(), fieldName);
						if (field == null) {
							continue;
						}
						field.setAccessible(true);
						this.setValue(field, v[i]);
					} catch (SecurityException | IllegalArgumentException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 查找某个类中的属性，当前类没有往父级查
	 */
	private Field getField(Class<?> clazz, String fieldName) {
		Field field = null;
		for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
			Field[] fields = clazz.getDeclaredFields();
			for (int i = 0; fields != null && i < fields.length; i++) {
				if (fields[i].getName().equals(fieldName)) {
					field = fields[i];
					return field;
				}
			}
		}
		return field;
	}

	/**
	 * 属性赋值(如需要特殊处理，子类重写该方法)
	 */
	public void setValue(Field field, Object value) {
		if (field == null) {
			return;
		}
		try {
			Class<?> clazz = field.getType();
			if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
				field.set(this, Integer.parseInt(value.toString()));
			} else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
				field.set(this, Double.parseDouble(value.toString()));
			} else if (clazz.equals(Date.class) || clazz.equals(Date.class)) {
				field.set(this, new SimpleDateFormat("yyyy-MM-dd").parse(value.toString()));
			} else {
				field.set(this, value);
			}
		} catch (IllegalArgumentException | IllegalAccessException | ParseException e) {
			e.printStackTrace();
		}
	}
}