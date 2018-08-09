package com.ancao.treasures.mapper;

import com.ancao.treasures.bean.UUser;
import com.ancao.treasures.common.BaseDao;

public interface UUserDao extends BaseDao<UUser, Long> {

	/**
	 * 增加对象
	 * 
	 * @param obj
	 */
	public UUser findByName(String name);
}
