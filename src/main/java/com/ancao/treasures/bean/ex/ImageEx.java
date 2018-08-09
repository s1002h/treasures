package com.ancao.treasures.bean.ex;

public class ImageEx {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String openUrl;// 跳转链接
	private String imgeUrl;// 图片路径
	private String description;// 描述
	private String code;// 序号

	public ImageEx() {

	}

	public ImageEx(Integer id, String openUrl, String imgeUrl, String description, String code) {
		super();
		this.id = id;
		this.openUrl = openUrl;
		this.imgeUrl = imgeUrl;
		this.description = description;
		this.code = code;
	}

	public String getOpenUrl() {
		return openUrl;
	}

	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}

	public String getImgeUrl() {
		return imgeUrl;
	}

	public void setImgeUrl(String imgeUrl) {
		this.imgeUrl = imgeUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
