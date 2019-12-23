<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<center><h1>공지사항</h1></center>
	<table class="table">
		<c:if test="${grade == 'G'}">
			<tr>
				<td colspan='4' style="text-align: right"><a
					href='<%=request.getContextPath() %>/noticeForm.cl?AuthUser=${AuthUser }'>공지 작성</a></td>
			</tr>
		</c:if>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		<c:set var="num" value="${topnum+1 }" />
		<c:forEach var="bean" items="${noticelist }">
			<c:set var="num" value="${num-1 }" />
			<tr>
				<td><c:out value="${ num}" /></td>
				<td><a href="noticeContent.cl?page=${page }&no=${bean.no}">${bean.subject }</a></td>
				<td>${bean.reg_date }</td>
				<td>${bean.readcount}</td>
			</tr>
		</c:forEach>

	</table>
	<center>
	<ul class="pagination">
		<c:if test="${view >1 }">
			<li><a href=" ?page=${startPage -PPV }">&laquo;</a></li>
		</c:if>
		<c:forEach var="pag" begin="${startPage }" end="${endPage }">
			<li><a href=" ?page=${pag }">${pag }</a></li>
		</c:forEach>
		<c:if test="${view <VC }">
			<li><a href="<%=request.getContextPath() %>/noticeList.cl?page=${startPage +PPV }">&raquo;</a>
			</li>
		</c:if>
	</ul>
	</center>
	</div>
	<div class="col-sm-3"></div>
	</div>
</body>
</html>