package com.guodf.owner.common;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zhenhh@si-tech.com.cn
 */
@Configuration
@MapperScan(basePackages = "com.guodf.owner.*.mapper")
public class MyBatisConfig {


}
