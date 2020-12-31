package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.DateUtil.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【时间工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/timeUtil")
@Api(tags = "时间工具包")
public class TimeUtilController {
	
	/**
     * 毫秒转时间
     * @return
     */
    @PostMapping("/formattime")
    @ResponseBody
    @ApiOperation(value = "毫秒转时间", notes = "毫秒转时间  #工具方法->TimeUtil.formattime()")
    public String formattime(@ApiJsonObject(name="毫秒转时间",value={
    		@ApiJsonProperty(key = "m",example = "1000",description = "毫秒", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String m = param.get("m");
    	if(StringUtils.isBlank(m)) {
    		return "传入毫秒数不为空";
    	}
    	return TimeUtil.formattime(Long.parseLong(m));
    }
    
    
    /**
     * 毫秒转分钟
     *
     * @return 两位小数
     */
    @PostMapping("/mill2Min")
    @ResponseBody
    @ApiOperation(value = "毫秒转分钟", notes = "毫秒转分钟  #工具方法->TimeUtil.mill2Min()")
    public String mill2Min(@ApiJsonObject(name="毫秒转分钟",value={
    		@ApiJsonProperty(key = "m",example = "1000",description = "毫秒", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String m = param.get("m");
    	if(StringUtils.isBlank(m)) {
    		return "传入毫秒数不为空";
    	}
    	return TimeUtil.mill2Min(m);
    }
    
    /**
     * 毫秒转秒
     *
     * @return 两位小数
     */
    @PostMapping("/mill2Sec")
    @ResponseBody
    @ApiOperation(value = "毫秒转秒", notes = "毫秒转秒   #工具方法->TimeUtil.mill2Sec()")
    public String mill2Sec(@ApiJsonObject(name="毫秒转秒",value={
    		@ApiJsonProperty(key = "sec",example = "1000",description = "毫秒", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String sec = param.get("sec");
    	if(StringUtils.isBlank(sec)) {
    		return "传入毫秒数不能为空";
    	}
    	return TimeUtil.mill2Sec(sec);
    }
    
    
    /**
     * 毫秒转小时
     * @return 两位小数
     */
    @PostMapping("/mill2Hour")
    @ResponseBody
    @ApiOperation(value = "毫秒转小时", notes = "毫秒转小时  #工具方法->TimeUtil.mill2Hour()")
    public String mill2Hour(@ApiJsonObject(name="毫秒转小时",value={
    		@ApiJsonProperty(key = "m",example = "1000",description = "毫秒", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String m = param.get("m");
    	if(StringUtils.isBlank(m)) {
    		return "传入毫秒数不为空";
    	}
    	return TimeUtil.mill2Hour(m);
    }
}
