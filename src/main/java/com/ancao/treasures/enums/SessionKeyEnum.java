package com.ancao.treasures.enums;


/**
 * @author hongxuewen
 * @version 1.0 Description:参数结果编码
 */

public enum SessionKeyEnum {
	/**
	 * 验证码
	 */
	VERIFY_CODE("verifyCode"),
	/**
	 * 登陆用户
	 */
	USER_SESSION("userSession");

	private String msg;

	private SessionKeyEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
