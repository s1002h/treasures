package com.ancao.treasures.bean.ex;

public class TitleEx {
	private String type;// ":"类型","
	private String name;// ":"名称"

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TitleEx(String type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

}
