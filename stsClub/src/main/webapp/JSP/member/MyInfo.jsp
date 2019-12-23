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
  <script src="http://code.jquery.com/jquery-latest.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<c:import url="../member/Navbar.jsp"/>
<div class="row">
	<div class="col-sm-3"></div>
	<div class="col-sm-6">
<h1>회원 정보</h1>
	<form method="POST" action="<%=request.getContextPath() %>/infoUpdate.cl">
		<input type="hidden" name="id" value="${bean.id }">
		
		<table class="table">
			<tr>
				<td>아이디</td>
				<td>${bean.id }</td>
			</tr>
			<tr>
				<td>현재 비밀번호</td>
				<c:if test="${grade!='G' }">
				<td><input type="password" name="passwdOld"></td>
				</c:if>
				<c:if test="${grade=='G' }">
				<td><input type="password" name="passwdOld" value="${bean.passwd }"></td>
				</c:if>
			</tr>
<!-- 			Ajax로 구현해보자. -->
			<tr>
				<td>새 비밀번호</td>
				<td><input type="password" name="Jpass"></td>
			</tr>
			<tr>
				<td>비밀번호 재확인</td>
				<td><input type="password" name="Jpass1"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${bean.name }</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${bean.birth }</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>${bean.sex }</td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="tel" value="${bean.tel }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${bean.email }"></td>
			</tr>
			<tr>
				<td rowspan="3">주소</td>
				<td><input type="text" name="postcode" id="postcode" value="${bean.postcode}" readonly="readonly"><input type=button value="찾기" onclick="execDaumPostcode()"></td>
			</tr>
			<tr>
				<td><input type="text" name="address1" id="address1" value="${bean.address1 }" readonly="readonly"></td>
			</tr>
			<tr>
				<td><input type="text" name="address2" id="address2" value="${bean.address2 }"></td>
			</tr>
		</table>
		<input type="submit" value="저장" class="btn btn-default"><input type="button" value="취소" onclick="history.go(-1);" class="btn btn-default">
	</form>
	<br><br>
	<a href="<%=request.getContextPath() %>/dropoutForm.cl?id=${bean.id}">회원탈퇴</a>
	</div>
	<div class="col-sm-3"></div>
	</div>
</body>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
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
                if(data.userSelectedType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
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