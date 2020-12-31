package com.guodf.owner.controller;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.BaseUtil.TemplateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 【模板工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/templateUtil")
@Api(tags = "模板工具")
public class TemplateUtilController {
	/**
	 * 模板内容替换，参数按名称替换
	 * @return
	 */
    @PostMapping("/templateRepalce")
    @ResponseBody
    @ApiOperation(value = "模板内容替换，参数按名称替换", notes = "模板内容替换，参数按名称替换，template 模板内容{ABANM},Map<关键字, 替换内容>,Map<A, H>")
    public String templateRepalce(@ApiJsonObject(name="模板内容替换，参数按名称替换",value={
    		@ApiJsonProperty(key = "template",example = "ABANM",description = "模板内容", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String template = param.get("template");
    	if(StringUtils.isBlank(template)) {
    		return "模板内容不能为空";
    	}
    	Map<String,String> templateMap = new HashMap<String,String>();
    	templateMap.put("A", "H");
    	template = "{"+template+"}";
    	return TemplateUtil.templateRepalce(template,templateMap);
    }

	@PostMapping("/getUUID32")
	@ResponseBody
	@ApiOperation(value = "获取32位随机数", notes = "获取32位随机数 #工具方法->TemplateUtil.getUUID32()")
	public String getUUID32(){
		String res = TemplateUtil.getUUID32();
		return res;
	}
}


