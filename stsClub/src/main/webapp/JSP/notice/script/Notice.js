$(document).ready(function() {

	
	$("#file").mouseover(function(){
		$("#file").css("text-decoration","underline");
		$("#file").css("color","red");
		
	});
	$("#file").mouseleave(function(){
		$("#file").css("text-decoration","none");
		$("#file").css("color","black");
		
	});
	$("#file").click(function(){
		
			$("#before").css("display","none");
			$("#after").css("display","block");
			
	});

});