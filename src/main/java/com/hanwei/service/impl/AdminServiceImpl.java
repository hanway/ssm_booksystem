package com.hanwei.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwei.dao.AdminMapper;
import com.hanwei.entity.Admin;
import com.hanwei.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Admin findByUsername(String username) {
		return adminMapper.findByUsername(username);
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminMapper.updateAdmin(admin);
	}
}
