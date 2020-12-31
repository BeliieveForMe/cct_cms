package com.guodf.owner.controller;

import java.util.Map;

import com.guodf.owner.BaseIn;
import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.ClassUtils.ClassUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【class类的反射相关工具】
 * @author guodf
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/classUtil")
@Api(tags = "class类的反射相关工具包")
public class ClassUtilController {

	
	/**
	 * 对 类 中的 String 属性进行 encode 或 decode 操作
	 * 可以解决存储在hbase中时汉字乱码问题
	 * @param param - E/Encode 编码；D/Decode 解码
	 * @throws Exception
	 */
    @PostMapping("/changeCode")
    @ResponseBody
    @ApiOperation(value = "根据传入的对象的，反射出对应的类s对其中的string属性进行编码和解码操作", 
    		notes = "根据传入的对象的，反射出对应的类进行编码和解码操作,optType:D(解码)|E(编码),o:baseIn,"
    				+ "前两个入参optType和o对象都是必传，这里不允许o对象传入，可传入optType:D(解码)|E(编码)和 charSet(sun.jnu.encoding)，o:baseIn中只有一个赋值deviceType=0，编码和解码结果可参考出参，#工具方法->ClassUtil.changeCode()")
    public Object changeCode(@ApiJsonObject(name="根据传入的对象的，反射出对应的类s对其中的string属性值进行编码和解码操作",value={
    		@ApiJsonProperty(key = "optType",example = "D",description = "解码", type = "string", required = true),
    		@ApiJsonProperty(key = "charSet",example = "55",description = "字符集", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	String optType = param.get("optType");
    	String charSet = param.get("charSet");
    	if(StringUtils.isBlank(optType)) {
    		return "optType不允许为空";
    	}
    	BaseIn o = new BaseIn();
    	Object res = null;
    	try {
    		res = ClassUtil.changeCode(optType, o, charSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return res;
    }
}
