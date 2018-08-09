package com.ancao.treasures.util;

/**
 * 
 * 系统自定义异常
 * 
 * @author Administrator
 * @version [v1.0, 2012-3-2]
 * @see
 * @since
 */
public class MyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 错误信息ID
	 */
	private String errMsgId;

	/**
	 * 错误消息
	 */
	private String errMsg;

	/**
	 * 构造函数
	 */
	public MyException() {
	}

	/**
	 * 构造函数
	 */
	public MyException(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 构造函数
	 */
	public MyException(String errMsgId, String errMsg) {
		this.errMsgId = errMsgId;
		this.errMsg = errMsg;
	}

	/**
	 * 获取错误消息
	 * 
	 * @return
	 */
	public String getMessage() {
		return errMsg;
	}

	/**
	 * 
	 * 获取错误ID
	 * 
	 * @return
	 * @since <IVersion>
	 */
	public String getErrorId() {
		return errMsgId;
	}
}
