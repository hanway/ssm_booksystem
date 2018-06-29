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
		<jsp:param value="card" name="menu"/>
	</jsp:include>

	<div class="container">
		<a href="newOrEditCard" class="btn btn-success">添加借书证</a>&nbsp;
		<HR>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>借书证号</th>
					<th>姓名</th>
					<th>电话</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty cardList }">
					<tr>
						<td colspan="7">没有任何数据</td>
					</tr>
				</c:if>
				<c:forEach items="${cardList}" var="card" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${card.cardnum }</td>
						<td>${card.name }</td>
						<td>${card.tel }</td>
						<td>${card.createtime }</td>
						<td>
							<a href="#" onclick="updateCard(${card.id})">修改</a>
							<a href="#" onclick="delCard(${card.id})">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<script>
		function delCard(id) {
			var btn = window.confirm("确定要删除吗？");
	        if(btn == true){
	            window.location.href = "delCard?id=" + id;
	        }else{
	            return;
	        }
		}
		function updateCard(id) {
			window.location.href = "newOrEditCard?id=" + id;
		}
	</script>
</body>
</html>