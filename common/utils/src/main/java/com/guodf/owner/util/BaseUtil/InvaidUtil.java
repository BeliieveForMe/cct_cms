package com.guodf.owner.util.BaseUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.guodf.owner.BaseIn;
import com.guodf.owner.common.constant.BaseConstant;
import com.guodf.owner.util.ListUtils.ListUtil;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class InvaidUtil {
	/**
	 * 校验黑名单关键字
	 * blackKey格式：(key1),(key2)...
	 * @return true-校验通过，false-校验不通过
	 */
	public static boolean validBlackKey(String source, String blackKey) {
		if (StringUtils.isNotBlank(source) && StringUtils.isNotBlank(blackKey)) {
			String[] keys = blackKey.split(",");
			for (String key : keys) {
				if (source.contains(key.subSequence(1, key.length()-1))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 校验 是否包含
	 * @param reg	合集，如：1,2,3,4,5
	 * @param v		被校验值，如：3
	 * @param isIgnore 是否忽略大小写 true-忽略,false-不忽略
	 * @return boolean
	 */
	public static boolean chkContains(String reg, Object v, boolean isIgnore){
		if (StringUtils.isBlank(reg) || v==null || StringUtils.isBlank((String)v)) {
			return false;
		}
		String val = String.valueOf(v);
		if (isIgnore) {
			reg = reg.toUpperCase();
			val = val.toUpperCase();
		}
		List<String> regLst = new ArrayList<String>();
		for (String regV : reg.split(",")) {
			regLst.add(regV);
		}
		String[] vArray = val.split(",");
		for (String tmpV : vArray) {
			if (!regLst.contains(tmpV)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 日期入参校验
	 * @param dateType 访问日期类型：0-今天，1-昨天，7-最近7天，30-最近30天，R-其他时间
	 * @param dateRange 其他时间（dateType=R时启用），例："20160222" 或 "20160221,20160225"(用逗号分隔)
	 * @param compFlag 是否对比标识：0-不对比（默认），1-对比
	 * @param compDateType 对比日期类型：0-今天，1-昨天，7-最近7天，30-最近30天，R-其他时间
	 * @param compDateRange 对比其他时间（compareDateType=R时启用），例："20160222" 或 "20160221,20160225"(用逗号分隔)
	 * @return
	 */
	public static boolean chkDate(String dateType, String dateRange,
			String compFlag, String compDateType, String compDateRange){
		if (StringUtils.isBlank(dateType)) {
			return false;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(BaseConstant.DATE_FORMAT);
		if (!chkContains(BaseConstant.DATE_TYPE_REG, dateType, true)) {
			return false;
		}
		if (BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(dateType)) {
			if (StringUtils.isBlank(dateRange)) {
				return false;
			}
			try {
				for (String tmp : dateRange.split(",")) {
					@SuppressWarnings("unused")
					Date tmpDate = dateFormat.parse(tmp);
				}
			} catch (Throwable e) {
				return false;
			}
		}
		DateTime now = new DateTime();
		if (BaseConstant.DATE_TYPE_MONTH.equalsIgnoreCase(dateType)) {
			// 时间字符串格式校验
			if (!chkDateFormat(dateRange, BaseConstant.DATE_FORMAT_YM)) {
				return false;
			}
			// 超过当前月份，直接返回失败
			DateTime _d = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT_YM).parseDateTime(dateRange);
			if (now.compareTo(_d) == -1) {
				return false;
			}
		}
		if (StringUtils.isNotBlank(compFlag) && !chkContains(BaseConstant.COMPARE_FLAG_REG, compFlag, true)) {
			return false;
		} else if (BaseConstant.COMPARE_FLAG_Y.equalsIgnoreCase(compFlag)) {
			if (!chkContains(BaseConstant.DATE_TYPE_REG, compDateType, true)) {
				return false;
			}
			if (BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(compDateType)) {
				if (StringUtils.isBlank(compDateRange)) {
					return false;
				}
				try {
					for (String tmp : compDateRange.split(",")) {
						@SuppressWarnings("unused")
						Date tmpDate = dateFormat.parse(tmp);
					}
				} catch (Throwable e) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 校验时间字符串格式
	 * 
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static boolean chkDateFormat(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr)) {
			return false;
		}
		if (StringUtils.isBlank(format)) {
			format =BaseConstant.DATE_FORMAT;
		}
		try {
			@SuppressWarnings("unused")
			Date tmpDate = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	/**
	 * 入参校验
	 * @param stamp - 调用rest类时间戳
	 * @param baseReq
	 * @return
	 */
	public static String chkInParam(long stamp, BaseIn baseReq, String targetReg) {
		//LogUtil.info(log, stamp, "入参列表：" + baseReq.toString());
		String errorMsg = "";
		if (!chkDate(baseReq.getDateType(), baseReq.getDateRange(), baseReq.getCompareFlag(), baseReq.getCompareDateType(), baseReq.getCompareDateRange())) {
			errorMsg = "日期参数错误";
		} else if (!chkContains(targetReg, baseReq.getTarget(), true)) {
			//LogUtil.info(log, stamp, "可选指标合集：["+targetReg+"]");
			List<String> regLst = ListUtil.array2List(targetReg.split(","));
			if (StringUtils.isBlank(baseReq.getTarget())) {
				//LogUtil.info(log, stamp, "指标字段为空，校验不通过");
			} else {
				String[] tArr = baseReq.getTarget().split(",");
				for (String t : tArr) {
					if (!regLst.contains(t)) {
						//LogUtil.info(log, stamp, "指标["+t+"]校验不通过");
					}
				}
			}
			errorMsg = "指标参数错误";
		} else if (!chkContains(BaseConstant.STATS_TYPE_REG, baseReq.getStatsType(), true)) {
			errorMsg = "统计类型参数错误";
		} else if (!chkContains(BaseConstant.VISITOR_TYPE_REG, baseReq.getVisitorType(), true)) {
			errorMsg = "访客类型参数错误";
		} else if (!chkContains(BaseConstant.DEVICE_TYPE_REG, baseReq.getDeviceType(), true)) {
			errorMsg = "终端设备类型参数错误";
		}
		return errorMsg;
	}

	/**
	 * 数据接口入参校验
	 * 不校验指标集合，增加月份校验
	 * @param stamp
	 * @param baseReq
	 * @return
	 */
	public static String chkInParam(long stamp, BaseIn baseReq) {
		//LogUtil.info(log,stamp, "入参列表：" + baseReq.toString());
		//校验渠道编码
		if (StringUtils.isBlank(baseReq.getSys_id())){
			return "渠道编码不能为空";
		}
		
		/* 统计日期类型 M-按月 R-时间段 */
		String dateType = baseReq.getDateType();
		if (!BaseConstant.DATE_TYPE_MONTH.equalsIgnoreCase(dateType) && !BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(dateType) && !chkDate(baseReq.getDateType(), baseReq.getDateRange(), baseReq.getCompareFlag(), baseReq.getCompareDateType(), baseReq.getCompareDateRange())) {
			return "时间类型参数错误";
		}
		/* 时间 按月时传入yyyyMM，按时间段时传入 yyyyMMdd 或者 yyyyMMdd,yyyyMMdd */
		String dateRange = baseReq.getDateRange();
		if ((BaseConstant.DATE_TYPE_MONTH.equalsIgnoreCase(dateType) || BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(dateType))
				&& StringUtils.isBlank(dateRange)) {
			return "时间参数错误";
		}

		DateTime now = new DateTime();
		if (BaseConstant.DATE_TYPE_MONTH.equalsIgnoreCase(dateType)) {
			// 时间字符串格式校验
			if (!chkDateFormat(dateRange, BaseConstant.DATE_FORMAT_YM)) {
				return "时间参数格式错误";
			}
			// 超过当前月份，直接返回失败
			DateTime _d = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT_YM).parseDateTime(dateRange);
			if (now.compareTo(_d) == -1) {
				return "时间参数超过当前日期，无数据";
			}
		}else if (BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(dateType)) {
			String[] _dArray = dateRange.split(",");
			// 时间字符串格式校验
			for (String tmp : _dArray) {
				if (!chkDateFormat(tmp,BaseConstant.DATE_FORMAT)) {
					return "时间参数格式错误";
				}
			}
			// 开始时间（第一个时间）超过当前时间，直接返回失败
			DateTime _d = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT).parseDateTime(_dArray[0]);
			if (now.compareTo(_d) == -1) {
				return "时间参数超过当前日期";
			}
		}
		return null;
	}
	/**
	 * 自定义出参规则，排除为空
	 *
	 * @return
	 */
	public static JsonConfig propertyFilter() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		PropertyFilter propertyFilter = new PropertyFilter() {
			@Override
			public boolean apply(Object obj, String key, Object value) {
				if (null == value || "".equals(value)) {//对象为空||字符串为空
					return true;
				}
				return false;
			}
		};
		jsonConfig.setJsonPropertyFilter(propertyFilter);
		return jsonConfig;
	}
}	
