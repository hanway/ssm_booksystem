<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增/修改图书</title>
	<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/2.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="book" name="menu"/>
	</jsp:include>

	<div class="container">
		<form id="newForm" action="saveOrUpdateBook" method="post">
			<legend>添加新书籍</legend>
			<label>书籍名称</label>
			<input type="hidden" name="id" value="${book.id}">
			<input type="text" name="bookname" value="${book.bookname}">
			<label>作者</label>
			<input type="text" name="author" value="${book.author}">
			<label>出版社</label>
			<input type="text" name="publisher" value="${book.publisher}">
			<label>ISBN</label>
			<input type="text" name="isbn" value="${book.isbn}">
			<label>总数量</label>
			<input type="text" name="total" value="${book.total}">
			
			<div class="form-actions">
				<button class="btn btn-primary">保存</button>
			</div>
		
		</form>
	</div>
</body>
</html>