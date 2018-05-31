<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String menu = request.getParameter("menu");
%>
<div class="navbar navbar-fixed-top navbar-inverse">
	  <div class="navbar-inner">
	    <div class="container">
	    	<a class="brand" href="#">图书管理系统</a>
		    <ul class="nav">
		      <li class="<%=menu.equals("home") ? "active" : ""%>"><a href="/admin/home">首页</a></li>
		      <li class="<%=menu.equals("book") ? "active" : ""%>"><a href="/admin/book">书籍管理</a></li>
		      <li class="<%=menu.equals("card") ? "active" : ""%>"><a href="/admin/card">借书证管理</a></li>
		      <li class="<%=menu.equals("borrow") ? "active" : ""%>"><a href="/admin/borrow">借书</a></li>
		      <li class="<%=menu.equals("revert") ? "active" : ""%>"><a href="/admin/revert">还书</a></li>
		      <li><a href="/logout">安全退出</a></li>
		    </ul>
	    </div>
	  </div>
	</div>