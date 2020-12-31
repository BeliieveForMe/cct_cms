package com.guodf.owner.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.guodf.owner.BaseIn;
import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.DateUtil.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author liyx_pass
 * @description 【日期工具】
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/dateUtil")
@Api(tags = "日期工具包")
@Slf4j
public class DateUtilController {

    /**
     * 获取时间集合
     *
     * @param param
     * @return
     * @throws Throwable
     */
    @PostMapping("/getDateLst")
    @ResponseBody
    @ApiOperation(value = "根据时间类型和时间获取时间集合", notes = "根据时间类型和时间获取时间集合"
            + "时间类型 (dateType):Y(年)M(月)W(周)Q(季度)D(天)R(时间段标识)注  dateType=R时，时间(dateRange)需将开始时间和结束时间以,分割"
            + "时间(dateRange):Y-yyyy(2020) #工具方法->DateUtil.getDateLst()")
    public List<String> getDateLst(@ApiJsonObject(name = "根据时间类型和时间获取时间集合", value = {
            @ApiJsonProperty(key = "dateType", example = "M", description = "时间类型", type = "string", required = true),
            @ApiJsonProperty(key = "dateRange", example = "202001", description = "时间", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateType = param.get("dateType");
        String dateRange = param.get("dateRange");
        List<String> resList = new ArrayList<String>();
        if (StringUtils.isBlank(dateType)) {
            resList.add("时间类型不为空");
            return resList;
        }
        if (StringUtils.isBlank(dateRange)) {
            resList.add("时间不为空");
            return resList;
        }
        try {
            resList = DateUtil.getDateLst(dateType, dateRange);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 获取返回的时间主体
     *
     * @param param
     * @return
     * @throws Throwable
     */
    @PostMapping("/getDateItem")
    @ResponseBody
    @ApiOperation(value = "获取返回的时间主体", notes = "获取返回的时间主体,List<String> dateLst=[20200220],此处不需要用户传入  #工具方法->DateUtil.getDateItem()")
    public String getDateItem(@ApiJsonObject(name = "获取返回的时间主体", value = {
            @ApiJsonProperty(key = "dateLst", example = "", description = "时间list", type = "string", required = false)
    }) @RequestBody Map<String, String> param) {
        List<String> dateLst = new ArrayList<String>();
        dateLst.add("20200220");
        String res = "";
        try {
            res = DateUtil.getDateItem(dateLst);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取前几天的日期list
     * @return 日期格式
     */
    @PostMapping("/getDateList")
    @ResponseBody
    @ApiOperation(value = "获取指定日志前几天的日期list", notes = "获取指定日志前几天的日期listrange 时间段,BaseIn in,此处不需要用户传入  #工具方法->DateUtil.getDateList()")
    public List<String> getDateList(@ApiJsonObject(name = "获取指定日志前几天的日期list", value = {
            @ApiJsonProperty(key = "range", example = "-3", description = "时间段", type = "string", required = true),
            @ApiJsonProperty(key = "date", example = "20200220", description = "时间", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String range = param.get("range");
        String date = param.get("date");
        if (StringUtils.isBlank(range)) {
            log.info("传入时间段不能为空");
        }
        if (StringUtils.isBlank(date)) {
            log.info("传入时间不能能为空");
        }
        BaseIn in = new BaseIn();
        in.setDateRange(date);
        return DateUtil.getDateList(Integer.parseInt(range), in);
    }


    /**
     * 根据当前日期list获取上月对应日期的list
     * @return
     * @throws ParseException
     */
    @PostMapping("/getLastMonthLst")
    @ResponseBody
    @ApiOperation(value = "根据当前日期list获取上月对应日期的list", notes = "根据当前日期list获取上月对应日期的list,"
            + "List<String> dateLst=[20200219,20200220]此处不需要用户传入 #工具方法->DateUtil.getLastMonthLst()")
    public List<String> getLastMonthLst(@ApiJsonObject(name = "根据当前日期list获取上月对应日期的list", value = {
            @ApiJsonProperty(key = "dateLst", example = "", description = "时间list", type = "string", required = false)
    }) @RequestBody Map<String, String> param) {
        List<String> dateLst = new ArrayList<String>();
        dateLst.add("20200219");
        dateLst.add("20200220");
        List<String> resLst = null;
        try {
            resLst = DateUtil.getLastMonthLst(dateLst);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resLst;
    }

    /**
     * 根据本周日期list获取上周对应日期的list
     * @return
     * @throws Throwable
     */
    @PostMapping("/getLastWeekLst")
    @ResponseBody
    @ApiOperation(value = "根据本周日期list获取上周对应日期的list", notes = "根据本周日期list获取上周对应日期的list,"
            + "List<String> dateLst=[20200219,20200220]此处不需要用户传入 #工具方法->DateUtil.getLastWeekLst()")
    public String getLastWeekLst(@ApiJsonObject(name = "根据本周日期list获取上周对应日期的list", value = {
            @ApiJsonProperty(key = "dateLst", example = "", description = "时间list", type = "string", required = false)
    }) @RequestBody Map<String, String> param) {
        List<String> dateLst = new ArrayList<String>();
        dateLst.add("20200219");
        dateLst.add("20200220");
        String res = null;
        try {
            res = DateUtil.getLastWeekLst(dateLst);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据日期list<yyyymmdd>获取今年月份的list<yyyymm>
     * @return
     * @throws Throwable
     */
    @PostMapping("/getYMLst")
    @ResponseBody
    @ApiOperation(value = "根据日期获取今年月份的list", notes = "根据日期list<yyyymmdd>获取今年月份的list<yyyymm>,"
            + "List<String> dateLst=[20200219,20200220]此处不需要用户传入 #工具方法->DateUtil.getYMLst()")
    public List<String> getYMLst(@ApiJsonObject(name = "根据日期获取今年月份的list", value = {
            @ApiJsonProperty(key = "dateLst", example = "", description = "时间list", type = "string", required = false)
    }) @RequestBody Map<String, String> param) {
        List<String> dateLst = new ArrayList<String>();
        dateLst.add("20200219");
        dateLst.add("20200220");
        List<String> resLst = null;
        try {
            resLst = DateUtil.getYMLst(dateLst);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return resLst;
    }

    /**
     * 根据给定日期字符串（yyyyMMdd）获取当月字符串
     * @return
     * @throws ParseException
     */
    @PostMapping("/currentMonthByDate")
    @ResponseBody
    @ApiOperation(value = "根据给定日期字符串获取当月字符串", notes = "根据给定日期字符串（yyyyMMdd）获取当月字符串 #工具方法->DateUtil.currentMonthByDate()")
    public String currentMonthByDate(@ApiJsonObject(name = "根据给定日期字符串获取当月字符串", value = {
            @ApiJsonProperty(key = "dateStr", example = "20200220", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String res = "";
        try {
            res = DateUtil.currentMonthByDate(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 本月（格式：yyyy-MM）
     * @throws ParseException
     */
    @PostMapping("/currentMonth")
    @ResponseBody
    @ApiOperation(value = "获取指定日期的月份", notes = "获取指定日期的月份,dateStr201901,return 201901  #工具方法->DateUtil.currentMonth()")
    public String currentMonth(@ApiJsonObject(name = "获取指定日期的月份", value = {
            @ApiJsonProperty(key = "dateStr", example = "201901", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String res = "";
        try {
            res = DateUtil.currentMonth(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 上月（格式：yyyy-MM）
     * @throws ParseException
     */
    @PostMapping("/lastMonth")
    @ResponseBody
    @ApiOperation(value = "获取上月", notes = "获取上月,dateStr 201901  #工具方法->DateUtil.lastMonth()")
    public String lastMonth(@ApiJsonObject(name = "获取上月", value = {
            @ApiJsonProperty(key = "dateStr", example = "201901", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String res = "";
        try {
            res = DateUtil.lastMonth(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取给定日期的前一天
     * @return
     */
    @PostMapping("/getSpecifiedDayBefore")
    @ResponseBody
    @ApiOperation(value = "获取给定日期的前一天", notes = "获取给定日期的前一天,dateStr 20190304   #工具方法->DateUtil.getSpecifiedDayBefore()")
    public String getSpecifiedDayBefore(@ApiJsonObject(name = "获取给定日期的前一天", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        return DateUtil.getSpecifiedDayBefore(dateStr, 1);
    }

    /**
     * 获取给定日期的后一天
     * @return
     */
    @PostMapping("/getSpecifiedDayAfter")
    @ResponseBody
    @ApiOperation(value = "获取给定日期的后一天", notes = "获取给定日期的后一天,dateStr 20190304  #工具方法->DateUtil.getSpecifiedDayAfter()")
    public String getSpecifiedDayAfter(@ApiJsonObject(name = "获取给定日期的后一天", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        return DateUtil.getSpecifiedDayAfter(dateStr);
    }

    /**
     * 得到某个时间的前几天的日期数据
     * @return true-是错误数据，false-不是错误数据
     * @throws ParseException
     */
    @PostMapping("/isFalseDate")
    @ResponseBody
    @ApiOperation(value = "判断某个时间是否是前几天的日期数据", notes = "判断某个时间是否是前几天的日期数据,dateStr 20190304,"
            + "return:true-是错误数据，false-不是错误数据  #工具方法->DateUtil.isFalseDate()")
    public String isFalseDate(@ApiJsonObject(name = "判断某个时间是否是前几天的日期数据", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String res = "";
        try {
            res = String.valueOf(DateUtil.isFalseDate(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取给定日期上月最后一天
     * @return
     * @throws ParseException
     */
    @PostMapping("/getLastMonth")
    @ResponseBody
    @ApiOperation(value = "获取给定日期上月最后一天", notes = "获取给定日期上月最后一天,dateStr当前月日期,dateFormat 日期格式：yyyyMMdd，注：dateStr与dateFormat传入格式一致" +
            " #工具方法->DateUtil.getLastMonth()")
    public String getLastMonth(@ApiJsonObject(name = "获取给定日期上月最后一天", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true),
            @ApiJsonProperty(key = "dateFormat", example = "yyyymmdd", description = "日期格式", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        String dateFormat = param.get("dateFormat");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        if (StringUtils.isBlank(dateFormat)) {
            return "日期格式不能为空";
        }
        String res = "";
        try {
            res = DateUtil.getLastMonth(dateStr, dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 获取去年当前月开始结束时间
     * @return
     */
    @PostMapping("/getLastYearMonthDays")
    @ResponseBody
    @ApiOperation(value = "获取指定日期去年当前月开始结束时间", notes = "获取指定日期去年当前月开始结束时间,dateStr 当前月日期  #工具方法->DateUtil.getLastYearMonthDays()")
    public String getLastYearMonthDays(@ApiJsonObject(name = "获取指定日期去年当前月开始结束时间", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        return DateUtil.getLastYearMonthDays(dateStr);
    }

    /**
     * 获取上月第一天|最后一天
     */
    @PostMapping("/getLastMonthDays")
    @ResponseBody
    @ApiOperation(value = "获取上月第一天|最后一天", notes = "获取上月第一天|最后一天,dateStr 当前月日期  #工具方法->DateUtil.getLastMonthDays()")
    public String getLastMonthDays(@ApiJsonObject(name = "获取上月第一天|最后一天", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        return DateUtil.getLastMonthDays(dateStr);
    }

    /**
     * 获取指定月第一天
     */
    @PostMapping("/getFirstDayOfGivenMonth")
    @ResponseBody
    @ApiOperation(value = "获取指定月第一天", notes = "获取指定月第一天,dateStr 日期,String format日期格式,注：dateStr格式要与转入的日期格式format一致" +
            "  #工具方法->DateUtil.getFirstDayOfGivenMonth()")
    public String getFirstDayOfGivenMonth(@ApiJsonObject(name = "获取指定月第一天", value = {
            @ApiJsonProperty(key = "dateStr", example = "20190304", description = "日期字符串", type = "string", required = true),
            @ApiJsonProperty(key = "format", example = "yyyymmdd", description = "日期格式", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String format = param.get("format");
        if (StringUtils.isBlank(format)) {
            return "日期格式不能为空";
        }
        return DateUtil.getFirstDayOfGivenMonth(dateStr, format);
    }

    /**
     * 根据日期 找到对应日期的星期
     * @return
     */
    @PostMapping("/getDayOfWeekByDate")
    @ResponseBody
    @ApiOperation(value = "根据给定日期字符串获取对应日期的星期", notes = "根据给定日期字符串获取对应日期的星期" +
            "  #工具方法->DateUtil.getDayOfWeekByDate()")
    public String getDayOfWeekByDate(@ApiJsonObject(name = "根据给定日期字符串获取获取对应日期的星期", value = {
            @ApiJsonProperty(key = "date", example = "2020-07-01", description = "日期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("date");
        if (StringUtils.isBlank(dateStr)) {
            return "日期字符串不能为空";
        }
        String res = "";
        res = DateUtil.getDayOfWeekByDate(dateStr);
        return res;
    }


    /**
     * 根据星期获取数字标识
     */
    @PostMapping("/getWeekNum")
    @ResponseBody
    @ApiOperation(value = "根据星期获取数字标识", notes = "根据星期获取数字标识  #工具方法->DateUtil.getWeekNum()")
    public String getWeekNum(@ApiJsonObject(name = "根据星期获取数字标识", value = {
            @ApiJsonProperty(key = "week", example = "星期一", description = "星期字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("week");
        if (StringUtils.isBlank(dateStr)) {
            return "星期不能为空";
        }
        String res = "";
        res = DateUtil.getWeekNum(dateStr);
        return res;
    }

    /**
     * 获取某日期上一年同天日期
     * @return
     */
    @PostMapping("/getSpecifiedDayBeforeYear")
    @ResponseBody
    @ApiOperation(value = "获取某日期上一年同天日期", notes = "获取某日期上一年同天日期  #工具方法->DateUtil.getSpecifiedDayBeforeYear()")
    public String getSpecifiedDayBeforeYear(@ApiJsonObject(name = "获取某日期上一年同天日期", value = {
            @ApiJsonProperty(key = "specifiedDay", example = "20200701", description = "时间字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("specifiedDay");
        if (StringUtils.isBlank(dateStr)) {
            return "日期不能为空";
        }
        String res = "";
        res = DateUtil.getSpecifiedDayBeforeYear(dateStr);
        return res;
    }


    /**
     * 获取某日期上一年同天日期
     * @return
     */
    @PostMapping("/getWeekDays")
    @ResponseBody
    @ApiOperation(value = "获取当前日期所在周的日期范围", notes = "获取当前日期所在周的日期范围" +
            " #工具方法->DateUtil.getWeekDays()")
    public String getWeekDays(@ApiJsonObject(name = "获取当前日期所在周的日期范围", value = {
            @ApiJsonProperty(key = "specifiedDay", example = "20200701", description = "时间字符串", type = "string", required = true),
            @ApiJsonProperty(key = "i", example = "0", description = "-1上一周， 0本周， 1下一周", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("specifiedDay");
        int i = Integer.parseInt(param.get("i"));
        if (StringUtils.isBlank(dateStr)) {
            return "日期不能为空";
        }
        String res = "";
        res = DateUtil.getWeekDays(i, dateStr);
        return res;
    }


    /**
     * 获取本月第一天和最后一天
     * @return
     */
    @PostMapping("/getMonthDays")
    @ResponseBody
    @ApiOperation(value = "获取本月第一天和最后一天", notes = "获取本月第一天和最后一天  #工具方法->DateUtil.getWeekDays() #工具方法->DateUtil.getMonthDays()")
    public String getMonthDays(@ApiJsonObject(name = "获取本月第一天和最后一天", value = {
            @ApiJsonProperty(key = "specifiedDay", example = "20200701", description = "时间字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("specifiedDay");
        if (StringUtils.isBlank(dateStr)) {
            return "日期不能为空";
        }
        String res = "";
        res = DateUtil.getMonthDays(dateStr);
        return res;
    }


    /**
     * 根据传入时间获取年份
     * @return
     * @throws ParseException
     */
    @PostMapping("/getLastyear")
    @ResponseBody
    @ApiOperation(value = "根据传入时间获取年份", notes = "根据传入时间获取年份  #工具方法->DateUtil.getLastyear()")
    public String getLastyear(@ApiJsonObject(name = "根据传入时间获取年份", value = {
            @ApiJsonProperty(key = "specifiedDay", example = "20200701", description = "时间字符串", type = "string", required = true),
            @ApiJsonProperty(key = "i", example = "0", description = "0为今年，-1为去年，1为明年", type = "string", required = true),
            @ApiJsonProperty(key = "dateFormat", example = "yyyymmdd", description = "日期格式", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("specifiedDay");
        String dateFormat = param.get("dateFormat");
        int i = Integer.parseInt(param.get("i"));
        if (StringUtils.isBlank(dateStr)) {
            return "日期不能为空";
        }
        String res = "";
        try {
            res = DateUtil.getLastyear(dateStr, dateFormat, i);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }


    @PostMapping("/getLastMonthDate")
    @ResponseBody
    @ApiOperation(value = "获取当前日期上月同天日期", notes = "获取当前日期上月同天日期  #工具方法->DateUtil.getLastMonthDate()")
    public String getLastMonthDate(@ApiJsonObject(name = "获取当前日期上月同天日期", value = {
            @ApiJsonProperty(key = "dateStr", example = "20200701", description = "时间字符串", type = "string", required = true)
    }) @RequestBody Map<String, String> param) {
        String dateStr = param.get("dateStr");
        if (StringUtils.isBlank(dateStr)) {
            return "日期不能为空";
        }
        String res = "";

        try {
            res = DateUtil.getLastMonthDate(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return res;
    }

    @PostMapping("/beforeyesterdaySplit")
    @ResponseBody
    @ApiOperation(value = "前天（带 - 分隔符）", notes = "前天（带 - 分隔符）  #工具方法->DateUtil.beforeyesterdaySplit()")
    public String beforeyesterdaySplit() {
        String res = DateUtil.beforeyesterdaySplit();
        return res;
    }


	@PostMapping("/beforeyesterday")
	@ResponseBody
	@ApiOperation(value = "前天", notes = "前天   #工具方法->DateUtil.beforeyesterday()")
	public String beforeyesterday() {
		String res = DateUtil.beforeyesterday();
		return res;
	}


	@PostMapping("/yesterdaySplit")
	@ResponseBody
	@ApiOperation(value = "昨天（带分隔符）", notes = "昨天（带分隔符）    #工具方法->DateUtil.yesterdaySplit()")
	public String yesterdaySplit() {
		String res = DateUtil.yesterdaySplit();
		return res;
	}


	@PostMapping("/yesterday")
	@ResponseBody
	@ApiOperation(value = "昨天", notes = "昨天    #工具方法->DateUtil.yesterday()")
	public String yesterday() {
		String res = DateUtil.yesterday();
		return res;
	}


	@PostMapping("/today")
	@ResponseBody
	@ApiOperation(value = "今天", notes = "今天  #工具方法->DateUtil.today()")
	public String today() {
		String res = DateUtil.today();
		return res;
	}

	/**
	 * 当月第一天
	 *
	 * @return
	 */
	@PostMapping("/firstday")
	@ResponseBody
	@ApiOperation(value = "当月第一天", notes = "当月第一天 #工具方法->DateUtil.firstday()")
	public String firstday() {
		String res = DateUtil.firstday();
		return res;
	}


	@PostMapping("/currentMonthNoSplit")
	@ResponseBody
	@ApiOperation(value = "当前年月", notes = "当前年月 #工具方法->DateUtil.currentMonthNoSplit()")
	public String currentMonthNoSplit() {
		String res = DateUtil.currentMonthNoSplit();
		return res;
	}

	@PostMapping("/nowDay")
	@ResponseBody
	@ApiOperation(value = " 获取当前日期（格式：yyyy-MM）", notes = " 获取当前日期（格式：yyyy-MM） #工具方法->DateUtil.nowDay()")
	public String nowDay() {
		String res = DateUtil.nowDay();
		return res;
	}


    @PostMapping("/currentTime")
    @ResponseBody
    @ApiOperation(value = " 获取当前日期（格式：yyyyMMdd HH:mm:ss）", notes = " 获取当前日期（格式：yyyyMMdd HH:mm:ss） #工具方法->DateUtil.currentTime()")
    public String currentTime() {
        String res = DateUtil.currentTime();
        return res;
    }


    @PostMapping("/getCurrentDate")
    @ResponseBody
    @ApiOperation(value = " 获取当前日期（格式：获取当前时间 yyyy-MM-dd HH:mm:ss）", notes = " 获取当前日期（格式：获取当前时间 yyyy-MM-dd HH:mm:ss） #工具方法->DateUtil.currentTime()")
    public String getCurrentDate() {
        String res = DateUtil.currentTime();
        return res;
    }



//    @PostMapping("/getDate")
//    @ResponseBody
//    @ApiOperation(value = " 获取当前时间到yyyymmdd hh24:mi", notes = " 获取当前时间到yyyymmdd hh24:mi #工具方法->DateUtil.getDate()")
//    public String getDate() {
//        Date date = new Date();
//        Date res = DateUtil.getDate(date);
//        return String.valueOf(res);
//    }

}
