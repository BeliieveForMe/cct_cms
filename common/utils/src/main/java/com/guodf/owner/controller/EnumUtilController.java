package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.BaseUtil.EnumUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【字典工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/enumUtil")
@Api(tags = "字典工具包")
public class EnumUtilController {
	
	/**
	 * 根据渠道ID获取渠道名称
	 * #param sysId  渠道ID
	 * @return 渠道名称
	 */
    @PostMapping("/getChannelName")
    @ResponseBody
    @ApiOperation(value = "根据渠道ID-->渠道名称", notes = "根据工程接入的渠道进行查询：例如：0-网厅，1-掌厅  #工具方法->EnumUtil.getChannelName()")
    public String getChannelName(@ApiJsonObject(name="根据渠道ID-->渠道名称",value={
    		@ApiJsonProperty(key = "sysId",example = "0",description = "渠道对应的ID", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String sysId = param.get("sysId");
    	return EnumUtil.getChannelName(sysId);
    }





}
