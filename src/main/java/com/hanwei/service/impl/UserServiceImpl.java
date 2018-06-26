package com.hanwei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwei.dao.UserMapper;
import com.hanwei.entity.User;
import com.hanwei.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}
}
