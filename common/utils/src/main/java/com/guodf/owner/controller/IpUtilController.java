package com.guodf.owner.controller;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Map;

import com.guodf.owner.common.ApiJsonObject;
import com.guodf.owner.common.ApiJsonProperty;
import com.guodf.owner.util.IpUtils.CityUtil;
import com.guodf.owner.util.IpUtils.IPEntry;
import com.guodf.owner.util.IpUtils.IpUtil;
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
 * @description 【获取ip信息工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
@Controller
@RequestMapping("/util/ipUtil")
@Api(tags = "获取ip信息工具")
@Slf4j
public class IpUtilController {
	/**
	 * 给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录
	 *
	 * @param param 地点子串
	 * @return 包含IPEntry类型的List
	 */
    @PostMapping("/getIPEntriesDebug")
    @ResponseBody
    @ApiOperation(value = "定一个地点的不完全名字，得到一系列包含s子串的IP范围记录degug", notes = "定一个地点的不完全名字，得到一系列包含s子串的IP范围记录degug,"
    		+ "入参：s 地点子串,此处不需要传入RandomAccessFile,传入path是为了生成文件RandomAccessFile,return 包含IPEntry类型的List")
    public List<IPEntry> getIPEntriesDebug(@ApiJsonObject(name="定一个地点的不完全名字，得到一系列包含s子串的IP范围记录degug",value={
    		@ApiJsonProperty(key = "s",example = "西安市",description = "地点字符串", type = "string", required = true),
    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String s = param.get("s");
    	if(StringUtils.isBlank(s)) {
    		log.info("传入地点字符串不能为空");
    	}
    	String path = param.get("path");
    	if(StringUtils.isBlank(path)) {
    		log.info("传入文件路径不能为空");
    	}
    	RandomAccessFile accessFile = null;
    	try {
			 accessFile = CityUtil.getFile(path);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    	IpUtil ipUtil = new IpUtil(accessFile);
    	return ipUtil.getIPEntriesDebug(s);
    }


    /**
	 * 根据ip获取地点
	 *
	 */
    @PostMapping("/getIPLocation")
    @ResponseBody
    @ApiOperation(value = "根据ip获取地点", notes = "根据ip获取地点,传入path是为了生成文件RandomAccessFile,return 包含IPEntry类型的List")
    public String getIPLocation(@ApiJsonObject(name="根据ip获取地点",value={
    		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String ip = param.get("ip");
    	if(StringUtils.isBlank(ip)) {
    		log.info("传入ip不能为空");
    	}
    	String path = param.get("path");
    	if(StringUtils.isBlank(path)) {
    		log.info("传入文件路径不能为空");
    	}
    	RandomAccessFile accessFile = null;
    	try {
			 accessFile = CityUtil.getFile(path);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    	IpUtil ipUtil = new IpUtil(accessFile);
    	return ipUtil.getIPLocation(ip)+"";
    }

    /**
	 * 给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录
	 *
	 * @param param 地点子串
	 * @return 包含IPEntry类型的List
	 */
    @PostMapping("/getIPEntries")
    @ResponseBody
    @ApiOperation(value = "给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录", notes = "给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录")
    public List<IPEntry> getIPEntries(@ApiJsonObject(name="给定一个地点的不完全名字，得到一系列包含s子串的IP范围记录",value={
    		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String ip = param.get("ip");
    	if(StringUtils.isBlank(ip)) {
    		log.info("传入ip不能为空");
    	}
    	String path = param.get("path");
    	if(StringUtils.isBlank(path)) {
    		log.info("传入文件路径不能为空");
    	}
    	RandomAccessFile accessFile = null;
    	try {
			 accessFile = CityUtil.getFile(path);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    	IpUtil ipUtil = new IpUtil(accessFile);
    	return ipUtil.getIPEntries(ip);
    }

    /**
	 * 根据IP得到国家名
	 *
	 * @param param ip的字节数组形式
	 * @return 国家名字符串
	 */
    @PostMapping("/getCountry")
    @ResponseBody
    @ApiOperation(value = "根据IP得到国家名", notes = "根据IP得到国家名,入参为ip byte[]")
    public String getCountry(@ApiJsonObject(name="根据IP得到国家名",value={
    		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/临时文件/12412.log",description = "文件路径", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String ip = param.get("ip");
    	if(StringUtils.isBlank(ip)) {
    		log.info("传入ip不能为空");
    	}
    	String path = param.get("path");
    	if(StringUtils.isBlank(path)) {
    		log.info("传入文件路径不能为空");
    	}
    	RandomAccessFile accessFile = null;
    	try {
			 accessFile = CityUtil.getFile(path);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    	IpUtil ipUtil = new IpUtil(accessFile);
    	byte[] bytes = ip.getBytes();
    	return ipUtil.getCountry(bytes);
    }

    /**
   	 * 根据IP得到国家名字符串
   	 * @return 国家名字符串
   	 */
       @PostMapping("/getCountrys")
       @ResponseBody
       @ApiOperation(value = "根据IP得到国家名(入参为字符串)", notes = "根据IP得到国家名字符串，入参为ip字符串")
       public String getCountrys(@ApiJsonObject(name="根据IP得到国家名(入参为字符串)",value={
       		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
       		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/临时文件/12412.log",description = "文件路径", type = "string", required = true)
       })@RequestBody Map<String, String> param){
       	String ip = param.get("ip");
       	if(StringUtils.isBlank(ip)) {
       		log.info("传入ip不能为空");
       	}
       	String path = param.get("path");
       	if(StringUtils.isBlank(path)) {
       		log.info("传入文件路径不能为空");
       	}
       	RandomAccessFile accessFile = null;
       	try {
   			 accessFile = CityUtil.getFile(path);
   		} catch (IOException | InterruptedException e) {
   			e.printStackTrace();
   		}
       	IpUtil ipUtil = new IpUtil(accessFile);
       	return ipUtil.getCountry(ip);
       }

       /**
		 * 根据IP得到地区名
		 * @return 地区名字符串
		 */
          @PostMapping("/getArea")
          @ResponseBody
          @ApiOperation(value = "根据IP得到地区名", notes = "根据IP得到地区名,入参为byte[] ip")
          public String getArea(@ApiJsonObject(name="根据IP得到地区名",value={
          		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
          		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/临时文件/12412.log",description = "文件路径", type = "string", required = true)
          })@RequestBody Map<String, String> param){
          	String ip = param.get("ip");
          	if(StringUtils.isBlank(ip)) {
          		log.info("传入ip不能为空");
          	}
          	String path = param.get("path");
          	if(StringUtils.isBlank(path)) {
          		log.info("传入文件路径不能为空");
          	}
          	RandomAccessFile accessFile = null;
          	try {
      			 accessFile = CityUtil.getFile(path);
      		} catch (IOException | InterruptedException e) {
      			e.printStackTrace();
      		}
          	IpUtil ipUtil = new IpUtil(accessFile);
        	byte[] bytes = ip.getBytes();
          	return ipUtil.getArea(bytes);
          }
          /**
  		 * 根据IP得到地区名
  		 * @return 地区名字符串
  		 */
        @PostMapping("/getAreas")
        @ResponseBody
        @ApiOperation(value = "根据IP得到地区名(入参为字符串)", notes = "根据IP得到地区名(入参为字符串),入参为ip")
        public String getAreas(@ApiJsonObject(name="根据IP得到地区名(入参为字符串)",value={
        		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip", type = "string", required = true),
        		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/临时文件/12412.log",description = "文件路径", type = "string", required = true)
        })@RequestBody Map<String, String> param){
        	String ip = param.get("ip");
        	if(StringUtils.isBlank(ip)) {
        		log.info("传入ip不能为空");
        	}
        	String path = param.get("path");
        	if(StringUtils.isBlank(path)) {
        		log.info("传入文件路径不能为空");
        	}
        	RandomAccessFile accessFile = null;
        	try {
    			 accessFile = CityUtil.getFile(path);
    		} catch (IOException | InterruptedException e) {
    			e.printStackTrace();
    		}
        	IpUtil ipUtil = new IpUtil(accessFile);
        	return ipUtil.getArea(ip);
        }
}
