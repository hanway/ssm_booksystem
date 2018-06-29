<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增/修改图书</title>
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
	<link rel="stylesheet" href="/css/bootstrap-2.3.1.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="card" name="menu"/>
	</jsp:include>

	<div class="container">
		<form action="saveOrUpdateCard" method="post">
			<legend>新增/修改借书证</legend>
			<label>借书证号</label>
			<input type="hidden" name="id" value="${card.id}">
			<input type="text" name="cardnum" value="${card.cardnum}">
			<label>姓名</label>
			<input type="text" name="name" value="${card.name}">
			<label>电话</label>
			<input type="text" name="tel" value="${card.tel}">
			
			<div class="form-actions">
				<button class="btn btn-primary">保存</button>
			</div>
		</form>
	</div>
</body>
</html>