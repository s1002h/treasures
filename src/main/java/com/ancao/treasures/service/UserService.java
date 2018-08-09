package com.ancao.treasures.service;

import java.util.List;
import java.util.Map;

import com.ancao.treasures.bean.UserBean;
import com.ancao.treasures.util.MyException;

public interface UserService {
	public UserBean getUser(String loginName, String password) throws MyException;

	public List<Map<String, Object>> getAuthList(String loginName);
}
