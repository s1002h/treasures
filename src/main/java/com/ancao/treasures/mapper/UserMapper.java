package com.ancao.treasures.mapper;

import java.util.List;
import java.util.Map;

import com.ancao.treasures.bean.UserBean;

public interface UserMapper {
	public UserBean getUserByLoginName(String loginId);
	public List<Map<String, Object>> getAuthList(String userId);
}
