function mySubmit(i){
			if(i==1){
				$("#form2").prop("action","/stsClub/accountBasket.cl");
			}
			else if(i==2){
				$("#form2").prop("action","/stsClub/accountDelete.cl");
			} else {
				
			}
			$("#form2").submit();
		};

		$(document).ready(function(){
			$("#form1").submit(function(){
			if($("#carrot").val()==""){
				alert("금액을 입력하세요.");
				$("#carrot").focus();
				return false;
			}
			if(isNaN($("#carrot").val())){
				alert("숫자를 입력하세요.");
				$("#carrot").focus();
				return false;
			}
			if($("#content").val()==""){
				alert("내용을 입력하세요.");
				$("#content").focus();
				return false;
			}
			});
			

	$("#yearl").change(function(){
		by = "y";
		lineUp(by);
		
		
	});
	$("#monthl").change(function(){
		by = "m";
		lineUp(by);
	
		
		
		});
	
	
	function lineUp(by){
		
		$.ajax({
			url : "/stsClub/filter.cl",
			data:{"byWhat":by,"monthl":$("#monthl").val(),"yearl":$("#yearl").val()},
			dataType : "json", // 데이터 타입을 제이슨 꼭해야함, 다른방법도 2가지있음
			cache : false, // 이걸 안쓰거나 true하면 수정해도 값반영이 잘안댐
			
			success : function(data) {
				
				$("#con").html("");
				$("#data").html("");
				$("#choice").html("");
				$("<table id='filter' class='table'/>").appendTo("#con");
				$("<tr>",{
					html:	
						"<td>"+"구분"+"<select id='io'><option value=''>전체</option><option value='수입'>수입</option><option value='지출'>지출</option></select> "+"</td>"+
						"<td>"+"월"+"</td>"+
						"<td>"+"일"+"</td>"+
						"<td>"+"금액"+"</td>"+
						"<td>"+"내용"+"</td>"+
						"<td>"+"파일첨부"+"</td>"+
						"<td>"+"<input type='checkbox' name='chA' id='chA'>" +"</td>"
				}).appendTo("#filter");
				$.each(data, function(index, d) { // 이치를 써서 모든 데이터들을 배열에 넣음
					var items = [];
					
					items.push("<td class='"+d.inout+"'>" + d.inout + "</td>");
					items.push("<td>" + d.tran_date.substring(5,7) + "</td>");
					items.push("<td>" + d.tran_date.substring(8) + "</td>");
					items.push("<td>" + d.carrot + "</td>");
					items.push("<td>" + d.content + "</td>");
					items.push("<td>" + d.orifile + "</td>");
					items.push("<td>" + "<input type='checkbox' class='chs' name='ch' value='"+d.no+"'>" + "</td>");
					$("<tr/>", {
						html : items // 티알에 붙임,
					}).appendTo("#filter"); // 그리고 그 tr을 테이블에 붙임
					
					var items1 = [];
					items1.push("<input type='hidden' name='"+"no"+index+"' value='"+d.no+"'>");
					items1.push("<input type='hidden' name='"+"inout"+index+"' value='"+d.inout+"'>");
					items1.push("<input type='hidden' name='"+"month"+index+"' value='"+d.tran_date.substring(0, d.tran_date.indexOf("월"))+"'>");
					items1.push("<input type='hidden' name='"+"day"+index+"' value='"+d.tran_date.substring(d.tran_date.indexOf("월")+1,d.tran_date.indexOf(","))+"'>");
					items1.push("<input type='hidden' name='"+"carrot"+index+"' value='"+d.carrot+"'>");
					items1.push("<input type='hidden' name='"+"content"+index+"' value='"+d.content+"'>");
					items1.push("<input type='hidden' name='"+"orifile"+index+"' value='"+d.orifile+"'>");
					items1.push("<input type='hidden' name='"+"savefile"+index+"' value='"+d.savefile+"'>");
					
					$("<div/>", {
						html : items1 // div에 붙임,
					}).appendTo("#data"); // 그리고 그 div를 앞에 붙임
				});//each끝
				
				
				var items2 = [];
				items2.push("총수입<input type='text' id='plus' name='plus' readonly='readonly'>");
				items2.push("총지출<input type='text' id='minus' name='minus' readonly='readonly'>");
				items2.push("합계<input type='text' id='sum' name='sum' readonly='readonly'>");
				items2.push("<input type='button' onclick='mySubmit(1)' value='장바구니'>");
				items2.push("<input type='button' onclick='mySubmit(2)' value='선택삭제'>");
				$("<div/>", {
					html : items2 // div에 붙임,
				}).appendTo("#choice"); // 그리고 그 div를 앞에 붙임
				
				function mySubmit(i){
					if(i==1){
						$("#form2").prop("action","/accountBasket.cl");
					}
					else if(i==2){
						$("#form2").prop("action","/accountDelete.cl");
					} else {
						
					}
					$("#form2").submit();
				};
				
				$("#chA").click(function(){
					 if($("#chA").prop("checked")){
				            //클래스가 chs인 태그들을 찾아서 checked옵션을 true로 정의
				            $(".chs").prop("checked",true);
				            //클릭이 안되있으면
				        }else{
				            //클래스가 chs인 태그들을 찾아서 checked옵션을 false로 정의
				            $(".chs").prop("checked",false);
				        }
				});
				
			
					$("#io").on("mouseenter", function() {
					    var value = $(this).val().toLowerCase();
					    $("#filter tr td:first-child").filter(function() {
					      $(this).parent().toggle($(this).text().toLowerCase().indexOf(value) > -1)
					    });
					  });
//					display 필터링
					
					/*$("#form2 select").on("mouseenter",function(){
						var plus=0, minus=0, sum=0;
						$("#filter tr td:first-child[class='수입']").filter(function(){
							plus+=Number( $(this).next().next().next().text().trim())
						});
						$("#filter tr td:first-child[class='지출']").filter(function(){
							minus+=Number($(this).next().next().next().text().trim())
						});
						sum = plus - minus;
						$("#plus").val(plus);
						$("#minus").val(minus);
						$("#sum").val(sum);
					});
//					합계구하기
*/					$(":checkbox").on("change",function(){
						var plus=0, minus=0, sum=0;
						$(":checkbox:checked").filter(function(){
							
							$(this).parent().parent().find("td:first-child[class='수입']").filter(function(){
								plus+=Number( $(this).next().next().next().text().trim())
							});
							$(this).parent().parent().find("td:first-child[class='지출']").filter(function(){
								minus+=Number( $(this).next().next().next().text().trim())
							});
							
						});
						sum = plus - minus;
						$("#plus").val(plus).css("background-color", "rgba( 255, 255, 0, 0.3 )");
						$("#minus").val(minus).css("background-color", "rgba( 255, 255, 0, 0.3 )");
						$("#sum").val(sum).css("background-color", "rgba( 255, 255, 0, 0.3 )");
					});
//					선택합계
			}
		});
	};
	
	
	
	$("#form0").submit(function(){
		if(isNaN($("#addyear").val())||$("#addyear").val()=="" ){
			alert("숫자를 입력하세요.");
			$("#addyear").focus();
			return false;
		}
		
	});
	
	$("#month").click(function(){
		var currentYear = $("#year").val();
		var month = $("#month").val()-1;
		var lastDate = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		if( (currentYear % 4 === 0 && currentYear % 100 !== 0) || currentYear % 400 === 0 )
		lastDate[1] = 29;
		//각 달의 마지막 일을 계산, 윤년의 경우 년도가 4의 배수이고 100의 배수가 아닐 때 혹은 400의 배수일 때 2월달이 29일 임.
		for(i=1;i<=lastDate[month];i++){
		$("#day").append("<option value='"+i+"'>"+i+"</option>");
		}
	});
//	일추가
});
