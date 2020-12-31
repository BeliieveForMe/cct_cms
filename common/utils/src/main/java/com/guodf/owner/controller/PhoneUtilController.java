package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.PhoneUtils.PhoneUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【手机号工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/enumUtil")
@Api(tags = "手机号工具包")
public class PhoneUtilController {
	/**
	 * 通过手机号判断所属运营商
	 * @return
	 */
    @PostMapping("/phoneOperators")
    @ResponseBody
    @ApiOperation(value = "通过手机号判断所属运营商", notes = "通过手机号判断所属运营商 #工具方法->PhoneUtil.phoneOperators()")
    public String phoneOperators(@ApiJsonObject(name="通过手机号判断所属运营商",value={
    		@ApiJsonProperty(key = "phone",example = "15829245251",description = "手机号码", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String phone = param.get("phone");
    	if(StringUtils.isBlank(phone)) {
    		return "手机号不能为空";
    	}
    	return PhoneUtil.phoneOperators(phone);
    }
}
