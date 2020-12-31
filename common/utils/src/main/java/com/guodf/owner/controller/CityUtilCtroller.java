package com.guodf.owner.controller;

/**
 * @description 【获取地市信息工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
/*	@Controller
	@RequestMapping("/util/cityUtil")
	@Api(tags = "获取地市信息工具")
	@Log4j
	public class CityUtilCtroller {
		
		*//**
		 * 根据传入的文件路径，
		 * @return  随机访问文件对象
		 *//*
	    @PostMapping("/getFile")
	    @SystemLog("根据文件路径获取随机访问文件对象")
	    @ResponseBody
	    @ApiOperation(value = "根据文件路径获取随机访问文件对象", notes = "根据文件路径获取随机访问文件对象")
	    public String getFile(@ApiJsonObject(name="根据文件路径获取随机访问文件对象",value={
	    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
	    })@RequestBody Map<String, String> param){
	    	String path = param.get("path");
	    	if(StringUtils.isBlank(path)) {
	    		return "传入文件路径不能为空";
	    	}
	    	RandomAccessFile accessFile = null;
	    	try {
	    		accessFile = CityUtil.getFile(path);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	    	return accessFile+"";
	    }
	    
	    
	    *//**
		 * 根据IP查询地市名称
		 * @param ip
		 * @return
		 *//*
	    @PostMapping("/getRegionName")
	    @SystemLog("根据IP查询地市名称")
	    @ResponseBody
	    @ApiOperation(value = "根据IP查询地市名称", notes = "根据IP查询地市名称,"
	    		+ "入参：String ip,RandomAccessFile,此处不需要传入RandomAccessFile,传入path是为了生成文件RandomAccessFile")
	    public String getRegionName(@ApiJsonObject(name="根据IP查询地市名称",value={
	    		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip地址", type = "string", required = true),
	    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
	    })@RequestBody Map<String, String> param){
	    	String ip = param.get("ip");
	    	if(StringUtils.isBlank(ip)) {
	    		return "传入ip不能为空";
	    	}
	    	String path = param.get("path");
	    	if(StringUtils.isBlank(path)) {
	    		return "传入文件路径不能为空";
	    	}
	    	RandomAccessFile accessFile = null;
	    	try {
	    		accessFile = CityUtil.getFile(path); 
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	    	return CityUtil.getRegionName(ip,accessFile);
	    }
	    
	    
	    *//**
		 * 根据IP获取地市编码
		 * @param ip
		 * @return
		 *//*
	    @PostMapping("/getRegionCode")
	    @SystemLog("根据IP获取地市编码")
	    @ResponseBody
	    @ApiOperation(value = "根据IP获取地市编码", notes = "根据IP获取地市编码,"
	    		+ "入参：String ip,RandomAccessFile,此处不需要传入RandomAccessFile,传入path是为了生成文件RandomAccessFile")
	    public String getRegionCode(@ApiJsonObject(name="根据IP获取地市编码",value={
	    		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip地址", type = "string", required = true),
	    		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
	    })@RequestBody Map<String, String> param){
	    	String ip = param.get("ip");
	    	if(StringUtils.isBlank(ip)) {
	    		return "传入ip不能为空";
	    	}
	    	String path = param.get("path");
	    	if(StringUtils.isBlank(path)) {
	    		return "传入文件路径不能为空";
	    	}
	    	RandomAccessFile accessFile = null;
	    	try {
	    		accessFile = CityUtil.getFile(path);
	    		IpUtil ipUtil = new IpUtil(accessFile);
	    		
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
	    	return CityUtil.getRegionCode(ip,accessFile);
	    }
	    
	    
	    *//**
	   	 * 根据IP获取省编码
	   	 * @param ip
	   	 * @return
	   	 *//*
	       @PostMapping("/getProvinceCode")
	       @SystemLog("根据IP获取省编码")
	       @ResponseBody
	       @ApiOperation(value = "根据IP获取省编码", notes = "根据IP获取省编码,"
	       		+ "入参：String ip,RandomAccessFile,此处不需要传入RandomAccessFile,传入path是为了生成文件RandomAccessFile")
	       public String getProvinceCode(@ApiJsonObject(name="根据IP获取省编码",value={
	       		@ApiJsonProperty(key = "ip",example = "123.139.106.174",description = "ip地址", type = "string", required = true),
	       		@ApiJsonProperty(key = "path",example = "C:/Users/南宫影露/Desktop/临时文件夹/运营视图.xlsx",description = "文件路径", type = "string", required = true)
	       })@RequestBody Map<String, String> param){
	       	String ip = param.get("ip");
	       	if(StringUtils.isBlank(ip)) {
	       		return "传入ip不能为空";
	       	}
	       	String path = param.get("path");
	       	if(StringUtils.isBlank(path)) {
	       		return "传入文件路径不能为空";
	       	}
	       	RandomAccessFile accessFile = null;
	       	try {
	       		accessFile = CityUtil.getFile(path);
	       		IpUtil ipUtil = new IpUtil(accessFile);
	       		
	   		} catch (IOException | InterruptedException e) {
	   			e.printStackTrace();
	   		}
	       	return CityUtil.getProvinceCode(ip,accessFile);
	       }
	}*/
