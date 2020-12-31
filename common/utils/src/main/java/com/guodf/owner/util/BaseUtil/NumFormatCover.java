package com.guodf.owner.util.BaseUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang.StringUtils;

public class NumFormatCover {
	/**
	 * 计算并格式化百分数
	 * @param x double
	 * @param y double
	 * @return x/y
	 */
	public static String getPercent(double x, double y) {
		if (x == 0 && y == 0) {
			return "0%";
		}
		if (y == 0) {
			return "100%";
		}
		double percent = x / y;
		DecimalFormat df = new DecimalFormat("#0.00%");
		String r = df.format(percent);
		if (r.equals("0.00%")) {
			r = "0%";
		} else if (r.equals("100.00%")) {
			r = "100%";
		}
		return r;
	}
	 
	/**
	 * 计算并格式化百分数
	 * @param x 纯数字串或百分数
	 * @param y 纯数字串或百分数
	 * @return (x-y)/y
	 */
	public static String getVaryPercent(String x, String y) {
		String sx = StringUtils.isBlank(x) ? "0" : x;
		String sy = StringUtils.isBlank(y) ? "0" : y;
		double dx = 0, dy = 0;
		if (sx.contains("%")) {
			sx = sx.substring(0, sx.length() - 1);
			dx = Double.parseDouble(sx) / 100;
		} else {
			dx = Double.parseDouble(sx);
		}
		if (sy.contains("%")) {
			sy = sy.substring(0, sy.length() - 1);
			dy = Double.parseDouble(sy) / 100;
		} else {
			dy = Double.parseDouble(sy);
		}
		return getPercent(dx - dy, dy);
	}

	/**
	 * 计算并格式化百分数
	 * @param x 纯数字串或百分数
	 * @param y 纯数字串或百分数
	 * @return (x-y)/x
	 */
	public static String getVaryPercent2(String x, String y) {
		String sx = StringUtils.isBlank(x) ? "0" : x;
		String sy = StringUtils.isBlank(y) ? "0" : y;
		double dx = 0, dy = 0;
		if (sx.contains("%")) {
			sx = sx.substring(0, sx.length() - 1);
			dx = Double.parseDouble(sx) / 100;
		} else {
			dx = Double.parseDouble(sx);
		}
		if (sy.contains("%")) {
			sy = sy.substring(0, sy.length() - 1);
			dy = Double.parseDouble(sy) / 100;
		} else {
			dy = Double.parseDouble(sy);
		}
		return getPercent(dx - dy, dx);
	}
	 
	/**
	 * 计算并格式化百分数
	 * @param x 纯数字串或百分数
	 * @param y 纯数字串或百分数
	 * @return x/y
	 */
	public static String getPercent(String x, String y) {
		String sx = StringUtils.isBlank(x) ? "0" : x;
		String sy = StringUtils.isBlank(y) ? "0" : y;
		double dx = 0, dy = 0;
		if (sx.contains("%")) {
			sx = sx.substring(0, sx.length() - 1);
			dx = Double.parseDouble(sx) / 100;
		} else {
			dx = Double.parseDouble(sx);
		}
		if (sy.contains("%")) {
			sy = sy.substring(0, sy.length() - 1);
			dy = Double.parseDouble(sy) / 100;
		} else {
			dy = Double.parseDouble(sy);
		}
		return getPercent(dx, dy);
	}
	 
	/**
	 * 格式化两位小数
	 * @param x - int
	 * @param y - int
	 * @return x/y->xxx.xx
	 */
	public static String getDecimalForTwo(int x, int y) {
		double decimalX = x * 1.0;
		double decimalY = y * 1.0;
		return getDecimalForTwo(decimalX, decimalY);
	}
	/**
	 * 格式化两位小数
	 * @param x - double
	 * @param y - double
	 * @return x/y->xxx.xx
	 */
	public static String getDecimalForTwo(double x, double y) {
		double decimal = x / y;
		NumberFormat df = new DecimalFormat("0.00");
//		NumberFormat df = new DecimalFormat("#.00");
		String decm = df.format(decimal);
		if(decm.equals("0.00") || decm.equals(".00")) {
			decm="0";
		}
		return decm;
	}
	
	/**
	 * 如果double类型小数为0，则忽略小数点后
	 * @param d
	 * @return
	 */
	public static String doubleToInt(double d){
		double c= d-(int)d;
		String result = "";
		if(c==0){
			result = (int)d+"";
		}else{
			result = d+"";
		}
		return result;
	}
	 
	/**
	 * 分转元
	 */
	public static String fenToYuan(Object fen) {
		if (fen==null || StringUtils.isBlank(String.valueOf(fen))) {
			fen = 0;
		}
		BigDecimal f = new BigDecimal(String.valueOf(fen).trim());
		BigDecimal m = new BigDecimal("100");
		return f.divide(m, 2, BigDecimal.ROUND_DOWN).toString();
	}
}
