<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理系统-登录</title>
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
	<link rel="stylesheet" href="/css/bootstrap-2.3.1.min.css">
	<style type="text/css">
		.login{
			width:300px;
			margin:120px auto 0px auto;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="login">
			<h3>图书管理系统</h3>
			<c:if test="${'1001' == param.code }">
				<div class="alert">
					用户名或密码错误
				</div>
			</c:if>
			<form id="loginForm" action="login" method="post">
				<label>账号 </label>
				<input type="text" name="username"/>
				<label>密码</label>
				<input type="password" name="password" id="password"/>
				<!-- <label class="checkbox">
					<input type="checkbox" name="remeberme" value="yes">记住用户名和密码
				</label> -->
				<div class="form-actions">
					<button id="btn" type="button" class="btn btn-primary">登录</button>
				</div>
			</form>
		</div>
	</div>
	
	<!-- <script src="/js/sha1.js"></script> -->
	<script src="/js/jquery-1.9.1.min.js"></script>
	<script>
		$(function(){
			$("#btn").click(function(){
				//$("#password").val(CryptoJS.SHA1($("#password").val()));
				$("#loginForm").submit();
			});
		});
	</script>
</body>
</html>