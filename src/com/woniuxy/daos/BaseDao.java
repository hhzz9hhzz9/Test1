package com.woniuxy.daos;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.woniuxy.annotations.ColumnNameAnn;
import com.woniuxy.annotations.ObjectAnno;


public class BaseDao<T> {
	public List<T> getResultList(ResultSet rs, Class<T> cla) throws Exception {
		List<T> list = new ArrayList<T>();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> map = getObjiect(cla);
			T obj=cla.newInstance();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String columnString = rsmd.getColumnName(i);
				String typeString = rsmd.getColumnTypeName(i);
				Field[] fields = cla.getDeclaredFields();
				String fieldName = "";
				boolean flag = false;
				Object childObj = null;
				for (Field field : fields) {
					//普通属性
					if (field.isAnnotationPresent(ColumnNameAnn.class)) {
						ColumnNameAnn annotation = field.getAnnotation(ColumnNameAnn.class);
						if (annotation.value().equals(columnString)) {
							fieldName = field.getName();
							flag = true;
						}
					}
					//对象属性
					else if (field.isAnnotationPresent(ObjectAnno.class)) {
						ObjectAnno anno = field.getAnnotation(ObjectAnno.class);
						if (useList(anno.value(), columnString)) {
							fieldName = field.getName();
							flag = false;
						}
					}
				}
				
				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("set");
				stringBuilder.append(fieldName.substring(0,1).toUpperCase());
				stringBuilder.append(fieldName.substring(1));
				
				String setMethodString = stringBuilder.toString();
				Method method = null;
				if (flag) {
					if (typeString.equalsIgnoreCase("int")) {
						method = cla.getDeclaredMethod(setMethodString, Integer.TYPE);
					} else if (typeString.equalsIgnoreCase("char") || typeString.equalsIgnoreCase("varchar")) {
						method = cla.getDeclaredMethod(setMethodString, String.class);

					} else if (typeString.equalsIgnoreCase("float")) {
						method = cla.getDeclaredMethod(setMethodString, Float.TYPE);
					}else if (typeString.equalsIgnoreCase("date")||typeString.equalsIgnoreCase("datetime")) {
						
						method = cla.getDeclaredMethod(setMethodString, Date.class);
						
					}
					
					Object value = rs.getObject(columnString);
					method.invoke(obj, value);
				}else {
					childObj=map.get(fieldName);
					Field[] childFields = childObj.getClass().getDeclaredFields();
					String childName = "";
					for (Field field : childFields) {
						if (field.isAnnotationPresent(ColumnNameAnn.class)) {
							ColumnNameAnn annotation = field.getAnnotation(ColumnNameAnn.class);
							if (annotation.value().equals(columnString)) {
								childName = field.getName();
								String setMethodName = "set" + childName.substring(0, 1).toUpperCase() + childName.substring(1);
								Class<?> clazz = childObj.getClass();
								Method method1 = null;
								if (typeString.equalsIgnoreCase("int")) {
									method1 = clazz.getDeclaredMethod(setMethodName, Integer.TYPE);
								} else if (typeString.equalsIgnoreCase("char") || typeString.equalsIgnoreCase("varchar")) {
									method1 = clazz.getDeclaredMethod(setMethodName, String.class);

								} else if (typeString.equalsIgnoreCase("float")) {
									method1 = clazz.getDeclaredMethod(setMethodName, Float.TYPE);
								}else if (typeString.equalsIgnoreCase("date")||typeString.equalsIgnoreCase("datetime")) {
									method1 = clazz.getDeclaredMethod(setMethodName, Date.class);
								}
							
								Object value = rs.getObject(columnString);
								
								method1.invoke(childObj, value);
								method = cla.getDeclaredMethod(setMethodString, clazz);
								method.invoke(obj, childObj);
							}
						}
					}
					
				}
				
			}
			list.add(obj);
		}
		return list;
	}
	private Map<String, Object> getObjiect(Class<T> cla) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
			Field[] fields = cla.getDeclaredFields();
			String fieldName = "";
			for (Field field : fields) {
				if (field.isAnnotationPresent(ObjectAnno.class)) {
					fieldName = field.getName();
					map.put(fieldName, field.getType().newInstance());					
				}
			}
		
		return map;
	}
	private boolean useList(String[] arr, String targetValue) {

		return Arrays.asList(arr).contains(targetValue);

	}
}
