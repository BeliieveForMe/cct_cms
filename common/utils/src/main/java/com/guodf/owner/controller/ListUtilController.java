package com.guodf.owner.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.ListUtils.ListUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @description 【List工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/listUtil")
@Api(tags = "List工具")
public class ListUtilController {
	
	/**
	 * 数组 转 list
	 * @param param
	 * @return list
	 */
    @PostMapping("/array2List")
    @ResponseBody
    @ApiOperation(value = "数组 转 list", notes = "数组 转 list,Object[] array,此处不需要传参，示例入参： {1,2,3} " +
			"#工具方法->ListUtil.array2List()")
    public List<String> array2List(@ApiJsonObject(name="数组 转 list",value={
    		@ApiJsonProperty(key = "array",example = "0",description = "数组", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	Object[] array = {"1","2","3"};
    	return ListUtil.array2List(array);
    }
    
    /**
	 * 数组 判断是否为空
	 * @return list
	 */
    @SuppressWarnings("rawtypes")
	@PostMapping("/isEmpty")
    @ResponseBody
    @ApiOperation(value = "数组 判断是否为空", notes = "数组 判断是否为空,Object[] array,示例入参：一个空List,此处不需要传参 " +
			"#工具方法->ListUtil.isEmpty()")
    public String isEmpty(@ApiJsonObject(name="数组 判断是否为空",value={
    		@ApiJsonProperty(key = "array",example = "0",description = "数组", type = "string", required = false)
    })@RequestBody Map<String, String> param){
    	List array = new ArrayList();
    	return String.valueOf(ListUtil.isEmpty(array));
    }
    
    /**
   	 * 数组空值取反
   	 * @return list
   	 */
       @SuppressWarnings("rawtypes")
   	@PostMapping("/isNotEmpty")
       @ResponseBody
       @ApiOperation(value = "数组空值取反", notes = "数组空值取反,Object[] array,此处不需要传参，示例入参：一个空List " +
			   "#工具方法->ListUtil.isNotEmpty()")
       public String isNotEmpty(@ApiJsonObject(name="数组空值取反",value={
       		@ApiJsonProperty(key = "array",example = "0",description = "数组", type = "string", required = false)
       })@RequestBody Map<String, String> param){
       	List array = new ArrayList();
       	return String.valueOf(ListUtil.isNotEmpty(array));
       }
       
       /**
  	  * 两个list对应项相加
  	  * @return
  	  */
  	 @PostMapping("/listAdd")
      @ResponseBody
      @ApiOperation(value = "两个list对应项相加", notes = "两个list对应项相加,此处不需要传参 ,示例入参lst1={1},lst1={2}" +
			  "#工具方法->ListUtil.listAdd()")
      public String listAdd(@ApiJsonObject(name="两个list对应项相加",value={
      		@ApiJsonProperty(key = "lst1",example = "0",description = "数组", type = "string", required = false),
      		@ApiJsonProperty(key = "lst2",example = "0",description = "数组", type = "string", required = false)
      })@RequestBody Map<String, String> param){
      	List<String> lst1 = new ArrayList<String>();
      	lst1.add("1");
      	List<String> lst2 = new ArrayList<String>();
      	lst2.add("2");
      	return String.valueOf(ListUtil.listAdd(lst1,lst2));
      }
      
      /**
 	  * list内部元素倒序
 	  * @return
 	  */
   	 @PostMapping("/reverse")
       @ResponseBody
       @ApiOperation(value = "list内部元素倒序", notes = "list内部元素倒序,此处不需要传参,示例入参：, lst1={1,2} " +
			   "#工具方法->ListUtil.reverse()")
       public String reverse(@ApiJsonObject(name="list内部元素倒序",value={
       		@ApiJsonProperty(key = "lst1",example = "0",description = "数组", type = "string", required = false)
       })@RequestBody Map<String, String> param){
       	List<String> lst1 = new ArrayList<String>();
       	lst1.add("1");
       	lst1.add("2");
       	return String.valueOf(ListUtil.reverse(lst1));
       }
   	 
   	/**
	  * list截取
	  * @return
	  */
   	   @SuppressWarnings("unchecked")
   	   @PostMapping("/subList")
       @ResponseBody
       @ApiOperation(value = "list截取", notes = "list截取,示例入参： lst1={1,2,3},size截取长度，此处不需要传lst1 " +
			   "#工具方法->ListUtil.subList()")
       public List<String> subList(@ApiJsonObject(name="list截取",value={
       		@ApiJsonProperty(key = "lst1",example = "0",description = "数组", type = "string", required = false),
       		@ApiJsonProperty(key = "size",example = "2",description = "截取长度", type = "string", required = true)
       })@RequestBody Map<String, String> param){
   		String size = param.get("size");
       	List<String> lst1 = new ArrayList<String>();
   		if(StringUtils.isBlank(size)) {
   			lst1.add("截取长度不为空");
   			return lst1;
   		}
       	lst1.add("1");
       	lst1.add("2");
       	lst1.add("3");
       	return (List<String>) ListUtil.subList(lst1,Integer.valueOf(size));
       }
   	   
   	 /**
  	  * 取出list内部时间后两位
  	  * @return
  	  */
 	 @PostMapping("/subList1")
     @ResponseBody
     @ApiOperation(value = "取出list内部时间后两位", notes = "取出list内部时间后两位,示例入参：lst1={1222222222,2333333333},此处不需要传lst1 " +
			 "#工具方法->ListUtil.subLst()")
     public List<String> subList1(@ApiJsonObject(name="取出list内部时间后两位",value={
     		@ApiJsonProperty(key = "lst1",example = "0",description = "数组", type = "string", required = false)
     })@RequestBody Map<String, String> param){
     	List<String> lst1 = new ArrayList<String>();
     	lst1.add("1222222222");
     	lst1.add("2333333333");
     	return ListUtil.subLst(lst1);
     }
}
