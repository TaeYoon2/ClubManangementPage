function mySubmit(i){
				if(i==1){
					$("#form1").prop("action","/stsClub/accountApply.cl");
				}
				else if(i==2){
					$("#form1").prop("action","/stsClub/accountReport.cl");
				} else {
					
				}
				$("#form1").submit();
			};

		$(document).ready(function(){
			
			$("#newpo").keyup(function(e){
				if(e.keyCode==13){
					$("#savepo").click();
				}
			});
			$("#savepo").click(function(){
				var cont = $("#newpo").val();
				$("<input type='button' style='margin-right : 10px; margin-bottom : 10px;' class='fakepo btn btn-info' value='"+cont+"'><input type='hidden' name='po' value='"+cont+"'>").appendTo("#passover");
			
				$(".fakepo").click(function(){
					$(this).next().remove();
					$(this).remove();
				});
				
				$("#form1").submit(function(){
					if($("#po").val()==""){
						alert("활동 사항을 입력하세요.");
						$("#newpo").focus();
						return false;
						
					}
				});
				
			});
//			경과보고 추가
			
			
			
});