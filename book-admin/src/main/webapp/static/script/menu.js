$(function () {
	/**
	 * 渠道合作相关js
	 */
	var Menu={
		init:function() {
			var path=location.pathname;
			if("/index" == path) {
				$("#a_0").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/cooperation/.test(path)) {
				$("#a_1").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/app/.test(path)) {
				$("#a_5").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/cache/.test(path)) {
				$("#a_3").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/book\/beijing/.test(path)) {
				$("#a_6").css("color","#fff").css("backgroundColor","#428bca");
			} else if("/admin/users" == path) {
				$("#a_100").css("color","#fff").css("backgroundColor","#428bca");
			} else if("/admin/menus" == path) {
				$("#a_101").css("color","#fff").css("backgroundColor","#428bca");
			} else if("/admin/myinfo" == path) {
				$("#a_102").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/book\/shanghai/.test(path)) {
				$("#a_7").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/order\/beijing/.test(path)) {
				$("#a_8").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/order\/shanghai/.test(path)) {
				$("#a_9").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/order\/statistics\/beijing/.test(path)) {
				$("#a_10").css("color","#fff").css("backgroundColor","#428bca");
			} else if(/order\/statistics\/shanghai/.test(path)) {
				$("#a_11").css("color","#fff").css("backgroundColor","#428bca");
			}
    	},
  	};
	
	Menu.init();
})