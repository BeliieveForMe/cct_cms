package com.guodf.owner.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import com.guodf.owner.BaseIn;
import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.ExportFileUtils.ExportFileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【下载（导出）工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/exportFileUtil")
@Api(tags = "下载（导出）工具类")
public class ExportFileUtilCtroller {
	/**
	 * 通过Response流方式直接返回出参
	 * @param httpServletResponse
	 * @param param
	 */
    @PostMapping("/returnByResponse")
    @ResponseBody
    @ApiOperation(value = "将传入的对象(此处不需要用户传入)以流形式输出", notes = "将传入的对象(此处不需要用户传入)以httpServletResponse流形式输出，返回给前端，默认字符utf-8" +
			"  #工具方法->ExportFileUtil.returnByResponse()")
    public void returnByResponse(@ApiJsonObject(name="将传入的对象(此处不需要用户传入)以httpServletResponse流形式输出，返回给前端，默认字符utf-8",value={
    		@ApiJsonProperty(key = "charset",example = "UTF-8",description = "字符", type = "string", required = false)
    })@RequestBody Map<String, String> param,@Context HttpServletResponse httpServletResponse){
    	String charset = param.get("charset");
    	BaseIn o = new BaseIn();
    	ExportFileUtil.returnByResponse(httpServletResponse, o, charset);
    }
    
    
    /**
	 * 通过response方式将文件以流的形式导出，客户端会直接弹出下载框
	 * @param httpServletResponse 响应
	 * @param param 要导出的文件
	 * @param //isDelete 导出后是否需要删除原文件  true——需要   false——不需要
	 * @return 异常提示，正确执行返回""
	 */
    @PostMapping("/downloadFile")
    @ResponseBody
    @ApiOperation(value = "下载文件", notes = "传入文件filePath，response，和isDelete导出后是否需要删除原文件  true——需要   false——不需要,此处不需要用户传入file和response" +
			"  #工具方法->ExportFileUtil.downloadFile()")
    public String downloadFile(@ApiJsonObject(name="传入文件路径filePath，response，和isDelete导出后是否需要删除原文件  true——需要   false——不需要,此处不需要用户传入file和response",value={
    		@ApiJsonProperty(key = "filePath",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true),
    		@ApiJsonProperty(key = "isDelete",example = "false",description = "不需要", type = "string", required = true)
    })@RequestBody Map<String, String> param,@Context HttpServletResponse httpServletResponse){
    	String filePath = param.get("filePath");
    	if(StringUtils.isBlank(filePath)) {
    		return "filePath必传";
    	}
    	String isDelete = param.get("isDelete");
    	if(StringUtils.isBlank(isDelete)) {
    		return "isDelete必传";
    	}
    	File file = new File(filePath);
    	return ExportFileUtil.downloadFile(httpServletResponse, file,Boolean.parseBoolean(isDelete));
    }
}
