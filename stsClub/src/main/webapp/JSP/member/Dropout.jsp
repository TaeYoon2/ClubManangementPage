<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
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

</head>
<body>
	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
<h1>정말 탈퇴하실건가요? ㅠㅜ</h1>
<form method="POST" action="<%=request.getContextPath() %>/Dropout.cl">
<table>
	<tr>비밀번호  </tr>
	<tr><input type="password" name="passwd"></tr>
</table>
<input type="submit" value="탈퇴"><input type="button" value="취소">
</form>
</div>
<div class="col-sm-1"></div>
</div>
</body>
</html>