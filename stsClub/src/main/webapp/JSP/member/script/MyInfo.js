$(document).ready(function() {
//	유효성 검사
	$("#form1").submit(function() {
		if ($("#id").val() == "") {
			alert("id를 입력하세요.");
			$("#id").focus();
			return false;
		}
		
		if ($("#Jpass").val() == "") {
			alert("비밀번호를 입력하세요.");
			$("#Jpass").focus();
			return false;
		}
		if ($("#Jpass1").val() == "") {
			alert("비밀번호 확인을 입력하세요.");
			$("#Jpass1").focus();
			return false;
		}
		if ($("#Jpass").val() != $("#Jpass1").val()) {
			alert("비밀번호가 일치하지 않습니다.");
			$("#Jpass1").focus();
			return false;
		}
		if ($("#tel").val() == "") {
			alert("전화번호를 입력하세요.");
			$("#tel").focus();
			return false;
		}
		if ($("#email").val() == "") {
			alert("이메일을 입력하세요.");
			$("#email").focus();
			return false;
		}
		if ($("#postcode").val() == "") {
			alert("우편번호를 입력하세요.");
			$("#postcode").focus();
			return false;
		}
		if ($("#address1").val() == "") {
			alert("주소를 입력하세요.");
			$("#address1").focus();
			return false;
		}
		if ($("#address2").val() == "") {
			alert("나머지 주소를 입력하세요.");
			$("#address2").focus();
			return false;
		}
	});

	
});
