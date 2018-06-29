<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String menu = request.getParameter("menu");
%>
<div class="navbar navbar-fixed-top navbar-inverse">
	<div class="navbar-inner">
		<div class="container">
	    	<a class="brand" href="#">图书管理系统</a>
		    <ul class="nav">
		    	<li class="<%=menu.equals("home") ? "active" : ""%>"><a href="/booksystem/book/index">首页</a></li>
		    	<li class="<%=menu.equals("book") ? "active" : ""%>"><a href="/booksystem/book/index">书籍管理</a></li>
		    	<li class="<%=menu.equals("card") ? "active" : ""%>"><a href="/booksystem/card/list">借书证管理</a></li>
		    	<li class="<%=menu.equals("borrow") ? "active" : ""%>"><a href="/booksystem/borrow/list">借书</a></li>
		    	<li class="<%=menu.equals("revert") ? "active" : ""%>"><a href="/booksystem/revert/index">还书</a></li>
		    </ul>
		    <ul class="nav pull-right">
		    	<li><a href="/booksystem/user/logout">安全退出</a></li>
		    </ul>
		</div>
	</div>
</div>