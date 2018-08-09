package com.ancao.treasures.enums;


/**
 * @author hongxuewen
 * @version 1.0 Description:参数结果编码
 */

public enum ErrEnum {
	INFO("info"), MESSAGE("message");

	private String msg;

	private ErrEnum(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
