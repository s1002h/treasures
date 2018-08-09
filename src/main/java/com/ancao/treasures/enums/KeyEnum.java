package com.ancao.treasures.enums;


/**
 * @author hongxuewen
 * @version 1.0 Description:参数结果编码
 */

public enum KeyEnum {
	/**
	 * 验证码
	 */
	VERIFY_CODE("verifyCode"),
	/**
	 * 开始时间
	 */
	BEGIN_DATE("beginDate"),
	/**
	 * 结束时间
	 */
	END_DATE("endDate"),
	/**
	 * 用户类型
	 */
	CUST_TYPE("custType"),
	/**
	 * 机构编号
	 */
	ORG_CODE("orgCode");

	private String msg;

	private KeyEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
