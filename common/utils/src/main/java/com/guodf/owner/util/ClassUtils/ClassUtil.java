package com.guodf.owner.util.ClassUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;

public class ClassUtil {
	/**
	 * 对 类 中的 String 属性进行 encode 或 decode 操作
	 * 可以解决存储在hbase中时汉字乱码问题
	 * @param optType - E/Encode 编码；D/Decode 解码
	 * @param charSet - 默认是 系统字符集
	 * @throws Exception
	 */
	public static Object changeCode(String optType, Object o, String charSet) throws Exception {
		if (StringUtils.isNotBlank(optType) && o!=null) {
			if (StringUtils.isBlank(charSet)) {
				charSet = System.getProperty("sun.jnu.encoding");
			}
			Field[] fields = o.getClass().getDeclaredFields();
			for (Field f : fields) {
				String type = f.getGenericType().toString();
				if ("class java.lang.String".equals(type)) {
					String v = (String) initGetMethod(o, f).invoke(o);
					if (StringUtils.isNotBlank(v)) {
						if ("E".equalsIgnoreCase(optType) || "Encode".equalsIgnoreCase(optType)) {
							v = URLEncoder.encode(v, charSet);
						} else if ("D".equalsIgnoreCase(optType) || "Decode".equalsIgnoreCase(optType)) {
							v = URLDecoder.decode(v, charSet);
							v = URLDecoder.decode(v, charSet);// 防止前端已经进行一次编码，此处解两次
						}
						initSetMethod(o, f, String.class).invoke(o, v);
					}
				}
			}
		}
		return o;
	}
	
	/**
	 * 对 类 中的 String 属性进行 encode 操作
	 * @param o
	 * @param charSet - 默认是 系统字符集
	 * @throws Exception
	 */
	public static Object encode(Object o, String charSet) throws Exception {
		return changeCode("E", o, charSet);
	}
	/**
	 * 对 类 中的 String 属性进行 encode 操作，字符集使用 UTF-8
	 * @param o
	 * @throws Exception
	 */
	public static Object encodeByUTF8(Object o) throws Exception {
		return encode(o, "UTF-8");
	}

	
	/**
	 * 对 类 中的 String 属性进行 decode 操作
	 * @param o
	 * @param charSet - 默认是 系统字符集
	 * @throws Exception
	 */
	public static Object decode(Object o, String charSet) throws Exception {
		return changeCode("D", o, charSet);
	}
	/**
	 * 对 类 中的 String 属性进行 decode 操作，字符集使用 UTF-8
	 * @param o
	 * @throws Exception
	 */
	public static Object decodeByUTF8(Object o) throws Exception {
		return decode(o, "UTF-8");
	}
	
	
	
	
	
	
	/**
	 * 获取 object 对象的 field 属性的getter方法 
	 */
	public static Method initGetMethod(Object o, Field f) throws Exception {
		String methodName = "get" + upperCaseFirstChar(f.getName());
		return o.getClass().getMethod(methodName);
	}
	/**
	 * 获取 object 对象的 field 属性的setter方法
	 */
	public static Method initSetMethod(Object o, Field f, Class<?> p) throws Exception {
		String methodName = "set" + upperCaseFirstChar(f.getName());
		return o.getClass().getMethod(methodName, p);
	}
	/**
	 * 把一个字符串的首字母大写
	 */
	public static String upperCaseFirstChar(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	/**
	 * 获取对象里的所有属性名，返回数组
	 * */
	public static String[] getFiledName(Object o){
		Field[] fields=o.getClass().getDeclaredFields();
		String[] fieldNames=new String[fields.length];
		for(int i=0;i<fields.length;i++){
			fieldNames[i]=fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 根据属性名获取属性值
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
//			String methodName = "get" + upperCaseFirstChar(fieldName);
			String methodName = ("get" + fieldName).replace("_","");
			Method method = o.getClass().getMethod(methodName, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
