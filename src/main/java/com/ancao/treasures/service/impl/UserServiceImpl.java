package com.ancao.treasures.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ancao.treasures.bean.UserBean;
import com.ancao.treasures.mapper.UserMapper;
import com.ancao.treasures.service.UserService;
import com.ancao.treasures.util.MD5Util;
import com.ancao.treasures.util.MyException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserBean getUser(String loginName, String password) throws MyException {
		password = MD5Util.getEncryptStr(password);
		UserBean userBean = userMapper.getUserByLoginName(loginName);
		if (null == userBean) {
			throw new MyException("用户不存在！");
		} else if (!password.equals(userBean.getPassWord())) {
			throw new MyException("密码不正确！");
		} else if (userBean.getEndDate().getTime() < new Date().getTime()) {
			throw new MyException("用户名或密码不正确！");
		} else {
			List<Map<String, Object>> authList = userMapper.getAuthList(userBean.getUserId());
			for (Map<String, Object> map : authList) {
				if (("100".equals(map.get("AUTHID")))) {
					break;
				} else {
					throw new MyException("该账号未配置数据查询权限！");
				}

			}

		}
		return userBean;
	}

	@Override
	public List<Map<String, Object>> getAuthList(String loginName) {
		return userMapper.getAuthList(loginName);
	}

}
