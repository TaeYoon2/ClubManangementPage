<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>::화양동 성당::</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/JSP/accounting/script/AccountingBasket.js"></script>
</head>
<body>
	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
<h1><span class="glyphicon glyphicon-bookmark"></span>장바구니</h1>
<form method="post" id="form1">
<table class="table">
<tr>
<td>일자</td>
<td>세부내역</td>
<td>금액</td>
<td>영수증</td>
</tr>
<c:forEach var="bean" items="${beans}">
<tr>
<td><input type="hidden" name='tran_date' value="${bean.tran_date }">${bean.tran_date}</td>
<td><input type="hidden" name='content' value="${bean.content }">${bean.content}</td>
<td><input type="hidden" name='carrot' value="${bean.carrot }">${bean.carrot}원</td>
<td><input type="hidden" name='savefile' value="${bean.savefile }">${bean.orifile}</td>
</tr>
</c:forEach>
<tr>
<td colspan="2"><span class="glyphicon glyphicon-tags"></span>&nbsp;합계</td>
<td>${sum }원</td>
<td></td>
</tr>
</table>
<hr>
활동 사항
<br>
<input type="text" id="newpo"><input type="button" id="savepo" value="저장">
<div id="passover">
</div>
<br>
<br>
<hr>
<div>
당월:&nbsp;<select name="month"><c:forEach var="m" begin="1" end="12"><option value="${m }">${m }월</option></c:forEach></select>
&nbsp;
<input type="button" onclick="mySubmit(1)" value="예산신청">
&nbsp;
<input type="button" onclick="mySubmit(2)" value="집행보고">
</div>
</div>
<div class="col-sm-1"></div>
</div>
</form>
</body>
</html>