<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./notice/script/Notice.js"></script>
<title>::화양동 성당::</title>
 <meta charset="utf-8">
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
	<center><h1>공지 수정</h1></center>
	<form method="post" action="<%=request.getContextPath() %>/noticeUpdate.cl"
		enctype="multipart/form-data">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="no" value="${bean.no }">
		<table class="table">
			<tr>
				<th>ID</th>
				<td><input type="hidden" name="id"
					value="${bean.id }"><input type="text" name="show"
					value="${bean.id }" disabled="disabled"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${bean.subject }"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<pre><textarea name="content" style="height: 300px; width: 400px;">${bean.content }</textarea></pre>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><div id="before">${bean.orifile }<span id="file">변경</span></div>
				<div id="after" style="display:none"><input type="file" name="uploadFile" id="uploadFile"></div></td>
			</tr>
		</table>

		<center>
			<input type="submit" value="수정" class="btn btn-default">&nbsp;&nbsp;<input
				type="button" value="취소" class="btn btn-default">
		</center>
	</form>
	</div>
	<div class="col-sm-3"></div>
	</div>
</body>
</html>