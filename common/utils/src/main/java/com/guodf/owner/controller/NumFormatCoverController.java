package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.BaseUtil.NumFormatCover;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【数据格式转化工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/numFormatCover")
@Api(tags = "数据格式转化")
public class NumFormatCoverController {
	
	/**
	 * 计算并格式化百分数
	 * @return x/y
	 */
    @PostMapping("/getPercent")
    @ResponseBody
    @ApiOperation(value = "计算并格式化百分数", notes = "计算并格式化百分数,x double,y double,return x/y" +
			"#工具方法->NumFormatCover.getPercent()")
    public String getPercent(@ApiJsonObject(name="计算并格式化百分数",value={
    		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
    		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	String x = param.get("x");
    	String y = param.get("y");
    	
    	return NumFormatCover.getPercent(Double.parseDouble(x),Double.parseDouble(y));
    }
    
   /* *//**
	 * 计算并格式化百分数
	 * @return (x-y)/y
	 */
    @PostMapping("/getVaryPercent")
    @ResponseBody
    @ApiOperation(value = "计算并格式化百分数", notes = "x 纯数字串或百分数,,y 纯数字串或百分数,return (x-y)/y" +
			" #工具方法->NumFormatCover.getVaryPercent()")
    public String getVaryPercent(@ApiJsonObject(name="计算并格式化百分数(纯数字串或百分数)",value={
    		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
    		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	String x = param.get("x");
    	String y = param.get("y");
    	
    	return NumFormatCover.getVaryPercent(x,y);
    }
    
    /**
	 * 计算并格式化百分数
	 * @return (x-y)/x
	 */
    @PostMapping("/getVaryPercent2")
    @ResponseBody
    @ApiOperation(value = "计算并格式化百分数", notes = "x纯数字串或百分数,y纯数字串或百分数,return (x-y)/x" +
			" #工具方法->NumFormatCover.getVaryPercent2()")
    public String getVaryPercent2(@ApiJsonObject(name="格式化百分数",value={
    		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
    		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	String x = param.get("x");
    	String y = param.get("y");
    	
    	return NumFormatCover.getVaryPercent2(x,y);
    }
    
    
    /**
	 * 计算并格式化百分数
	 * @return x/y
	 */
       @PostMapping("/getPercentByStr")
       @ResponseBody
       @ApiOperation(value = "计算并格式化百分数", notes = "x纯数字串或百分数,y纯数字串或百分数,return x/y" +
			   " #工具方法->NumFormatCover.getPercent()")
       public String getPercentByStr(@ApiJsonObject(name="格式化百分数(入参为字符串)",value={
       		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
       		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
       })@RequestBody Map<String, String> param){
       	String x = param.get("x");
       	String y = param.get("y");
       	
       	return NumFormatCover.getPercent(x,y);
       }
       
       /**
   	 * 格式化两位小数
   	 * @return x/y->xxx.xx
   	 */
      @PostMapping("/getDecimalForTwo")
      @ResponseBody
      @ApiOperation(value = "格式化两位小数", notes = "格式化两位小数,x - int，y - int,return x/y->xxx.xx" +
			  "#工具方法->NumFormatCover.getDecimalForTwo()")
      public String getDecimalForTwo(@ApiJsonObject(name="格式化两位小数",value={
      		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
      		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
      })@RequestBody Map<String, String> param){
      	String x = param.get("x");
      	String y = param.get("y");
      	
      	return NumFormatCover.getDecimalForTwo(Integer.valueOf(x),Integer.valueOf(y));
      }
      
      /**
  	 * 格式化两位小数
  	 * @return x/y->xxx.xx
  	 */
        @PostMapping("/getDecimalForTwos")
        @ResponseBody
        @ApiOperation(value = "格式化两位小数", notes = "格式化两位小数,x - double，y - double,return x/y->xxx.xx" +
				" #工具方法->NumFormatCover.getDecimalForTwo()")
        public String getDecimalForTwos(@ApiJsonObject(name="格式化两位小数(double)",value={
        		@ApiJsonProperty(key = "x",example = "23",description = "分子", type = "string", required = false),
        		@ApiJsonProperty(key = "y",example = "40",description = "分母", type = "string", required = false)
        })@RequestBody Map<String, String> param){
        	String x = param.get("x");
        	String y = param.get("y");
        	
        	return NumFormatCover.getDecimalForTwo(Double.valueOf(x),Double.valueOf(y));
        }
        
        /**
    	 * 如果double类型小数为0，则忽略小数点后
    	 * @return
    	 */
        @PostMapping("/doubleToInt")
        @ResponseBody
        @ApiOperation(value = "double类型转int", notes = "如果double类型小数为0，则忽略小数点后" +
				" #工具方法->NumFormatCover.doubleToInt()")
        public String doubleToInt(@ApiJsonObject(name="如果double类型小数为0，则忽略小数点后",value={
        		@ApiJsonProperty(key = "x",example = "0",description = "数值", type = "string", required = false)
        })@RequestBody Map<String, String> param){
        	String x = param.get("x");
        	return NumFormatCover.doubleToInt(Double.valueOf(x));
        }
        
        /**
    	 * 分转元
    	 */
        @PostMapping("/fenToYuan")
        @ResponseBody
        @ApiOperation(value = "分转元", notes = "分转元，x为string类型 " +
				" #工具方法->NumFormatCover.fenToYuan()")
        public String fenToYuan(@ApiJsonObject(name="分转元",value={
        		@ApiJsonProperty(key = "x",example = "100",description = "分值", type = "string", required = false)
        })@RequestBody Map<String, String> param){
        	String x = param.get("x");
        	return NumFormatCover.fenToYuan(x);
        }
}
