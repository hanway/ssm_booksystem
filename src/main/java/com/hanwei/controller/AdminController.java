package com.hanwei.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.Admin;
import com.hanwei.service.AdminService;

@Controller
@RequestMapping(value="/booksystem/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping(value="/index")
	public String loginForm(HttpServletRequest request, Model model) {
		return "admin/login";
	}
	
	
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Admin admin = adminService.findByUsername(username);
		if (admin != null && admin.getPassword().equals(password)) {
			admin.setLastaccesstime(sdf.format(new Date()));
			admin.setLastaccessip(request.getRemoteAddr());
			adminService.updateAdmin(admin);
			return "redirect:/booksystem/book/index";
		} else {
			return "redirect:/booksystem/admin/index?code=1001";
		}
		
	}
	
	/**
	 * ÍË³öµÇÂ¼
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, Model model) {
		return "admin/login";
	}

}
