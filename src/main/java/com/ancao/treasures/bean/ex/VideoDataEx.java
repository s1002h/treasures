package com.ancao.treasures.bean.ex;

import java.util.Date;

import com.ancao.treasures.bean.TreasuresVideo;

public class VideoDataEx extends TreasuresVideo {
	private String isPurchased;// 是否已购买
	private Date expirationTime;// 到期时间

	public String getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(String isPurchased) {
		this.isPurchased = isPurchased;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

}
