<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理系统</title>
	<link rel="icon" type="image/x-icon" href="/favicon.ico">
	<link rel="stylesheet" href="/css/bootstrap-2.3.1.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="revert" name="menu"/>
	</jsp:include>

	<div class="container">
		<div class="well">
			<form action="index" class="form-search">
				<input type="text" name="cardnum" value="${card.cardnum}" placeholder="请输入借书证号">
				<button class="btn btn-info">查看应还书籍列表</button>
			</form>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>书籍名称</th>
					<th>作者</th>
					<th>出版社</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty bookList}">
					<tr>
						<td colspan="5">没有任何数据</td>
					</tr>
				</c:if>
				<c:forEach items="${bookList}" var="book" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${book.bookname }</td>
						<td>${book.author }</td>
						<td>${book.publisher }</td>
						<td>
							<a href="back?bid=${book.id}&cid=${card.id}">归还书籍</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>