package com.guodf.owner.util.BaseUtil;

import org.springframework.boot.SpringApplication;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class MapUtils {
	public static String getStrVal(Map in,String key){
		return in.get(key)==null ? "" : in.get(key).toString();
	}
	
	//为空时处理成0,类型转换时使用e
	public static String getStrVal0(Map in,String key){
		return in.get(key)==null ? "0" : in.get(key).toString();
	}

	public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
	        return (sourceMap == null || sourceMap.size() == 0);
	}
	public static <K, V> boolean isNotEmpty(Map<K, V> sourceMap) {
		return (sourceMap != null && sourceMap.size() > 0);
	}

	public static String getString(Map in,String key){
		String value = in.get(key)==null ? "" : in.get(key).toString();
		if("NULL".equals(value.toUpperCase().trim())){
			return "";
		}else{
			return value;
		}
	}



}
