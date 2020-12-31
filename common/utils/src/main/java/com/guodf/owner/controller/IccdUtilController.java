package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.IccdUtils.IccdUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【身份证工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/iccdUtil")
@Api(tags = "身份证工具类")
public class IccdUtilController {
	
	 /** 
     * 根据身份证信息查询对应年龄，性别，出生年、月、日 小工具
     * 将15位身份证号转化为18位返回，非15位身份证号原值返回 
     * @param param
     * @return 
     */
    @PostMapping("/get18Ic")
    @ResponseBody
    @ApiOperation(value = "将15位身份证号转化为18位返回，非15位身份证号原值返回", notes = "将15位身份证号转化为18位返回，非15位身份证号原值返回" +
            " #工具方法->IccdUtil.get18Ic()")
    public String get18Ic(@ApiJsonObject(name="将15位身份证号转化为18位返回，非15位身份证号原值返回",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return IccdUtil.get18Ic(identityCard);
    }
    
    /**
     * 根据身份编号获取年龄
     * @param param
     * @return  iAge=-1 入参为空
     */
    @PostMapping("/getAgeByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取年龄", notes = "根据身份编号获取年龄  #工具方法->IccdUtil.getAgeByIdCard()")
    public String getAgeByIdCard(@ApiJsonObject(name="根据身份编号获取年龄",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return String.valueOf(IccdUtil.getAgeByIdCard(identityCard));
    }
    
    
    /**
     * 根据身份编号获取生日
     * @return 生日(yyyyMMdd)
     */
    @PostMapping("/getBirthByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取生日", notes = "根据身份编号获取生日 #工具方法->IccdUtil.getBirthByIdCard()")
    public String getBirthByIdCard(@ApiJsonObject(name="根据身份编号获取生日",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return IccdUtil.getBirthByIdCard(identityCard);
    }
    
    /**
     * 根据身份编号获取生日年
     * @return 生日(yyyy)
     */
    @PostMapping("/getYearByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取生日年", notes = "根据身份编号获取生日年  #工具方法->IccdUtil.getYearByIdCard()")
    public String getYearByIdCard(@ApiJsonObject(name="根据身份编号获取生日年",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return String.valueOf(IccdUtil.getYearByIdCard(identityCard));
    }
    
    /**
     * 根据身份编号获取生日月
     * @return 生日(MM)
     */
    @PostMapping("/getMonthByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取生日月", notes = "根据身份编号获取生日月  #工具方法->IccdUtil.getMonthByIdCard()")
    public String getMonthByIdCard(@ApiJsonObject(name="根据身份编号获取生日月",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return String.valueOf(IccdUtil.getMonthByIdCard(identityCard));
    }
    
    /**
     * 根据身份编号获取生日天
     * @return 生日(dd)
     */
    @PostMapping("/getDateByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取生日天", notes = "根据身份编号获取生日天  #工具方法->IccdUtil.getDateByIdCard()")
    public String getDateByIdCard(@ApiJsonObject(name="根据身份编号获取生日天",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return String.valueOf(IccdUtil.getDateByIdCard(identityCard));
    }
    
    
    /**
     * 根据身份编号获取性别
     * @return 性别(M-男，F-女，N-未知)
     */
    @PostMapping("/getGenderByIdCard")
    @ResponseBody
    @ApiOperation(value = "根据身份编号获取性别", notes = "根据身份编号获取性别,性别(M-男，F-女，N-未知)  #工具方法->IccdUtil.getGenderByIdCard()")
    public String getGenderByIdCard(@ApiJsonObject(name="根据身份编号获取性别,性别(M-男，F-女，N-未知)",value={
    		@ApiJsonProperty(key = "identityCard",example = "610122199509211424",description = "身份证号", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String identityCard = param.get("identityCard");
    	if(StringUtils.isBlank(identityCard)) {
    		return "身份证号必传";
    	}
    	return String.valueOf(IccdUtil.getGenderByIdCard(identityCard));
    }
}
