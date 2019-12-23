<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=request.getContextPath() %>/JSP/member/script/JoinForm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<title>::화양동 성당::</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
</head>
<body>
	
	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
	
	<h1>회원가입</h1>
	<form method="POST" action="<%=request.getContextPath() %>/join.cl" id="form1">
		<table class="table">
			<tr>
				<td>아이디</td>
				<td><div><input type="text" name="id" id="jid"><input type="button" id="idchk" value="ID중복체크"></div><div id="idDouble" style="color: red"></div></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" id="Jpass"></td>
			</tr>
			<tr>
				<td>비밀번호 재확인</td>
				<td><input type="password" name="passwd1" id="Jpass1"><i><span id="check" style="color : red"></span></i></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><select name="birth1" id="birth1"><c:forEach begin="1910"
							end="2017" var="year">
							<c:set var="decyear" value="${2017-(year-1910) }" />
							<option value="${decyear }">${decyear }</option>
						</c:forEach></select> <select name="birth2" id="birth2"><c:forEach begin="01" end="12"
							var="month">
							<option value="${month }">${month }</option>
						</c:forEach>
				</select> <select name="birth3" id="birth3"><c:forEach begin="01" end="31"
							var="day">
							<option value="${day }">${day }</option>
						</c:forEach></select></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>남<input type="radio" name="sex" id="sex" value="M">여 <input type="radio" name="sex" value="F"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel" id="tel" ></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" id="email"></td>
			</tr>
			<tr>
				<td rowspan="3">주소</td>
				<td><input type="text" name="postcode" id="postcode" readonly="readonly"><input
					type=button value="찾기" onclick="execDaumPostcode()"></td>
			</tr>
			<tr>
				<td><input type="text" name="address1" id="address1" readonly="readonly"></td>
			</tr>
			<tr>
				<td><input type="text" name="address2" id="address2"></td>
			</tr>
		</table>
		<center><input type="submit" value="가입" class="btn btn-default"><input type="button" value="취소" class="btn btn-default" onclick=""></center>
	</form>
	</div>
	<div class="col-sm-3"></div>
	</div>
</body>
<script>
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('address1').value = fullAddr;

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('address2').focus();
					}
				}).open();
	}
</script>
</html>