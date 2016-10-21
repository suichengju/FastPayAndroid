package com.goodsrc.qynglibrary.utils;


import org.xutils.http.RequestParams;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public class ModelUtil {
	
	/**
	 * @category  通过model获取requestparams参数
	 * @param  model
	 *            模型
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static RequestParams RequestParamsReflect(Object model, RequestParams params) {
		if(model==null)return params;
		Field[] field = model.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
		try {
			for (int j = 0; j < field.length; j++) { // 遍历所有属性
				String name = field[j].getName(); // 获取属性的名字

				// Out.d("attribute name" , name);
				String type = field[j].getGenericType().toString(); // 获取属性的类型
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
					Method m;
					String nameSub = name.substring(0, 1).toUpperCase(
							Locale.ENGLISH);
					name = nameSub + name.substring(1);
					m = model.getClass().getMethod("get" + name);
					String value = (String) m.invoke(model); // 调用getter方法获取属性值
					if (value != null) {
						// Out.d("attribute value:" + value);
//						 .addQueryStringParameter(nameSub, value);
						params.addBodyParameter(name, value);
					}
				}
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {

			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
		return params;
	}

}
