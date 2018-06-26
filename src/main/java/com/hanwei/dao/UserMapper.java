package com.hanwei.dao;

import com.hanwei.entity.User;

public interface UserMapper {

	User findByUsername(String username);

	void updateUser(User user);
}
