package com.guodf.owner.util.BaseUtil;

import com.guodf.owner.common.constant.BaseConstant;
import org.apache.commons.lang.StringUtils;

public class BasicBusinessUtil {
	/**
	 * 过滤标识串
	 * 位数不足时后面补0，位数见BaseConstant.COUNT_KEY_LENGTH
	 * @param termKey
	 * @return
	 */
	public static String spellTermKey(String termKey) {
		termKey = StringUtils.isBlank(termKey)?"":termKey;
		while (termKey.length() < BaseConstant.TERM_KEY_LENGTH) {
			termKey += "0";
		}
		return termKey;
	}
}
