package com.guodf.owner.util.LogUtils;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.guodf.owner.BaseOut;
import net.sf.json.JSONObject;
import org.slf4j.Logger;


public class LogUtil {
	/**
	 * 获取stamp
	 * 初期设计stamp为当前时间毫秒值，但高并发情况下无法保证唯一，故增加此方法毫秒值后拼10000内随机数，尽量降低重复几率
	 * @return long
	 */
	public static long getStamp(){
		long currTime = System.currentTimeMillis();
		int randNum = (int)(Math.random()*10000);
		String r = String.valueOf(currTime) + String.valueOf(randNum);
		return Long.parseLong(r);
	}
	
	/**
	 * 输出 rest开始日志
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息
	 */
	public static void start(Logger log, long stamp, String m){
		start(log, stamp, m, null);
	}
	
	/**
	 * 输出开始标识日志 和 入参字符串
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息
	 * @param o		Request对象或Map对象
	 */
	@SuppressWarnings("rawtypes")
	public static void start(Logger log, long stamp, String m, Object o){
		if (log != null) {
			log.info("["+stamp+"]" + m + " start >>>>>");
			if (o != null) {
				if (o instanceof HttpServletRequest) {
					HttpServletRequest request = (HttpServletRequest)o;
					Enumeration keys = request.getParameterNames();
					Map<String, String> paramMap = new LinkedHashMap<String, String>();
					while (keys.hasMoreElements()) {
						String key = keys.nextElement().toString();
						String value = request.getParameter(key);
						paramMap.put(key, value);
					}
					info(log, stamp, "入参："+paramMap.toString());
				} else {
					info(log, stamp, "入参："+o.toString());
				}
			}
		}
	}
	
	/**
	 * 输出 rest结束日志
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息，如：忠诚度数据获取
	 */
	public static void end(Logger log, long stamp, String m){
		end(log, stamp, m, null);
	}
	
	/**
	 * 输出 rest结束日志
	 * @param log
	 * @param stamp
	 * @param m
	 * @param out - BaseOut
	 */
	public static void end(Logger log, long stamp, String m, BaseOut out){
		if (log != null) {
			if (out != null) {
				log.info("["+stamp+"]出参："+JSONObject.fromObject(out).toString());
			}
			log.info("["+stamp+"]<<<<< " + m + " end");
		}
	}
	
	/**
	 * 输出出参对象和结束标识日志
	 * @param log
	 * @param stamp
	 * @param m
	 * @param out - Object
	 */
	public static void end(Logger log, long stamp, String m, Object out){
		if (log != null) {
			if (out != null) {
				log.info("["+stamp+"]出参："+JSONObject.fromObject(out).toString());
			}
			log.info("["+stamp+"]<<<<< " + m + " end");
		}
	}
	
	/**
	 * 输出 info 日志
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息
	 * @return [时间戳]提示信息
	 */
	public static void info(Logger log, long stamp, String m){
		if (log != null) {
			log.info("["+stamp+"]" + m);
		}
	}
	
	/**
	 * 输出 error 日志
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息
	 * @param t		异常实体
	 * @return [时间戳]提示信息
	 */
	public static void error(Logger log, long stamp, String m, Throwable t){
		if (log != null) {
			log.error("["+stamp+"]" + m, t);
		}
	}
	
	/**
	 * 输出 debug 日志
	 * @param log	logger实体
	 * @param stamp	时间戳
	 * @param m		提示信息
	 */
	public static void debug(Logger log, long stamp, String m){
		if (log != null) {
			log.debug("["+stamp+"]" + m);
		}
	}
}
