<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/JSP/member/CSS/Navbar.css">
<script src="<%=request.getContextPath() %>/JSP/member/script/Navbar.js"></script>
<!-------------------------- 로그아웃 상태 --------------------------------------------------------------->
<c:if test="${AuthUser == null }">
<form method="POST" action="<%=request.getContextPath() %>/login.cl">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#" style="color:#ffcc00"><span class="glyphicon glyphicon-plus"></span>화양동성당</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="list ${main }"><a href="<%=request.getContextPath() %>/JSP/index.jsp">Main</a></li>
        <li class="list ${noti }"><a href="<%=request.getContextPath() %>/noticeList.cl">공지사항</a></li>
        <c:if test="${false }">
        <li class="list ${hi }"><a href="#">방명록</a></li>
        <li class="list ${tabl }"><a href="#">전례표</a></li>
        </c:if>
        <c:if test="${grade == 'G' }">
        <li class="list ${mone }"><a href="<%=request.getContextPath() %>/accountForm.cl">회계관리</a></li>
        <li class="list "><a href="<%=request.getContextPath() %>/memberList.cl">회원관리</a></li>
        </c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><div class="form-group" style="color:#ffffff">ID<input
				type='text' name='id' id='id' class="form-control"
				style="width: 100px"></div></li>
        <li><div class="form-group" style="color:#ffffff">Password<input
				type='password' name='passwd' id='passwd' class="form-control"
				style="width: 100px"></div></li>
			<li><div class="form-group">&nbsp;&nbsp;<input
				type="submit" value="로그인" name="submit" class="form-control"
				></div></li>
				<li><a href="<%=request.getContextPath() %>/JSP/member/JoinForm.jsp" style="color:#ffffff">&nbsp;&nbsp;<span class="glyphicon glyphicon-user"></span>Sign up</a></li>
      </ul>
    </div>
  </div>
</nav>
</form>
</c:if>

<!-------------------------- 로그인 상태 --------------------------------------------------------------->
<c:if test="${AuthUser != null }">
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#" style="color:#ffcc00"><span class="glyphicon glyphicon-plus"></span>화양동성당</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="list"><a href="<%=request.getContextPath() %>/JSP/index.jsp">Main</a></li>
        <li class="list"><a href="<%=request.getContextPath() %>/noticeList.cl">공지사항</a></li>
        <c:if test="${false }">
        <li class="list ${hi }"><a href="#">방명록</a></li>
        <li class="list ${tabl }"><a href="#">전례표</a></li>
        </c:if>
        <c:if test="${grade == 'G' }">
        <li class="list ${mone }"><a href="<%=request.getContextPath() %>/accountForm.cl">회계관리</a></li>
        <li class="list "><a href="<%=request.getContextPath() %>/memberList.cl">회원관리</a></li>
        </c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="" style="color: #ffffff">${AuthUser}님</a></li>
		<li><a href="<%=request.getContextPath() %>/myInfo.cl?AuthUser=${AuthUser }" style="color: #ffffff">내정보</a></li>
		<li><a href="#" style="padding:12px"><input style="display:inline" type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath() %>/logout.cl'"></a></li>
      </ul>
    </div>
  </div>
</nav>
</c:if>
