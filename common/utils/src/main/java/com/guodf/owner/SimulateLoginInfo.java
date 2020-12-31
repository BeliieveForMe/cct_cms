package com.guodf.owner;

import lombok.Data;

/**
 * 模拟登陆实体
 */
@Data
public class SimulateLoginInfo {

	/** 登陆会话 key */
	public static final String LOGIN_SESSION_KEY = "login_status";
	/** 模拟登陆状态标识 */
	public static final String LOGIN_STATUS_SIMULATE = "1";
	/** 终端类型标识 0-PC端 */
	public static final String DEVICE_TYPE_PC = "0";
	/** 终端类型标识 1-H5端 */
	public static final String DEVICE_TYPE_H5 = "1";
	
	/** 登陆状态 */
	private String login_status;
	/** 终端类型标识 0-PC端；1-H5 */
	private String device_type;
	/** 工号 */
	private String work_no;
	/** 手机号 */
	private String phone;
	/** 邮箱 */
	private String email;

	
}
