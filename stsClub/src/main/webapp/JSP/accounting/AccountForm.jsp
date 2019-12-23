<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>::화양동 성당::</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript"
	src="<%=request.getContextPath() %>/JSP/accounting/script/AccountForm.js"></script>
</head>
<body>

	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
	<h1>회계관리</h1>
	<form id="form0" method="post" action="<%=request.getContextPath() %>/addYear.cl">
		<input type="text" name="addyear" id="addyear"><input
			type="submit" value="연도추가">	<a style="float: right;" href="<%=request.getContextPath() %>/reportList.cl">보고서 출력</a>
	</form>
	<form id="form1" method="post" action="<%=request.getContextPath() %>/accountUpload.cl"
		enctype="multipart/form-data">
		<table class="table">
			<caption>
				<select name="year" id="year">
					<c:forEach var="years" items="${years }">
						<c:set var="year" value="${years.year }" />
						<option value="${year }">${year }</option>
					</c:forEach>
				</select>
			</caption>
			<tr>
				<td>구분</td>
				<td>월</td>
				<td>일</td>
				<td>금액</td>
				<td>내용</td>
				<td colspan="2">파일첨부</td>
			</tr>
			<tr>
				<td><select name="inout" id="inout"><option value="수입">수입</option>
						<option value="지출">지출</option></select></td>
				<td><select name="month" id="month"><c:forEach
							var="month" begin="1" end="12">
							<option value="${month }">${month }</option>
						</c:forEach></select></td>
				<td><select name="day" id="day"></select></td>
				<td><input type="text" name="carrot" id="carrot"></td>
				<td><input type="text" name="content" id="content"></td>
				<td colspan="2"><input type="file" name="paper" id="paper"></td>
				<td><input type="submit" value="등록"></td>
			</tr>

		</table>
	</form>
	<hr>
	
	<form id="form2" method="post">
	연도 :
	<select name="yearl" id="yearl">
		<c:forEach var="yearls" items="${years }">
			<c:set var="yearl" value="${yearls.year }" />
			<option value="${yearl }">${yearl }</option>
		</c:forEach>
	</select> 월 :
	<select name="monthl" id="monthl">
		<c:forEach var="monthl" begin="1" end="12">
			<option value="${monthl }">${monthl }</option>
		</c:forEach>
	</select>
		<div id="data"></div>
		<div id="con">
			<table id="filter" class="table">
				<tr>
					<td>구분</td>
					<td>월</td>
					<td>일</td>
					<td>금액</td>
					<td>내용</td>
					<td colspan="2">파일첨부</td>
				</tr>
			</table>
		</div>
		<div id="choice" style="float: right; "></div>
	</form>
	</div>
	<div class="col-sm-1"></div>
	</div>
</body>
</html>