package com.guodf.owner.util.DateUtil;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import com.guodf.owner.BaseIn;
import com.guodf.owner.common.constant.BaseConstant;
import com.guodf.owner.util.ListUtils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

@Slf4j
public class DateUtil {
	/**
	 * 获取时间集合
	 * @param dateType
	 * @param dateRange
	 * @return
	 * @throws Throwable
	 */
	public static List<String> getDateLst(String dateType, String dateRange) throws Throwable{
		List<String> dateLst = new ArrayList<String>();
		DateTime now = new DateTime();// 今天
		DateTime yesterday = now.minusDays(1);// 昨日
		SimpleDateFormat sdf = new SimpleDateFormat(BaseConstant.DATE_FORMAT);
		String starts = null, ends = null;
		if (BaseConstant.DATE_TYPE_RANGE.equalsIgnoreCase(dateType)) {
			String[] tmpDateRange = dateRange.split(",");
			if (tmpDateRange.length == 1) {
				dateLst.add(tmpDateRange[0]);
			} else {
				Calendar tmpDate = Calendar.getInstance();
				tmpDate.setTime(sdf.parse(tmpDateRange[1]));
				Date startDate = sdf.parse(tmpDateRange[0]);
				Date endDate = tmpDate.getTime();
				while (endDate.getTime() >= startDate.getTime()) {
					dateLst.add(sdf.format(endDate));
					tmpDate.add(Calendar.DAY_OF_YEAR, -1);
					endDate = tmpDate.getTime();
				}
			}
		}else if(BaseConstant.DATE_TYPE_MONTH.equalsIgnoreCase(dateType)) {
			// dateType=M	dateRange=yyyyMM
			DateTime dt01 = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT_YM).parseDateTime(dateRange.trim());
			starts = dt01.toString(BaseConstant.DATE_FORMAT);
			if (dt01.getYear() == yesterday.getYear()
					&& dt01.getMonthOfYear() == yesterday.getMonthOfYear()) {
				ends = yesterday.toString(BaseConstant.DATE_FORMAT);
			} else {
				DateTime dtLastDay = dt01.plusMonths(1).minusDays(1);
				ends = dtLastDay.toString(BaseConstant.DATE_FORMAT);
			}
			dateLst = getDateLst("R", starts+","+ends);
			
		}else if(BaseConstant.DATE_TYPE_WEEK.equalsIgnoreCase(dateType)){
			if (StringUtils.isBlank(dateRange) || "0".equals(dateRange)) {
				dateRange = yesterday();
			}
			DateTime dt = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT).parseDateTime(dateRange);
			int weekDay = dt.getDayOfWeek();
			DateTime week1 = dt.minusDays(weekDay - 1);// 周一
			starts = week1.toString(BaseConstant.DATE_FORMAT);
			DateTime week7 = week1.plusDays(6);// 周日
			// 判断是否取当前周
			if (yesterday.getMillis() >= week1.getMillis()
					&& yesterday.getMillis() < week7.getMillis()) {
				ends = yesterday.toString(BaseConstant.DATE_FORMAT);
			} else {
				ends = week7.toString(BaseConstant.DATE_FORMAT);
			}
			dateLst = getDateLst("R", starts+","+ends);
		}else if(BaseConstant.DATE_TYPE_QUARTER.equalsIgnoreCase(dateType)){
			Calendar oneCalendar = Calendar.getInstance();
			oneCalendar.setTime(new Date());  
			oneCalendar.set(Calendar.MONTH, ((int) oneCalendar.get(Calendar.MONTH) / 3 + Integer.parseInt(dateRange)) * 3);
			oneCalendar.set(Calendar.DAY_OF_MONTH, 1); 
			Calendar twoCalendar = Calendar.getInstance();
			twoCalendar.set(Calendar.MONTH, ((int) twoCalendar.get(Calendar.MONTH) / 3 + Integer.parseInt(dateRange)) * 3+1);
			twoCalendar.set(Calendar.DAY_OF_MONTH, 1); 
			Calendar threeCalendar = Calendar.getInstance();
			threeCalendar.set(Calendar.MONTH, ((int) threeCalendar.get(Calendar.MONTH) / 3 + Integer.parseInt(dateRange)) * 3 + 2);
			threeCalendar.set(Calendar.DAY_OF_MONTH, threeCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			dateLst.add(sdf.format(threeCalendar.getTime()).substring(0, 6));
			dateLst.add(sdf.format(twoCalendar.getTime()).substring(0, 6));
			dateLst.add(sdf.format(oneCalendar.getTime()).substring(0, 6));
			
		}else if(BaseConstant.DATE_TYPE_YEAR.equalsIgnoreCase(dateType)){
			@SuppressWarnings("deprecation")
			int currentYear = (new Date()).getYear()+1900;
	       String yearsBefStr = (currentYear+Integer.parseInt(dateRange))+"";
	//       String yearsStr = (currentYear+Integer.parseInt(dateRange)+1)+"";
	//       dateLst.add(yearsBefStr);
	       for(int i=12;i>0;i--){
	       	if(i<10){
	       		dateLst.add(yearsBefStr+"0"+i);	        		        		
	       	}else{
	       		dateLst.add(yearsBefStr+i);	        		        		
	       	}
	       }
       }else {
			Calendar tmpDate = Calendar.getInstance();
			if (BaseConstant.DATE_TODAY.equalsIgnoreCase(dateType)) {// 今天
				dateLst.add(sdf.format(tmpDate.getTime()));
			} else {// 昨天、最近7天、最近30天，注意：最近天数不包含今天
				for (int i = 0; i < Integer.parseInt(dateType); i++) {
					tmpDate.add(Calendar.DAY_OF_YEAR, -1);
					dateLst.add(sdf.format(tmpDate.getTime()));
				}
			}
		}
		return dateLst;
	}
	
	/**
	 * 获取返回的时间主体
	 * @param dateLst
	 * @return
	 * @throws Throwable
	 */
	public static String getDateItem(List<String> dateLst) throws Throwable{
		SimpleDateFormat inSdf = new SimpleDateFormat(BaseConstant.DATE_FORMAT);
		SimpleDateFormat outSdf = new SimpleDateFormat(BaseConstant.DATE_FORMAT_SHOW);
		if (dateLst.size() == 1) {// 只统计一天
			return outSdf.format(inSdf.parse(dateLst.get(0)));
		} else {
			String sD = dateLst.get(dateLst.size()-1);
			String eD = dateLst.get(0);
			return outSdf.format(inSdf.parse(sD))+" - "+outSdf.format(inSdf.parse(eD));
		}
	}
	
	/**
	 * 获取前几天的日期list
	 * @param range 时间段
	 * @return 日期格式
	 */
	public static List<String> getDateList(int range , BaseIn in) {
		List<String> dateList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		dateList.add(in.getDateRange());
		Date date = sdf.parse(in.getDateRange(), new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        for (int i = -1; i > range; i--) {
			calendar.add(Calendar.DATE , -1);
			Date date7 = calendar.getTime();
			dateList.add(sdf.format(date7));
		}
		return dateList;
	}
	
	
	 /**
     * 根据当前日期list获取上月对应日期的list
     *
     * @param dateLst
     * @return
     * @throws ParseException
     */
    public static List<String> getLastMonthLst(List<String> dateLst) throws ParseException {

        List<String> lastMonthLst = new ArrayList<String>();
        if (dateLst != null && dateLst.size() > 0) {
            for (int i = dateLst.size() - 1; i >= 0; i--) {
                String lastMonth = lastMonth(dateLst.get(i));
                String date = lastMonth + dateLst.get(i).substring(6, 8);
                if (isFalseDate(date)) {
                    continue;
                }
                lastMonthLst.add(date);
            }
        }
        return lastMonthLst;
    }

    /**
     * 根据本周日期list获取上周对应日期的list
     *
     * @param dateLst
     * @return
     * @throws Throwable
     */
//    public static List<String> getLastWeekLst(List<String> dateLst) throws Throwable {
//
//        List<String> lastWeekLst = new ArrayList<String>();
//        //获取上周一日期
//        String lastMon;
//        lastMon = getDateBefore(dateLst.get(dateLst.size() - 1), 7);
//        //根据上周日日期
//        String lastSun = getDateBefore(dateLst.get(dateLst.size() - 1), 1);
//        //获取上周的日期list
//        lastWeekLst = getDateLst("R", lastMon + "," + lastSun);
//        return lastWeekLst;
//    }

    /**
     * 根据本周日期list获取上周（整周 ）对应日期
     * @param dateLst 倒敘時間
     * @return
     * @throws Throwable
     */
    public static String getLastWeekLst(List<String> dateLst) throws Throwable{

        //获取上周一日期
        String lastMon = getDateBefore(dateLst.get(dateLst.size()-1),7);
        //根据上周日日期
        String lastSun = getDateBefore(dateLst.get(dateLst.size()-1),1);
        return lastMon+ "|" +lastSun;
    }


    /**
     * 根据日期list<yyyymmdd>获取今年月份的list<yyyymm>
     *
     * @param dateLst
     * @return
     * @throws Throwable
     */
    public static List<String> getYMLst(List<String> dateLst) throws Throwable {
        List<String> ymLst = new ArrayList<String>();
        if (ListUtil.isNotEmpty(dateLst)) {
            String lastDate = dateLst.get(0).substring(0, 6) + "01";
            int currYear = Integer.parseInt(dateLst.get(0).substring(0, 4));

            DateTime dt = DateTimeFormat.forPattern(BaseConstant.DATE_FORMAT).parseDateTime(lastDate);
            while (dt.getYear() == currYear) {
                int y = dt.getYear();
                int m = dt.getMonthOfYear();
                String mm = m < 10 ? "0" + m : "" + m;
                ymLst.add(y + mm);

                dt = dt.minusMonths(1);
            }
        }
        return ymLst;
    }

    /**
     * 前天（带 - 分隔符）
     *
     * @return
     */
    public static String beforeyesterdaySplit() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -2);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(c.getTime());
    }

    /**
     * 前天
     *
     * @return
     */
    public static String beforeyesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -2);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(c.getTime());
    }

    /**
     * 昨天（带分隔符）
     *
     * @return
     */
    public static String yesterdaySplit() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(c.getTime());
    }

    /**
     * 昨天
     *
     * @return
     */
    public static String yesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(c.getTime());
    }

    /**
     * 今天
     *
     * @return
     */
    public static String today() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(c.getTime());
    }


    /**
     * 今天
     *
     * @return
     */
    public static String currentTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return sf.format(c.getTime());
    }



    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     */
    public static Date getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return currentTime;
    }



    /**
     * 获取当前时间到yyyymmdd hh24:mi
     *
     */
    public static Date getDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date timeDate = new Date();
        try {
            synchronized(sdf){
                String time = sdf.format(date);
                timeDate = sdf.parse(time);
            }
        } catch (ParseException e) {
            log.error("日期转换发生异常，异常原因为" + e);
        }
        return timeDate;
    }
    /**
     * 当月第一天
     *
     * @return
     */
    public static String firstday() {
        Calendar c = Calendar.getInstance();//获取当前日期
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(c.getTime());
    }

    public static String currentMonthNoSplit() {
        return new SimpleDateFormat("yyyyMM").format(new Date());
    }

    /**
     * 根据给定日期字符串（yyyyMMdd）获取当月字符串
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static String currentMonthByDate(String dateStr) throws ParseException {
        SimpleDateFormat sdfym = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdfymd = new SimpleDateFormat("yyyyMMdd");
        Date ymd = sdfymd.parse(dateStr);
        return sdfym.format(ymd);
    }

    /**
     * 本月（格式：yyyy-MM）
     *
     * @return
     * @throws ParseException
     */
    public static String currentMonth(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(dateStr);
        return new SimpleDateFormat("yyyyMM").format(date);
    }

    /**
     * 获取当前日期（格式：yyyy-MM）
     *
     * @return
     * @throws ParseException
     */
    public static String nowDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
