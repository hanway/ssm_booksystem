package com.hanwei.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	/**
	 * µÇÂ¼À¹½ØÌø×ªµÇÂ¼Ò³
	 */
	@RequestMapping(value="/index")
	public String loginForm() {
		return "admin/login";
	}
	
	/**
	 * Ð£ÑéÓÃ»§ÃûÃÜÂë
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Admin admin = adminService.findByUsername(username);
		if (admin != null && admin.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			
			admin.setLastaccesstime(sdf.format(new Date()));
			admin.setLastaccessip(request.getRemoteAddr());
			adminService.updateAdmin(admin);
			return "redirect:/booksystem/book/index";
		} else {
			model.addAttribute("code", "1001");
			return "redirect:/booksystem/admin/index";
		}
	}
	
	/**
	 * ÍË³öµÇÂ¼
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "admin/login";
	}
}