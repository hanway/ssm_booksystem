<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理系统</title>
	<link rel="stylesheet" href="/css/bootstrap-2.3.1.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="borrow" name="menu"/>
	</jsp:include>

	<div class="container">
		<legend>借阅书籍</legend>
		<c:choose>
			<c:when test="${code eq '1001'}">
				<div class="alert">未查询到借书证</div>
			</c:when>
			<c:when test="${code eq '1002'}">
				<div class="alert">该借书证已借阅过该书籍</div>
			</c:when>
			<c:when test="${code eq '1003'}">
				<div class="alert">请选择借书证</div>
			</c:when>
		</c:choose>
		<form action="saveBorrow" method="post">
			<input type="hidden" name="bookid" value="${book.id}"/>
			<label>书籍名称</label>
			<input type="text" name="bookname" value="${book.bookname}" readonly>
			<label>借书证号</label>
			<!-- <input type="text" name="card"> -->
			<select name="cardid">
				<option value="">请选择借书证</option>
				<c:forEach items="${cardList}" var="card">
					<option value="${card.id}">${card.cardnum}</option>
				</c:forEach>
			</select>
			<div class="form-actions">
				<button class="btn btn-primary">保存</button>
			</div>
		</form>
	</div>
</body>
</html>