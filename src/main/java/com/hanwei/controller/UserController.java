package com.hanwei.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hanwei.entity.User;
import com.hanwei.service.UserService;
import com.hanwei.util.DateUtil;

@Controller
@RequestMapping(value="/booksystem/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * µÇÂ¼À¹½ØÌø×ªµÇÂ¼Ò³
	 */
	@RequestMapping(value="/index")
	public String loginForm() {
		return "user/login";
	}
	
	/**
	 * Ð£ÑéÓÃ»§ÃûÃÜÂë
	 */
	@RequestMapping(value="/login")
	public String login(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = userService.findByUsername(username);
		if (user != null && user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			user.setLastaccesstime(DateUtil.getNowDate());
			user.setLastaccessip(request.getRemoteAddr());
			userService.updateUser(user);
			return "redirect:/booksystem/book/index";
		} else {
			model.addAttribute("code", "1001");
			return "redirect:/booksystem/user/index";
		}
	}
	
	/**
	 * ÍË³öµÇÂ¼
	 */
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "user/login";
	}
}