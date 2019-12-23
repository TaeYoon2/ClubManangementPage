<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>::화양동 성당::</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>

<body>
<c:import url="../member/Navbar.jsp"/>
	<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
	<center><h1>공지 작성</h1></center>
	<form method="post" action="<%=request.getContextPath() %>/noticeWrite.cl"
		enctype="multipart/form-data">
		<table class="table">
			<tr>
				<th class="col-md-3">ID</th>
				<td class="col-md-9"><input type="hidden" name="id"
					value="${AuthUser }"><input type="text" name="show"
					value="${AuthUser }" disabled="disabled"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<pre><textarea name="content" style="height: 300px; width: 400px;"></textarea></pre>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="file" name="uploadFile" id="uploadFile"></td>
			</tr>
		</table>

		<input type="submit" class="btn btn-default" value="등록">&nbsp;&nbsp;<input
			type="reset" class="btn btn-default" value="취소">
	</form>
</div>
	<div class="col-sm-3"></div>
	</div>
</body>
</html>