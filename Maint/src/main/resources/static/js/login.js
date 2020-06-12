
$(function() {
	
	// 点击登录按钮
	$('#login').click(function() {
		login();
	});
	// 回车事件
	$('#user_account, #user_password').keypress(function (event) {
		if (13 == event.keyCode) {
			login();
		}
	});
});
// 登录
function login() {
		
	var	user_account = $("#user_account").val();
	if(""==user_account)
	{
	$("#user_account").focus();
	return;
	}
	var	user_password = $("#user_password").val();
	if(""==user_password)
	{
	$("#user_password").focus();
	return;
	}
	
	
	var	rememberMe=$("#rememberMe").is(':checked');
	
	
	
		$.ajax({
			type : "POST",
			url : "/login",
			dataType: "json",
			data : {"username": user_account,"password": user_password,"rememberMe": rememberMe},
			success : function(r) {
				if (r.code == 0) {
                    location.href = '/index';
                } else {
					$("#err").html(r.msg);
                }
					
				}
			
		});
}
	
	
	
