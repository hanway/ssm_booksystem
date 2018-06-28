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
		<jsp:param value="book" name="menu"/>
	</jsp:include>

	<div class="container">
		<div class="well">
			<div class="row">
				<form action="index" class="form-search">
					&nbsp;&nbsp;
					书籍名称：<input type="text" class="input-medium search-query" name="bookname" placeholder="请输入书籍名称" value="${keyword}" />&nbsp;
					<%-- 作者：<input type="text" class="form-control" name="book.author" placeholder="请输入作者" value="${keyword}" />&nbsp;
					出版社：<input type="text" class="form-control" name="book.publisher" placeholder="请输入出版社" value="${keyword}" /> --%>
					<button class="btn btn-info">搜索</button>
				</form>
			</div>
		</div>
	
		<a href="newOrEditBook" class="btn btn-success">添加新书籍</a>&nbsp;
		<a href="export" class="btn btn-success">导出Excel</a>&nbsp;
		<a href="importExcel" class="btn btn-success">数据导入</a>
		<HR>
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
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty page.items }">
					<tr>
						<td colspan="7">没有任何数据</td>
					</tr>
				</c:if>
				<c:forEach items="${page.items}" var="book" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${book.bookname }</td>
						<td>${book.author }</td>
						<td>${book.publisher }</td>
						<td>${book.isbn }</td>
						<td>${book.total }</td>
						<td>${book.nownum }</td>
						<td>
							<a href="#" onclick="updateBook(${book.id})">修改</a>
							<a href="#" onclick="delBook(${book.id})">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="page">
			<ul class="pager">
				<c:choose>
					<c:when test="${page.pageNo == 1 }">
						<li class="disabled"><a href="javascript:;">上一页</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="index?p=${page.pageNo-1 }">上一页</a></li>
					</c:otherwise>
				</c:choose>
			  	<c:choose>
			  		<c:when test="${page.pageNo == page.totalPages }">
			  			<li class="disabled"><a href="javascript:;">下一页</a></li>
			  		</c:when>
			  		<c:otherwise>
			  			<li><a href="index?p=${page.pageNo+1 }">下一页</a></li>
			  		</c:otherwise>
			  	</c:choose>
			</ul>
		</div>
	</div>
	
	<script src="/js/jquery-1.9.1.min.js"></script>
	<script>
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
</body>
</html>