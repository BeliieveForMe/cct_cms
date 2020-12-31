package com.guodf.owner.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang.StringUtils;

@Slf4j
public class ConfigUtil {
	public static String propertiesName="application.properties";//配置读取哪个properties文件
	
	/**
	 * 获取config.properties文件中的value
	 * @param key
	 */
	public static String getValue(String key) {
		return getValue(propertiesName, key);
	}

	/**
	 * 获取value
	 * @param fileName
	 * @param key
	 */
	public static String getValue(String fileName, String key) {
		PropertiesConfiguration config = getProperties(fileName);
		return config == null ? null : config.getString(key);
	}
	
	/**
	 * 获取配置文件中所有的key值
	 * @param fileName
	 */
	public static List<String> getKeys(String fileName) {
		PropertiesConfiguration config = getProperties(fileName);
		List<String> keyStrs = new ArrayList<String>();
		if (config == null) {
			return null;
		} else {
			@SuppressWarnings("rawtypes")
			Iterator keys = config.getKeys();
			while (keys.hasNext()) {
				keyStrs.add((String) keys.next());
			}
			return keyStrs;
		}
	}

	/**
	 * 根据文件名称获取PropertiesConfiguration对象
	 * @param fileName 文件名，如：config.properties
	 */
	public static PropertiesConfiguration getProperties(String fileName) {
		return getProperties(fileName, 0);
	}
	
	/**
	 * 根据文件名称获取PropertiesConfiguration对象
	 * @param fileName 文件名，如：config.properties
	 * @param delay 文件变更后延迟加载时间
	 */
	public static PropertiesConfiguration getProperties(String fileName, int delay) {
		try {
			if (StringUtils.isBlank(fileName)) {
				return null;
			}
			if (!fileName.endsWith(".properties")) {
				fileName += ".properties";
			}
		
			PropertiesConfiguration config = new PropertiesConfiguration(fileName);
			FileChangedReloadingStrategy change = new FileChangedReloadingStrategy();
			if (delay > 0) {
				change.setRefreshDelay(delay);
				config.setReloadingStrategy(change);
			} else if (delay == 0) {
				change.setRefreshDelay(60000L);
				config.setReloadingStrategy(change);
			}
			return config;
		} catch (Throwable t) {
			log.error("配置文件["+fileName+"]加载失败", t);
			return null;
		}
	}
}
