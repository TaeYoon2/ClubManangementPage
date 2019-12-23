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

</head>
<body>
<body>

	<c:import url="../member/Navbar.jsp"/>
	
<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
	<center><h1>저장된 보고서</h1></center>
	<div class="col-sm-6" style="overflow-y: scroll; height: 100px; border-radius:5px; box-shadow: 1px 1px 1px gray;">
	<i><b>예산신청서</b></i><br>
<c:forEach var='fname1' items="${fnames1 }">
<a href="<%=request.getContextPath() %>/reportDown.cl?where=1&file=${fname1 }"> ${fname1 }</a>
<br>
</c:forEach>
</div>
<div class="col-sm-6" style="overflow-y: scroll; height: 100px; border-radius:5px; box-shadow: 1px 1px 1px gray;">
<i><b>집행보고서</b></i><br>
<c:forEach var='fname2' items="${fnames2 }">
<a href="<%=request.getContextPath() %>/reportDown.cl?where=2&file=${fname2 }"> ${fname2 }</a>
<br>
</c:forEach>
</div>
</div>
<div class="col-sm-3"></div>
</div>
</body>
</html>