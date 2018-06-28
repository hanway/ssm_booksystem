<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>数据导入</title>
<link rel="stylesheet" href="/css/bootstrap-2.3.1.min.css">
<link rel="stylesheet" href="/css/style.css">

</head>
<body>

	<jsp:include page="../include/navbar.jsp">
		<jsp:param value="book" name="menu"/>
	</jsp:include>
	
	<div class="container">
		<!-- <div class="page-header">
			<h3>1. 下载数据模板</h3>
		</div>
		<a href="/admin/book/template">点击下载数据模板</a> -->
		
		<div class="page-header">
			<h3><!-- 2.  -->上传填好的模板文件</h3>
		</div>
		<form action="importSave" method="post" enctype="multipart/form-data">
			<input type="file" name="dataFile">
			<div class="form-actions">
				<button class="btn btn-success">上传</button>
			</div>
		</form>
		
	</div>

</body>
</html>