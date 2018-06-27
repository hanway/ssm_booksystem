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
		<%-- <div class="well">
			<div class="row">
				<form action="index" class="form-search">
					&nbsp;&nbsp;
					书籍名称：<input type="text" class="input-medium search-query" name="bookname" placeholder="请输入书籍名称" value="${keyword}" />&nbsp;
					<button class="btn btn-info">搜索</button>
				</form>
			</div>
		</div> --%>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>书籍名称</th>
					<th>作者</th>
					<th>出版社</th>
					<th>ISBN码</th>
					<th>总数量</th>
					<th>剩余数量</th>
					<th>#</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty bookList}">
					<tr>
						<td colspan="7">没有任何数据</td>
					</tr>
				</c:if>
				<c:forEach items="${bookList}" var="book" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${book.bookname }</td>
						<td>${book.author }</td>
						<td>${book.publisher }</td>
						<td>${book.isbn }</td>
						<td>${book.total }</td>
						<td>
							<c:choose>
								<c:when test="${book.nownum == book.total}">
									${book.nownum}
								</c:when>
								<c:otherwise>
									<a href="/admin/borrow/users?id=${book.id}" title="查看借阅该书籍的用户">${book.nownum}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:if test="${book.nownum != 0}">
								<a href="borrowForm?id=${book.id}">借阅此书</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>