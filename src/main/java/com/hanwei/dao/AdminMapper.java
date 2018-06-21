package com.hanwei.dao;

import com.hanwei.entity.Admin;

public interface AdminMapper {

	Admin findByUsername(String username);

	void updateAdmin(Admin admin);
}
