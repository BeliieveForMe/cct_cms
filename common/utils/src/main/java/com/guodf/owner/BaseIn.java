package com.guodf.owner;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import lombok.Data;

/**
 * 公用入参基类
 * 新增字段时，新增独立属性和在PMap中新增对应关系即可
 */
@Data
@Slf4j
public class BaseIn implements Serializable {
	private static final long serialVersionUID = 3573776415206646427L;
	
	/** 分支标识 **/
	private String branch;
	/** hbase 表空间 **/
	private String nameSpace;
	
	/** 渠道标识 **/
	private String sys_id;
	/** 访问日期类型 **/
	private String dateType;
	/** 时间段 **/
	private String dateRange;
	/** 是否对比标识 **/
	private String compareFlag;
	/** 对比日期类型 **/
	private String compareDateType;
	/** 对比时间段（compareDateType=R时启用） **/
	private String compareDateRange;
	/** 统计类型 **/
	private String statsType;
	/** 指标 **/
	private String target;
	/** 访客类型 **/
	private String visitorType;
	/**
	 * 终端设备类型
	 * 默认 0-PC端，1-H5
	 */
	private String deviceType = "0";
	/** 浏览器类型 **/
	private String browserType;
	/** Url入参 **/
	private String url;
	/** 展示类型 **/
	private String showType;
	/** 业务流程标识 **/
	private String busiKey;
	/** 来源渠道标识，多个用 , 分割 **/
	private String source;
	/** 用户号码 **/
	private String nbr;
	/** 指标标识0 大类标识，适用于业务类接口入参 **/
	private String code0;
	/** 指标标识1 **/
	private String code1;
	/** 指标标识2 **/
	private String code2;
	/** 指标标识2名称 **/
	private String code2Name;
	/** 页面指标标识 **/
	private String page;
	/** AB分析转化流失标识 0:是转化   1:流失  **/
	private String isTrans;
	/** 接口特殊标识，属于附加参数，可不用*/
	private String item;
	/** 登录工号 */
	private String workNo;
	/** 批次id */
	private String batch_id;
	/** 邮箱 */
	private String email;
	
	
	
	
	
	// Map<属性名称，前端传入参数名称>
	private static Map<String, String> pMap;
	static {
		pMap = new HashMap<>();
		pMap.put("branch", "branch");
		pMap.put("nameSpace", "name_space");
		pMap.put("sys_id", "sys_id");
		pMap.put("dateType", "date_type");
		pMap.put("dateRange", "date_range");
		pMap.put("compareFlag", "compare_flag");
		pMap.put("compareDateType", "compare_date_type");
		pMap.put("compareDateRange", "compare_date_range");
		pMap.put("statsType", "stats_type");
		pMap.put("target", "target");
		pMap.put("visitorType", "visitor_type");
		pMap.put("deviceType", "device_type");
		pMap.put("browserType", "browser_type");
		pMap.put("busiKey", "busi_key");
		pMap.put("url", "url_str");
		pMap.put("showType", "show_type");
		pMap.put("source", "source");
		pMap.put("nbr", "nbr");
		pMap.put("code0", "code0");
		pMap.put("code1", "code1");
		pMap.put("code2", "code2");
		pMap.put("code2Name", "code2_name");
		pMap.put("isTrans", "isTrans");
		pMap.put("page", "page");
		pMap.put("item", "item");
		pMap.put("workNo", "work_no");
		pMap.put("batch_id", "batch_id");
		pMap.put("email", "email");
	}
	
	
	public BaseIn(){}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		Field[] fs = this.getClass().getDeclaredFields();
		for (Field f : fs) {
			if ("serialVersionUID".equals(f.getName())) {
				continue;
			}
			sb.append("[" + f.getName() + "=");
			try {
				sb.append(f.get(this));
			} catch (Exception e) {
				sb.append("*属性不可查询*");
			}
			sb.append("]");
		}
		
		return sb.toString();
	}
	
}
