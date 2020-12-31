package com.guodf.owner.util.IpUtils;

import lombok.Data;

/**
 * 返回地市信息封装类
 * @author dell
 *
 */
@Data
public class Region {
    //地市id
    private String  region_id;
    //地市名称
    private String  region_name;
    //地市级别
    private String  region_level;
}
