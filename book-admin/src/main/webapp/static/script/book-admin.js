  /**
   * book-admin使用
   */
(function($){
	
	var Login = {
		init:function(){
			//登录验证
			$("#loginBtn").on("click",Login.validate);
		},
		validate:function(){
			$.post("/login/validate.json",
				{
					"username":$("input[name=username]").val(),
					"password":$("input[name=password]").val(),
					"remember":$("#remember").is(":checked"),
				},
				function(d) {
					//var d=JSON.parse(data);
					var _url=($("input[name=src]").val()||"/index");
					if(d.code == 0) {
//						alert(d.msg);
//						layer.alert(d.msg);
						layer.alert(d.msg,{
							icon:2,
							skin:'layer-ext-moon'
						});
					} else {
						window.location.href=_url;
					}
				},
				"json"
			);
		}
	};
	
	//初始化事件
	Login.init();
	
})(jQuery);