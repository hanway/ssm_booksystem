package com.hanwei.service;

import com.hanwei.entity.Admin;

public interface AdminService {

	Admin findByUsername(String username);

	void updateAdmin(Admin admin);

}