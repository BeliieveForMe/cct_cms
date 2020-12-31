package com.guodf.owner.util.IccdUtils;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

public class IccdUtil {
	 /** 
     * 根据身份证信息查询对应年龄，性别，出生年、月、日 小工具
     * 将15位身份证号转化为18位返回，非15位身份证号原值返回 
     * @param identityCard 
     * @return 
     */  
    public static String get18Ic(String identityCard) {
    	String retId = "";  
    	if(StringUtils.isNotBlank(identityCard)) {
    		String id17 = "";  
    		int sum = 0;  
    		int y = 0;  
    		// 定义数组存放加权因子（weight factor）  
    		int[] wf = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };  
    		// 定义数组存放校验码（check code）  
    		String[] cc = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };  
    		if (identityCard.length() != 15) {  
    			return identityCard;  
    		}  
    		// 加上两位年19  
    		id17 = identityCard.substring(0, 6) + "19" + identityCard.substring(6);  
    		// 十七位数字本体码加权求和  
    		for (int i = 0; i < 17; i++) {  
    			sum = sum + Integer.valueOf(id17.substring(i, i + 1)) * wf[i];  
    		}  
    		// 计算模  
    		y = sum % 11;  
    		// 通过模得到对应的校验码 cc[y]  
    		retId = id17 + cc[y];  
    	}
    	return retId;  
    }  
    
    /**
     * 根据身份编号获取年龄
     * @param idCardNo
     * @return  iAge=-1 入参为空
     */
    public static int getAgeByIdCard(String idCardNo) {
        int iAge = -1;
        if(StringUtils.isNotBlank(idCardNo)) {
        	String idCardNo18 = get18Ic(idCardNo);
        	Calendar cal = Calendar.getInstance();
        	String year = idCardNo18.substring(6, 10);
        	int iCurrYear = cal.get(Calendar.YEAR);
        	iAge = iCurrYear - Integer.valueOf(year);        	
        }
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     * @param idCardNo 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCardNo) {
    	 if(StringUtils.isNotBlank(idCardNo)) {
    		 String idCardNo18 = get18Ic(idCardNo);
    		 return idCardNo18.substring(6, 14);
    	 }else {
    		 return null;
    	 }
    }

    /**
     * 根据身份编号获取生日年
     * @param idCardNo 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCardNo) {
    	if(StringUtils.isNotBlank(idCardNo)) {
    		String idCardNo18 = get18Ic(idCardNo);
    		return Short.valueOf(idCardNo18.substring(6, 10));
    	}else {
    		return null;
    	}
    }

    /**
     * 根据身份编号获取生日月
     * @param idCardNo
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCardNo) {
    	if(StringUtils.isNotBlank(idCardNo)) {
    		String idCardNo18 = get18Ic(idCardNo);
    		return Short.valueOf(idCardNo18.substring(10, 12));
    	}else {
    		return null;
    	}
    }

    /**
     * 根据身份编号获取生日天
     * @param idCardNo
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCardNo) {
    	if(StringUtils.isNotBlank(idCardNo)) {
    		String idCardNo18 = get18Ic(idCardNo);
    		return Short.valueOf(idCardNo18.substring(12, 14));
    	}else {
    		return null;
    	}
    }

    /**
     * 根据身份编号获取性别
     * @param idCardNo 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCardNo) {
    	String sGender = null;
    	if(StringUtils.isNotBlank(idCardNo)) {
    		String idCardNo18 = get18Ic(idCardNo);
    		String sCardNum = idCardNo18.substring(16, 17);
    		if (Integer.parseInt(sCardNum) % 2 != 0) {
    			sGender = "男";
    		} else {
    			sGender = "女";
    		}
    	}
        return sGender;
    }
}
