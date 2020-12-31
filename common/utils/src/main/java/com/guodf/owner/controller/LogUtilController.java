package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.BaseOut;
import com.guodf.owner.SimulateLoginInfo;
import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
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
 * @description 【日志工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/logUtil")
@Api(tags = "日志工具包")
@Slf4j
public class LogUtilController {
	
	/**
	 * 输出 rest开始日志
	 */
    @PostMapping("/start")
    @ResponseBody
    @ApiOperation(value = "输出 rest开始日志", notes = "输出 rest开始日志,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
    		+ "此处只需要用户输入提示信息即可" +
			"#工具方法->LogUtil.start()")
    public void start(@ApiJsonObject(name="输出 rest开始日志",value={
    		@ApiJsonProperty(key = "m",example = "开始调用XX接口",description = "提示信息", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String m = param.get("m");
    	if(StringUtils.isBlank(m)) {
    		log.info("提示信息不能为空");
    	}
    	LogUtil.start(log, LogUtil.getStamp(), m);
    }
    
    /**
	 * 输出开始标识日志 和 入参字符串
	 */
    @PostMapping("/start1")
    @ResponseBody
    @ApiOperation(value = "输出开始标识日志 和 入参字符串", notes = "输出开始标识日志 和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
    		+ "o Request对象或Map对象,此处只需要用户输入提示信息即可" +
			"#工具方法->LogUtil.start()")
    public void start1(@ApiJsonObject(name="输出开始标识日志 和 入参字符串",value={
    		@ApiJsonProperty(key = "m",example = "开始调用XX接口",description = "提示信息", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String m = param.get("m");
    	if(StringUtils.isBlank(m)) {
    		log.info("提示信息不能为空");
    	}
    	 LogUtil.start(log, LogUtil.getStamp(), m,param);
    }
    
	    /**
		 * 输出 rest结束日志
		 */
       @PostMapping("/end")
       @ResponseBody
       @ApiOperation(value = "输出 rest结束日志", notes = "输出 rest结束日志 和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
       		+ ",此处只需要用户输入提示信息即可" +
			   "#工具方法->LogUtil.end()")
       public void end(@ApiJsonObject(name="输出 rest结束日志",value={
       		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
       })@RequestBody Map<String, String> param){
	       	String m = param.get("m");
	       	if(StringUtils.isBlank(m)) {
	    		log.info("提示信息不能为空");
	    	}
	       	LogUtil.end(log, LogUtil.getStamp(), m);
       }
       
       /**
   	 * 输出 rest结束日志
   	 */
      @PostMapping("/end1")
      @ResponseBody
      @ApiOperation(value = "输出 rest结束日志", notes = "输出 rest结束日志和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
      		+ "out - BaseOut输出实体,此处只需要用户输入提示信息即可" +
			  "#工具方法->LogUtil.end()")
      public void end1(@ApiJsonObject(name="输出 rest结束日志(增加输入实体)",value={
      		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
      })@RequestBody Map<String, String> param){
	       	String m = param.get("m");
	       	if(StringUtils.isBlank(m)) {
	    		log.info("提示信息不能为空");
	    	}
	       	BaseOut baseOut = new BaseOut();
	       	baseOut.setMsg("rest结束日志测试成功");
	       	LogUtil.end(log, LogUtil.getStamp(), m,baseOut);
      }
      
      /**
  	 * 输出出参对象和结束标识日志
  	 */
        @PostMapping("/end2")
        @ResponseBody
        @ApiOperation(value = "输出出参对象和结束标识日志", notes = "输出出参对象和结束标识日志,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
        		+ "out - Object输出对象,此处只需要用户输入提示信息即可 #工具方法->LogUtil.end()")
        public void end2(@ApiJsonObject(name="输出出参对象和结束标识日志",value={
        		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
        })@RequestBody Map<String, String> param){
  	       	String m = param.get("m");
  	       	if(StringUtils.isBlank(m)) {
  	    		log.info("提示信息不能为空");
  	    	}
  	       	SimulateLoginInfo info = new SimulateLoginInfo();
  	       	LogUtil.end(log, LogUtil.getStamp(), m,info);
        }
        
        /**
    	 * 输出 info 日志
    	 * @return [时间戳]提示信息
    	 */
        @PostMapping("/info")
        @ResponseBody
        @ApiOperation(value = "输出 info 日志", notes = "输出 info 日志 和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
        		+ "此处只需要用户输入提示信息即可" +
				"#工具方法->LogUtil.info()")
        public void info(@ApiJsonObject(name="输出 info 日志",value={
        		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
        })@RequestBody Map<String, String> param){
  	       	String m = param.get("m");
  	       	if(StringUtils.isBlank(m)) {
  	    		log.info("提示信息不能为空");
  	    	}
  	       	LogUtil.info(log, LogUtil.getStamp(), m);
        }
        
        /**
    	 * 输出 error 日志
    	 * @return [时间戳]提示信息
    	 */
        @PostMapping("/error")
        @ResponseBody
        @ApiOperation(value = "输出 error 日志", notes = "输出 error 日志 日志 和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
        		+ "t 异常实体,此处只需要用户输入提示信息即可" +
				"#工具方法->LogUtil.error() 使用try catch时，该方法放在catch以后")
        public void error(@ApiJsonObject(name="输出 error 日志",value={
        		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
        })@RequestBody Map<String, String> param){
  	       	String m = param.get("m");
  	       	if(StringUtils.isBlank(m)) {
  	    		log.info("提示信息不能为空");
  	    	}
//  	       	Throwable info = new Throwable();
//  	       	LogUtil.error(log, LogUtil.getStamp(), m,info);
        }
        
        /**
    	 * 输出 debug 日志
    	 */
        @PostMapping("/debug")
        @ResponseBody
        @ApiOperation(value = "输出 debug 日志", notes = "输出 debug日志 和 入参字符串,log	logger实体,stamp	时间戳,m 提示信息,打印结果见日志"
        		+ "t 异常实体,此处只需要用户输入提示信息即可" +
				"#工具方法->LogUtil.debug()")
        public void debug(@ApiJsonObject(name="输出 debug 日志",value={
        		@ApiJsonProperty(key = "m",example = "忠诚度数据获取",description = "提示信息", type = "string", required = true)
        })@RequestBody Map<String, String> param){
  	       	String m = param.get("m");
  	       	if(StringUtils.isBlank(m)) {
  	    		log.info("提示信息不能为空");
  	    	}
  	       	LogUtil.debug(log, LogUtil.getStamp(), m);
        }



	@PostMapping("/getStamp")
	@ResponseBody
	@ApiOperation(value = ""
			+ "获取当前毫秒值加1000以内数字 #工具方法->LogUtil.getStamp()")
	public Long getStamp(){
		return LogUtil.getStamp();
	}
}
