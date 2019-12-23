<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>::화양동 성당::</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
	<h1>회원목록</h1>
<table class='table'>
<tr>
	<td>번호</td>
	<td>아이디</td>
	<td>이름</td>
	<td>생년월일</td>
	<td>이메일</td>
	<td>가입일자</td>
	<td>등급</td>
</tr>

<c:set var="num" value="${topnum+1 }"/>
<c:forEach var="bean" items="${memberlist }">
<c:set var="num" value="${num-1 }"/>
<tr>
<td> <c:out value="${ num}"/> </td>
<td><a href="<%=request.getContextPath() %>/myInfo.cl?&AuthUser=${bean.id}">${bean.id }</a></td>
<td>${bean.name }</td>
<td> ${bean.birth} </td>
<td> ${bean.email} </td>
<td> ${bean.reg_date} </td>
<td> ${bean.grade} </td>
</tr>
</c:forEach>

</table>
<center>
<ul class="pagination">
<c:if test="${view >1 }">
<li>
<a href="<%=request.getContextPath() %>/memberList.cl?page=${startPage -PPV }">&laquo;</a>
</li>
</c:if>
<c:forEach var="pag" begin="${startPage }" end="${endPage }">

<li><a href="<%=request.getContextPath() %>/memberList.cl?page=${pag }">${pag }</a></li>

</c:forEach>
<c:if test="${view <VC }">
<li>
<a href="<%=request.getContextPath() %>/memberList.cl?page=${startPage +PPV }">&raquo;</a>
</li>
</c:if>
</ul>
</center>
</div>
 <div class="col-sm-3"></div>     
</div>
</body>
</html>