//        Date date = sdf.parse(dateStr);

        return sdf.format(date);
    }

    /**
     * 上月（格式：yyyy-MM）
     *
     * @return
     * @throws ParseException
     */
    public static String lastMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date datea = sdf.parse(date.substring(0, 6));
        Calendar c = Calendar.getInstance();
        c.setTime(datea);
        c.add(Calendar.MONTH, -1);

        SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMM");
        return sdfs.format(c.getTime());
    }

    /**
     * 获取给定日期的前几天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBefore(String specifiedDay,int i) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - i);

        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获取给定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 得到某个时间的前几天的日期数据
     *
     * @param day
     * @return
     * @throws ParseException
     */
    public static String getDateBefore(String dateStr, int day) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(BaseConstant.DATE_FORMAT);
        Calendar no = Calendar.getInstance();
        no.setTime(sdf.parse(dateStr));
        no.set(Calendar.DATE, no.get(Calendar.DATE) - day);
        return sdf.format(no.getTime());
    }

    /**
     * 得到某个时间的前几天的日期数据
     *
     * @return true-是错误数据，false-不是错误数据
     * @throws ParseException
     */
    public static boolean isFalseDate(String dateStr) throws ParseException {

        List<String> badDate = new ArrayList<>();
        badDate.add(dateStr.substring(0, 4) + "0229");
        badDate.add(dateStr.substring(0, 4) + "0230");
        badDate.add(dateStr.substring(0, 4) + "0231");
        badDate.add(dateStr.substring(0, 4) + "0431");
        badDate.add(dateStr.substring(0, 4) + "0631");
        badDate.add(dateStr.substring(0, 4) + "0831");
        badDate.add(dateStr.substring(0, 4) + "0931");
        badDate.add(dateStr.substring(0, 4) + "1131");
        boolean result = false;
        if (badDate.contains(dateStr)) {
            result = true;
        }
        return result;
    }

    /**
     * 获取给定日期上月最后一天
     *
     * @param dateStr    当前月日期
     * @param dateFormat 日期格式：yyyyMMdd
     * @return
     * @throws ParseException
     */
    public static String getLastMonth(String dateStr, String dateFormat) throws ParseException {
        // 日期格式转换
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(dateStr));
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = df.format(calendar.getTime());
        return endDate;
    }

    /**
     * 获取去年当前月开始结束时间
     *
     * @param specifiedDay
     * @return
     */
    public static String getLastYearMonthDays(String specifiedDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        c.add(Calendar.YEAR, -1);
        String beginDate = format.format(c.getTime());

        // 获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR, -1);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = format.format(ca.getTime());
        return beginDate + "|" + endDate;

    }

    /**
     * 获取上月第一天|最后一天
     */
    public static String getLastMonthDays(String specifiedDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String beginDate = format.format(c.getTime());

        // 获取当前月最后一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, -1);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = format.format(cale.getTime());
        return beginDate + "|" + endDate;
    }

    public static String getFirstDayOfGivenMonth(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MONTH, 0);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据日期 找到对应日期的星期
     * @param date
     * @return
     */
    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
            String str = formatter.format(myDate);
            dayOfweek = str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getWeekNum(dayOfweek);
    }

    /**
     * 根据星期获取数字标识
     * @param
     * @return
     */
    public static String getWeekNum(String week) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("星期一", "1");
        map.put("星期二", "2");
        map.put("星期三", "3");
        map.put("星期四", "4");
        map.put("星期五", "5");
        map.put("星期六", "6");
        map.put("星期日", "7");

        map.put("Monday", "1");
        map.put("Tuesday", "2");
        map.put("Wednesday", "3");
        map.put("Thursday", "4");
        map.put("Friday", "5");
        map.put("Saturday", "6");
        map.put("Sunday", "7");

        map.put("Mon", "1");
        map.put("Tue", "2");
        map.put("Wed", "3");
        map.put("Thu", "4");
        map.put("Fri", "5");
        map.put("Sat", "6");
        map.put("Sun", "7");
        return map.containsKey(week)?map.get(week):week;
    }

    /**
     * 获取某日期上一年同天日期
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayBeforeYear(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        // int day=c.get(Calendar.DATE);
        c.add(Calendar.YEAR, -1);
        Date y = c.getTime();
        String dayBeforeYear = new SimpleDateFormat("yyyyMMdd").format(y);
        return dayBeforeYear;
    }

    // 获取当前(上，下)周的日期范围如：...,-1上一周，0本周，1下一周...
    /**
     * 获取当前日期所在周的日期范围
     * 传入时间为本周日，返回周日，若传入时间为周1-周六，返回周六
     *
     * @param i
     *            这里 -1上一周， 0本周， 1下一周...
     */
    public static String getWeekDays(int i ,String specifiedDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar1 = Calendar.getInstance();
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        calendar1.setTime(date);
        calendar1.setFirstDayOfWeek(Calendar.MONDAY);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        int dayNum = calendar1.get(Calendar.DAY_OF_WEEK)-1;
        if (1 == dayOfWeek) {
            calendar1.add(Calendar.DAY_OF_MONTH, -1);
        }
        int day = calendar1.get(Calendar.DAY_OF_WEEK);
        // 获得当前日期是一个星期的第几天
        // 获取当前日期前（下）x周同星几的日期
        calendar1.add(Calendar.DATE, 7 * i);
        String endDay = sdf.format(calendar1.getTime());
        String endDate = endDay;
        calendar1.add(Calendar.DATE, calendar1.getFirstDayOfWeek() - day);
        String beginDate = sdf.format(calendar1.getTime());
        if (dayNum == 0) {
            calendar1.add(Calendar.DATE, 6);
            endDate = sdf.format(calendar1.getTime());
            return beginDate + "|" + endDate;
        } else {
            return beginDate + "|" + endDate;

        }
    }

    /**
     * 获取本月第一天和最后一天
     */
    public static String getMonthDays(String specifiedDay) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String beginDate = format.format(c.getTime());

        // 获取当前月最后一天
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.set(Calendar.DAY_OF_MONTH, cale.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = format.format(cale.getTime());
        return beginDate + "|" + endDate;

    }

    /**
     * 根据传入时间获取年份
     * @param dateStr	传入时间
     * @param dateFormat	日期输出格式
     * @param i		0为今年，-1为去年，1为明年
     * @return
     * @throws ParseException
     */
    public static String getLastyear(String dateStr, String dateFormat,int i) throws ParseException {

        // 日期格式转换
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(dateStr));
        calendar.add(Calendar.YEAR, i);
        return df.format(calendar.getTime()).substring(0, 4);
    }

    /**
     * 获取当前日期上月同天日期
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static String getLastMonthDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(BaseConstant.DATE_FORMAT);
        Date date = sdf.parse(dateStr);
        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(date); //设置时间为当前时间
        ca.add(Calendar.MONTH, -1);
        Date lastMonth = ca.getTime(); //结果
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(lastMonth);
    }
}
