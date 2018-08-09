package com.ancao.treasures.enums;


/**
 * @author hongxuewen
 * @version 1.0 Description:参数结果编码
 */

public enum CodeEnum {
	/**
	 * 表示成功
	 */
	TD_00000("success"), //
	/**
	 * 非空值
	 */
	TD_11111("{} is blank !"), //
	/**
	 * 加密传不对
	 */
	TD_22222("{} is not authorization!"),

	/**
	 * 错误页面键
	 */
	ERROR_URL("common/error"),

	/**
	 * 成功页面键
	 */
	SUCCESS_URL("common/success"),

	/**
	 * 登陆页面键
	 */
	LOGIN_URL("loginPage"),

	/**
	 * 错误信息键
	 */
	ERROR_MESSAGE("ERROR_MESSAGE"),

	/**
	 * 成功信息键
	 */
	SUCCESS_MESSAGE("SUCCESS_MESSAGE"),

	/**
	 * 会话中客户信息键
	 */
	SESSION_CUST_INFO("CUSTINFO"),

	AGENT_INFO("agentinfo"),

	/**
	 * 重定向页面
	 */
	MESSAGE_DIRECT_PAGE("directPage"),

	/**
	 * 系统后台用户信息
	 */
	SESSION_USER_INFO("SYSTEM_USER_INFO"),
	/**
	 * 用户状态为：正常
	 */
	USER_STATE_NORMAL("0"),

	/**
	 * 用户状态为：失效
	 */
	USER_STATE_DISABLE("1"),
	/**
	 * 接口调用成功
	 */
	CODE_10000("10000"),
	/**
	 * 接口调用异常
	 */
	CODE_20000("20000"),
	/**
	 * 接口调用参数异常
	 */
	CODE_30000("30000");

	private String msg;

	private CodeEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
