package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.BaseUtil.BasicBusinessUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【基础业务工具】
 * @author guodf
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/basicBusinessUtil")
@Api(tags = "基础业务工具")
public class BasicBusinessUtilController {
	/**
	 * 过滤标识串
	 * 位数不足时后面补0，位数见BaseConstant.COUNT_KEY_LENGTH
	 * @param param
	 * @return
	 */
    @PostMapping("/spellTermKey")
    @ResponseBody
    @ApiOperation(value = "过滤标识串", notes = "位数不足时后面补0，位数定义为5，不足补0  #工具方法->BasicBusinessUtil.spellTermKey()")
    public String spellTermKey(@ApiJsonObject(name="过滤标识串",value={
    		@ApiJsonProperty(key = "termKey",example = "wyt",description = "1000内随机数", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String termKey = param.get("termKey");
    	if(StringUtils.isBlank(termKey)) {
    		return "字符串不能为空";
    	}
    	return BasicBusinessUtil.spellTermKey(termKey);
    }
}
