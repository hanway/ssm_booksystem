package com.hanwei.service;

import com.hanwei.entity.User;

public interface UserService {

	User findByUsername(String username);

	void updateUser(User user);

}