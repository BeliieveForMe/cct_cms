package com.guodf.owner.util.PhoneUtils;

import com.guodf.owner.common.constant.BaseConstant;

import java.util.regex.Pattern;


public class PhoneUtil {
	/**
	 * 通过手机号判断所属运营商
	 * @param phone
	 * @return
	 */
	public static String phoneOperators(String phone) {  
        
		Boolean isPhone =false;
		String phoneName = "其他";
		isPhone = Pattern.matches(BaseConstant.MOBILE_PATTERN, phone);
		if(isPhone ==true) {
			phoneName = "移动";
		}else {
			isPhone = Pattern.matches(BaseConstant.UNICOM_PATTERN, phone);
			if(isPhone ==true) {
				phoneName = "联通";
			}else {
				isPhone = Pattern.matches(BaseConstant.TELECOM_PATTERN, phone);
				if(isPhone ==true) {
					phoneName = "电信";
				}
			}
		}
		return phoneName;
    }  
}
