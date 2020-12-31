package com.guodf.owner.util.BaseUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

public class TemplateUtil {
	/**
	 * 模板内容替换，参数按名称替换
	 * @param template 模板内容
	 * @param argMap<关键字, 替换内容>
	 * @return
	 */
	public static String templateRepalce(String template, Map<String, String> argMap) {
		if (StringUtils.isNotBlank(template) && MapUtils.isNotEmpty(argMap)) {
			for (String key : argMap.keySet()) {
				template = template.replaceAll("\\{"+key+"\\}", argMap.get(key));
			}
		}
		return template;
	}
	
	/**
	 * 模板内容替换，先按参数名称替换，再日期/时间参数替换
	 * @param template 模板内容
	 * @param argMap<关键字, 替换内容>
	 * @param dt
	 * @return
	 */
	public static String templateRepalce(String template, Map<String, String> argMap, DateTime dt) {
		template = templateRepalce(template, argMap);
		if (dt != null) {
			Map<String, String> dtMap = new HashMap<>();
			dtMap.put("yMd", dt.toString("yyyyMMdd"));
			dtMap.put("yyyyMMdd", dt.toString("yyyyMMdd"));
			dtMap.put("yyyymmdd", dt.toString("yyyyMMdd"));
			
			dtMap.put("yMdHms", dt.toString("yyyyMMddHHmmss"));
			dtMap.put("yyyyMMddHHmmss", dt.toString("yyyyMMddHHmmss"));
			dtMap.put("yyyyMMddHHmiss", dt.toString("yyyyMMddHHmmss"));
			dtMap.put("yyyyMMddhhmmss", dt.toString("yyyyMMddHHmmss"));
			dtMap.put("yyyyMMddhhmiss", dt.toString("yyyyMMddHHmmss"));
			
			dtMap.put("yMdHmsS", dt.toString("yyyyMMddHHmmssSSS"));
			dtMap.put("yyyyMMddHHmmssSSS", dt.toString("yyyyMMddHHmmssSSS"));
			dtMap.put("yyyyMMddHHmissSSS", dt.toString("yyyyMMddHHmmssSSS"));
			dtMap.put("yyyyMMddhhmmssSSS", dt.toString("yyyyMMddHHmmssSSS"));
			dtMap.put("yyyyMMddhhmissSSS", dt.toString("yyyyMMddHHmmssSSS"));

			dtMap.put("stamp", String.valueOf(dt.getMillis()));
			
			dtMap.put("date", dt.toString("yyyyMMdd"));
			dtMap.put("time", dt.toString("yyyyMMddHHmmss"));
			
			template = templateRepalce(template, dtMap);
		}
		return template;
	}
	
	/**
	 * 模板内容替换，参数按顺序替换
	 * @param template 模板字符串
	 * @param argArray 替换内容，注意顺序
	 */
	public static String templateRepalce(String template,Object[] argArray){
		if (StringUtils.isNotBlank(template) && argArray!=null && argArray.length>0) {
			template = MessageFormat.format(template, argArray);
		}
		return template;
	}

	//获取主键uuid
	public static String getUUID32() {
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		return uuid;
		//  return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}



}
