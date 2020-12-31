package com.guodf.owner.util.BaseUtil;

import java.util.HashMap;
import java.util.Map;

import com.guodf.owner.DateJsonValueProcessor;
import com.guodf.owner.common.constant.BaseConstant;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;

public class EnumUtil {
	/**
	 * 根据sysId获取渠道名称
	 * @param sysId
	 * @return
	 */
	public static String getChannelName(String sysId) {
		Map<String, String> channelMap = new HashMap<String, String>();
		//加个判空校验，空的话返回“您的入参渠道ID为空，请确认”
		return channelMap.containsKey(sysId)?channelMap.get(sysId):sysId;
	}
	
	/**
	 * 自定义出参规则，排除为空
	 * @return
	 */
	public static JsonConfig propertyFilter(){
		JsonConfig jsonConfig = new JsonConfig();
	    jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
	    PropertyFilter propertyFilter = new PropertyFilter() {
            @Override
            public boolean apply(Object obj, String key, Object value) {
                if (null == value||"".equals(value)) {//对象为空||字符串为空
                    return true;
                }
                return false;
            }
        };
	    jsonConfig.setJsonPropertyFilter(propertyFilter);
	    return jsonConfig;
	}
	
	/**
	 * 自定义出参规则，排除为空，并格式化date类型变量
	 * @return
	 */
	public static JsonConfig propertyFilterAndDateFormat(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		PropertyFilter propertyFilter = new PropertyFilter() {
			@Override
			public boolean apply(Object obj, String key, Object value) {
				if (null == value||"".equals(value)) {//对象为空||字符串为空
					return true;
				}
				return false;
			}
		};
		jsonConfig.setJsonPropertyFilter(propertyFilter);
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
		return jsonConfig;
	}
}
