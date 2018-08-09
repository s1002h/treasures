package com.ancao.treasures.mapper;


import java.util.List;

import com.ancao.treasures.bean.UPermission;
import com.ancao.treasures.common.BaseDao;

public interface UPermissionDao extends BaseDao<UPermission, Long> {

	/**
	 * 根据用户id获取用户权限
	 * 
	 * @param obj
	 */
//	List<UPermission> findPermissionByUid(Long id);
	List<UPermission> findPermissionByUid();
}
