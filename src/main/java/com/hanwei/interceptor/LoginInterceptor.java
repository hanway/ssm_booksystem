package com.hanwei.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hanwei.entity.Admin;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin == null) {
			System.out.println("=============Î´µÇÂ¼£¬ÇëµÇÂ¼=============");
			response.sendRedirect("/booksystem/admin/index");
			return false;
		}
		return true;
	}
}
