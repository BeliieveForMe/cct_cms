package com.guodf.owner.controller;

/**
 * @description 【字典工具】
 * @author liyx_pass
 * @date 2020/2/19 14:50
 */
/*@Controller
@RequestMapping("/util/enumUtil")
@Api(tags = "字典工具包")*/
public class NetUtilController {
	/**
	 * 根据渠道ID获取渠道名称
	 * #param sysId  渠道ID
	 * @return 渠道名称
	 */
    /*@PostMapping("/getChannelName")
    @SystemLog("根据渠道ID-->渠道名称")
    @ResponseBody
    @ApiOperation(value = "根据渠道ID-->渠道名称", notes = "根据工程接入的渠道进行查询：例如：0-网厅，1-掌厅")
    public String getChannelName(@ApiJsonObject(name="根据渠道ID-->渠道名称",value={
    		@ApiJsonProperty(key = "sysId",example = "0",description = "渠道对应的ID", type = "string", required = true)
    })@RequestBody Map<String, String> param){
    	String sysId = param.get("sysId");
    	return EnumUtil.getChannelName(sysId);
    }*/
}
