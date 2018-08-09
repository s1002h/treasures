package com.ancao.treasures.bean;

import java.util.HashMap;
import java.util.List;

public class DataBean {
	// 注册量
	private int registCount;
	// 个人注册量
	private int personalRegistCount;
	// 机构注册量
	private int orgRegistCount;
	// 检测量
	private int checkCount;
	// 个人检测量
	private int personalCheckCount;
	// 机构检测量
	private int orgCheckCount;
	// 充值金额
	private int money;
	// 平均订单充值金额
	private float avgMoney;
	// 人均充值金额
	private float perCapitaMoney;
	// 网络营销金额
	private float networkMarketingMoney;
	// 机构充值金额
	private float orgMoney;
	// 代理充值金额
	private float agentMoney;
	// 付费用户
	private int payUsers;
	// 免费用户
	private int freeUsers;
	// 页面展示日期段
	private String showDate;
	// 注册详情(个人+机构)
	private List<DetailsBean> registDetails;
	// 机构注册详情
	private List<DetailsBean> orgRegistDetails;
	// 个人注册详情
	private List<DetailsBean> personalRegistDetails;
	// 检测详情
	// private List<HashMap<String, Integer>> checkDetails;
	// 机构检测详情
	private List<DetailsBean> orgCheckDetails;
	// 个人检测详情
	private List<DetailsBean> personalCheckDetails;
	// 检测相似度详情
	private List<HashMap<String, Object>> checkGeneralRateDetails;
	// 充值详情
	// private List<HashMap<String, Integer>> rechargeDetails;
	// 个人充值详情
	private List<DetailsBean> personalRechargeDetails;
	// 机构充值详情
	private List<DetailsBean> orgRechargeDetails;

	public List<DetailsBean> getPersonalRechargeDetails() {
		return personalRechargeDetails;
	}

	public void setPersonalRechargeDetails(List<DetailsBean> personalRechargeDetails) {
		this.personalRechargeDetails = personalRechargeDetails;
	}

	public List<DetailsBean> getOrgRechargeDetails() {
		return orgRechargeDetails;
	}

	public void setOrgRechargeDetails(List<DetailsBean> orgRechargeDetails) {
		this.orgRechargeDetails = orgRechargeDetails;
	}

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}

	public List<DetailsBean> getOrgRegistDetails() {
		return orgRegistDetails;
	}

	public void setOrgRegistDetails(List<DetailsBean> orgRegistDetails) {
		this.orgRegistDetails = orgRegistDetails;
	}

	public List<DetailsBean> getPersonalRegistDetails() {
		return personalRegistDetails;
	}

	public void setPersonalRegistDetails(List<DetailsBean> personalRegistDetails) {
		this.personalRegistDetails = personalRegistDetails;
	}

	public float getNetworkMarketingMoney() {
		return networkMarketingMoney;
	}

	public void setNetworkMarketingMoney(float networkMarketingMoney) {
		this.networkMarketingMoney = networkMarketingMoney;
	}

	public float getOrgMoney() {
		return orgMoney;
	}

	public void setOrgMoney(float orgMoney) {
		this.orgMoney = orgMoney;
	}

	public float getAgentMoney() {
		return agentMoney;
	}

	public void setAgentMoney(float agentMoney) {
		this.agentMoney = agentMoney;
	}

	public float getAvgMoney() {
		return avgMoney;
	}

	public void setAvgMoney(float avgMoney) {
		this.avgMoney = avgMoney;
	}

	public float getPerCapitaMoney() {
		return perCapitaMoney;
	}

	public void setPerCapitaMoney(float perCapitaMoney) {
		this.perCapitaMoney = perCapitaMoney;
	}

	public List<HashMap<String, Object>> getCheckGeneralRateDetails() {
		return checkGeneralRateDetails;
	}

	public void setCheckGeneralRateDetails(List<HashMap<String, Object>> checkGeneralRateDetails) {
		this.checkGeneralRateDetails = checkGeneralRateDetails;
	}

	public int getPersonalCheckCount() {
		return personalCheckCount;
	}

	public void setPersonalCheckCount(int personalCheckCount) {
		this.personalCheckCount = personalCheckCount;
	}

	public int getOrgCheckCount() {
		return orgCheckCount;
	}

	public void setOrgCheckCount(int orgCheckCount) {
		this.orgCheckCount = orgCheckCount;
	}

	public List<DetailsBean> getOrgCheckDetails() {
		return orgCheckDetails;
	}

	public void setOrgCheckDetails(List<DetailsBean> orgCheckDetails) {
		this.orgCheckDetails = orgCheckDetails;
	}

	public List<DetailsBean> getPersonalCheckDetails() {
		return personalCheckDetails;
	}

	public void setPersonalCheckDetails(List<DetailsBean> personalCheckDetails) {
		this.personalCheckDetails = personalCheckDetails;
	}

	public List<DetailsBean> getRegistDetails() {
		return registDetails;
	}

	public void setRegistDetails(List<DetailsBean> registDetails) {
		this.registDetails = registDetails;
	}

	public int getPayUsers() {
		return payUsers;
	}

	public void setPayUsers(int payUsers) {
		this.payUsers = payUsers;
	}

	public int getFreeUsers() {
		return freeUsers;
	}

	public void setFreeUsers(int freeUsers) {
		this.freeUsers = freeUsers;
	}

	public int getPersonalRegistCount() {
		return personalRegistCount;
	}

	public void setPersonalRegistCount(int personalRegistCount) {
		this.personalRegistCount = personalRegistCount;
	}

	public int getOrgRegistCount() {
		return orgRegistCount;
	}

	public void setOrgRegistCount(int orgRegistCount) {
		this.orgRegistCount = orgRegistCount;
	}

	public int getRegistCount() {
		return registCount;
	}

	public void setRegistCount(int registCount) {
		this.registCount = registCount;
	}

	public int getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(int checkCount) {
		this.checkCount = checkCount;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
