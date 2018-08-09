package com.ancao.treasures.mapper;


import java.util.List;

import com.ancao.treasures.bean.URole;
import com.ancao.treasures.common.BaseDao;

public interface URoleDao extends BaseDao<URole, Long> {

	/**
	 * 根据用户ID获取该用户所在组的角色权限
	 * 
	 * @param obj
	 */
//	public List<URole> findRoleByUid(Long obj);
	public List<URole> findRoleByUid();
}
