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
		<div class="page-header">
			<h4> 《${book.bookname}》借阅用户列表： </h4>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>借书证号</th>
					<th>姓名</th>
					<th>电话</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty cardList}">
					<tr>
						<td colspan="3">没有任何数据</td>
					</tr>
				</c:if>
				<c:forEach items="${cardList}" var="card" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${card.cardnum }</td>
						<td>${card.name }</td>
						<td>${card.tel }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>