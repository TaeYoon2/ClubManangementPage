$(document).ready(function() {
	
//	유효성 검사
	$("#form1").submit(function() {
		if ($("#jid").val() == "") {
			alert("id를 입력하세요.");
			$("#jid").focus();
			return false;
		}
		if ($("#jid").val() != "") {
		
		$.post("/stsClub/idCheck.cl?id="+$("#jid").val(),
				function(data,status){
			if(data != 0){
				alert("중복된 ID입니다.");
			}  
		} );
		
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
		if ($("#name").val() == "") {
			alert("이름을 입력하세요.");
			$("#name").focus();
			return false;
		}
		if ($("#sex").val() == "") {
			alert("입력하세요.");
			$("#sex").focus();
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
// id 중복 검사
	$("#idchk").click(function(){
		if( $("#jid").val()==""){
			$("#idDouble").text("ID를 입력하세요.");
		} else{
		$.post("/stsClub/idCheck.cl?id="+$("#jid").val(),
				function(data,status){
			if(data == 0){
			$("#idDouble").text("사용 가능한 ID입니다.");
			}  else {
				$("#idDouble").text("중복된 아이디입니다.");
			}
		} );
		}
	});
	
//	비밀번호 일치 검사
	$("#Jpass1").keyup(function() {
		if ($("#Jpass").val() != $("#Jpass1").val()) {
			// 비밀번호가 일치하지 않을 때
			$("#check").text("일치하지 않습니다.");
		} else {
			// 비밀번호가 일치할 때,
			$("#check").text("일치");
		}
	});
});
