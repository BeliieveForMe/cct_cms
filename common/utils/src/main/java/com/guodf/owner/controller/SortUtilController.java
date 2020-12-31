package com.guodf.owner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.SortUtils.SortUtil;
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
 * @description 【排序工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/sortUtil")
@Api(tags = "排序工具")
@Slf4j
public class SortUtilController {
	
	/**
	 * 对 Map<String, Integer> 结构的Map 按照 value 排序
	 * @throws Exception
	 */
    @PostMapping("/sortMap")
    @ResponseBody
    @ApiOperation(value = "Map 按照 value 排序", notes = "对 Map<String, Integer> 结构的Map按照 value排序,"
    		+ "入参：Map<String, Integer>此处不需要用户传入,只需要传入:isSeq - true：正序/false：逆序；示例入参：inMap<key1,3>,inMap<key1,1>,inMap<key1,4>" +
			" #工具方法->SortUtil.sortMap()")
    public List<Entry<String, Integer>> sortMap(@ApiJsonObject(name="对 Map<String, Integer> 结构的Map按照 value排序",value={
    		@ApiJsonProperty(key = "isSeq",example = "true",description = "正序", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String isSeq = param.get("isSeq");
    	if(StringUtils.isBlank(isSeq)) {
    		log.info("isSeq不为空");
    	}
    	Map<String, Integer> inMap = new HashMap<String, Integer>();
    	inMap.put("key1", 3);
    	inMap.put("key2", 1);
    	inMap.put("key3", 4);
    	return SortUtil.sortMap(inMap, Boolean.parseBoolean(isSeq));
    }
    
    /**
	 * 对 Map<String, Double> 结构的Map 按照 value 排序
	 * @throws Exception
	 */
    @PostMapping("/sortMap4Double")
    @ResponseBody
    @ApiOperation(value = "Map按照 value排序", notes = "Map<String, Double> 结构的Map 按照 value 排序,"
    		+ "入参：Map<String, Double>此处不需要用户传入,只需要传入:isSeq - true：正序/false：逆序，"
    		+ "示例入参：inMap.put(key1, 3.00),inMap.put(key1, 1.00),inMap.put(key1, 4.00)" +
			"#工具方法->SortUtil.sortMap4Double()")
    public List<Entry<String, Double>> sortMap4Double(@ApiJsonObject(name="Map<String, Double> 结构的Map 按照 value 排序",value={
    		@ApiJsonProperty(key = "isSeq",example = "true",description = "正序", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String isSeq = param.get("isSeq");
    	if(StringUtils.isBlank(isSeq)) {
    		log.info("isSeq不为空");
    	}
    	Map<String, Double> inMap = new HashMap<String, Double>();
    	inMap.put("key1", 3.00);
    	inMap.put("key2", 1.00);
    	inMap.put("key3", 4.00);
    	return SortUtil.sortMap4Double(inMap, Boolean.parseBoolean(isSeq));
    }
}
