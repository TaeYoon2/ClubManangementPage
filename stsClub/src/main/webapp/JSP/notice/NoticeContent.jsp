<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<h1>공지사항</h1>

	<table class="table">
		<tr>
			<th>작성자</th>
			<td>${bean.id }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${bean.subject }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre><textarea style="width:588px; height:230px; resize:none;" readonly="readonly">${bean.content }</textarea></pre></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><a
				href="<%=request.getContextPath() %>/noticeFileDown.cl?downFile=${bean.savefile }">${bean.orifile }</a></td>
		</tr>

	</table>
	<br>
	<center>
		<c:if test="${grade == 'G'}">
			<input type="button" class="btn btn-default" value="수정"
				onclick="location.href='<%=request.getContextPath() %>/noticeUpdateForm.cl?no=${bean.no }&page=${page}'">&nbsp;<input
				type="button" class="btn btn-default" value="삭제"
				onclick="location.href='<%=request.getContextPath() %>/noticeDelete.cl?no=${bean.no }&page=${page}'">&nbsp;</c:if>
		<input type="button" value="목록으로" class="btn btn-default"
			onclick="location.href='<%=request.getContextPath() %>/noticeList.cl?page=${page}'">
	</center>
</div>
	<div class="col-sm-3"></div>
	</div>
</body>
</html>