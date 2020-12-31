package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.BaseIn;
import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.BaseUtil.InvaidUtil;
import com.guodf.owner.util.LogUtils.LogUtil;
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
 * @description 【校验工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/invaidUtil")
@Api(tags = "校验工具")
@Slf4j
public class InvaidUtilController {
	
	/**
	 * 校验黑名单关键字
	 * blackKey格式：(key1),(key2)...
	 * @return true-校验通过，false-校验不通过
	 */
    @PostMapping("/validBlackKey")
    @ResponseBody
    @ApiOperation(value = "校验黑名单关键字", notes = "校验黑名单关键字,source为需要被检验的字符串，blackKey为黑名单关键字，多个按照,分割,true-校验通过，false-校验不通过" +
			" #工具方法->InvaidUtil.validBlackKey()")
    public String validBlackKey(@ApiJsonObject(name="校验黑名单关键字,source为需要被检验的字符串，blackKey为黑名单关键字，多个按照,分割,true-校验通过，false-校验不通过",value={
    		@ApiJsonProperty(key = "source",example = "getTaget",description = "被校验字符串", type = "string", required = true),
    		@ApiJsonProperty(key = "blackKey",example = "get,put,delete",description = "黑名单字符串", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String source = param.get("source");
    	String blackKey = param.get("blackKey");
    	if(StringUtils.isBlank(source)) {
    		return "被校验字符串不能为空";
    	}
    	if(StringUtils.isBlank(blackKey)) {
    		return "黑名单关键字不能为空";
    	}
    	return String.valueOf(InvaidUtil.validBlackKey(source, blackKey));
    }
    
    
    /**
	 * 校验 是否包含
	 * @param param	合集，如：1,2,3,4,5
	 * @param param	被校验值，如：3
	 * @param param 是否忽略大小写 true-忽略,false-不忽略
	 * @return boolean
	 */
    @PostMapping("/chkContains")
    @ResponseBody
    @ApiOperation(value = "校验传入参数reg是否包含被校验值v", notes = "校验传入参数reg是否包含被校验值v,isIgnore 是否忽略大小写 true-忽略,false-不忽略" +
			" #工具方法->InvaidUtil.chkContains()")
    public String chkContains(@ApiJsonObject(name="校验传入参数reg是否包含被校验值v,isIgnore 是否忽略大小写 true-忽略,false-不忽略",value={
    		@ApiJsonProperty(key = "reg",example = "A,B,G,N",description = "合集", type = "string", required = true),
    		@ApiJsonProperty(key = "v",example = "a",description = "被校验值", type = "string", required = true),
    		@ApiJsonProperty(key = "isIgnore",example = "true",description = "忽略", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String reg = param.get("reg");
    	String v = param.get("v");
    	String isIgnore = param.get("isIgnore");
    	if(StringUtils.isBlank(reg)) {
    		return "合集不能为空";
    	}
    	if(StringUtils.isBlank(v)) {
    		return "被校验值不能为空";
    	}
    	if(StringUtils.isBlank(isIgnore)) {
    		return "是否忽略大小写不能为空";
    	}
    	return String.valueOf(InvaidUtil.chkContains(reg, v,Boolean.parseBoolean(isIgnore)));
    }
    
    /**
	 * 校验时间字符串格式
	 * @param param
	 * @return
	 */
    @PostMapping("/chkDateFormat")
    @ResponseBody
    @ApiOperation(value = "校验时间字符串格式", notes = "校验时间字符串格式  #工具方法->InvaidUtil.chkDateFormat()")
    public String chkDateFormat(@ApiJsonObject(name="校验时间字符串格式",value={
    		@ApiJsonProperty(key = "dateStr",example = "2020-01-01",description = "时间字符串", type = "string", required = true),
    		@ApiJsonProperty(key = "format",example = "yyyy-mm-dd",description = "时间格式", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String dateStr = param.get("dateStr");
    	String format = param.get("format");
    	if(StringUtils.isBlank(dateStr)) {
    		return "时间字符串不能为空";
    	}
    	if(StringUtils.isBlank(format)) {
    		return "时间格式不能为空";
    	}
    	
    	return String.valueOf(InvaidUtil.chkDateFormat(dateStr,format));
    }
    
   /* *//**
	 * 数据接口入参校验
	 * 不校验指标集合，增加月份校验
	 * @return
	 */
    @PostMapping("/chkInParam")
    @ResponseBody
    @ApiOperation(value = "数据接口入参校验", notes = "数据接口入参校验,入参为：long stamp 为1000内随机数, BaseIn,此处不需要用户传入 BaseIn为公共入参实体,"
    		+ "返回null则证明验证通过，否则入参校验失败,返回校验失败原因" +
			" #工具方法->InvaidUtil.chkInParam() ")
    public String chkInParam(@ApiJsonObject(name="数据接口入参校验",value={
    		@ApiJsonProperty(key = "stamp",example = "344",description = "1000内随机数", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	BaseIn in = new BaseIn();
    	return InvaidUtil.chkInParam(LogUtil.getStamp(),in);
    }

	/**
	 * 日期入参校验
	 * @return
	 */
	@PostMapping("/chkDate")
	@ResponseBody
	@ApiOperation(value = "日期入参校验", notes = "日期入参校验 #工具方法->InvaidUtil.chkDate()")
	public String chkDate(@ApiJsonObject(name="日期入参校验",value={
			@ApiJsonProperty(key = "dateType",example = "R",description = "访问日期类型,0-今天，1-昨天，7-最近7天，30-最近30天，R-其他时间", type = "string", required = true),
			@ApiJsonProperty(key = "dateRange",example = "20160221,20160225",description = "20160222（dateType=R时启用），例：\"20160222\" 或 \"20160221,20160225\"(用逗号分隔)", type = "string", required = true),
			@ApiJsonProperty(key = "compareFlag",example = "1",description = "是否对比标识：0-不对比（默认），1-对比", type = "string", required = true),
			@ApiJsonProperty(key = "compareDateType",example = "R",description = "对比日期类型：0-今天，1-昨天，7-最近7天，30-最近30天，R-其他时间", type = "string", required = true),
			@ApiJsonProperty(key = "compareDateRange",example = "20160222",description = "对比其他时间（compareDateType=R时启用），例：\"20160222\" 或 \"20160221,20160225\"(用逗号分隔)", type = "string", required = true)
	})@RequestBody Map<String, String> param){
		String dateType = param.get("dateType");
		String dateRange = param.get("dateRange");
		String compareFlag = param.get("compareFlag");
		String compareDateType = param.get("compareDateType");
		String compareDateRange = param.get("compareDateRange");

		if(StringUtils.isBlank(dateType)) {
			return "时间字符串不能为空";
		}
		return String.valueOf(InvaidUtil.chkDate(dateType,dateRange,compareFlag,compareDateType,compareDateRange));
	}




    
}
