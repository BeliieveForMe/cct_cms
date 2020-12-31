package com.guodf.owner;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 出参基类
 * @author Yangkaia
 */
@Data
@SuppressWarnings("rawtypes")
public class BaseOut implements Serializable{
	private static final long serialVersionUID = 125529367717250320L;

	/** 结果值，默认:失败 **/
	private int result ;

	/** 提示语，默认：失败提示语 **/
	private String msg;

	/** 时间戳 **/
	private long stamp;
	
	/** 对象载体 */
	private Object info;
	
	/** 列表载体(list元素以对象形式输出) **/
	private List infoLst;
	
	/** 渠道标识 **/
	private String sys_id;
	
	
	public BaseOut(){}
}
