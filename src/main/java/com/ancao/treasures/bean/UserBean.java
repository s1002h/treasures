package com.ancao.treasures.bean;

import java.util.Date;
import java.util.List;

/**
 * 
 * 用户信息bean
 * 
 * @author Administrator
 * @version [v1.0, 2011-5-11]
 * @see
 * @since
 */
public class UserBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 登录ID
	 */
	private String loginId;

	/**
	 * 用户密码
	 */
	private String passWord;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 电话号码
	 */
	private String phone;

	/**
	 * 邮箱地址
	 */
	private String email;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 部门编号
	 */
	private String branchId;

	/**
	 * 部门名称
	 */
	private String branchName;

	/**
	 * 职务编号
	 */
	private String positionId;

	/**
	 * 职务名称
	 */
	private String positionName;

	/**
	 * 用户状态 0正常；1禁用
	 */
	private int state = 0;

	/**
	 * 创建时间
	 */
	private Date createDate = new Date();

	/**
	 * 生效日期
	 */
	private Date beginDate;

	/**
	 * 失效日期
	 */
	private Date endDate;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 登录时间
	 */
	private Date loginTime = new Date();

	/**
	 * 连续登陆错误次数
	 */
	private int loginErrCount;

	private List<String> roleStrlist;
	private List<String> perminsStrlist;

	public List<String> getRoleStrlist() {
		return roleStrlist;
	}

	public void setRoleStrlist(List<String> roleStrlist) {
		this.roleStrlist = roleStrlist;
	}

	public List<String> getPerminsStrlist() {
		return perminsStrlist;
	}

	public void setPerminsStrlist(List<String> perminsStrlist) {
		this.perminsStrlist = perminsStrlist;
	}

	public UserBean() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMemo() {
		return memo;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getLoginErrCount() {
		return loginErrCount;
	}

	public void setLoginErrCount(int loginErrCount) {
		this.loginErrCount = loginErrCount;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
}
