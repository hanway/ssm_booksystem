<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>图书管理系统</title>
	<link rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/2.3.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="book" name="menu"/>
	</jsp:include>

	<div class="container">
	
		<a href="newOrEditBook" class="btn btn-success">添加新书籍</a>
	
		<table class="table">
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
				<c:forEach items="${bookList}" var="book" varStatus="vs">
					<tr>
						<td>${vs.count}</td>
						<td>${book.bookname}</td>
						<td>${book.author}</td>
						<td>${book.publisher}</td>
						<td>${book.isbn}</td>
						<td>${book.total}</td>
						<td>${book.nownum}</td>
						<td>
							<a href="#" onclick="updateBook(${book.id})">修改</a>
							<a href="#" onclick="delBook(${book.id})">删除</a>
						</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
<script type="text/javascript">
	function delBook(id) {
		var btn = window.confirm("确定要删除吗？");
        if(btn == true){
            window.location.href = "delBook?id=" + id;
        }else{
            return;
        }
	}
	function updateBook(id) {
		window.location.href = "newOrEditBook?id=" + id;
	}
</script>
</html>