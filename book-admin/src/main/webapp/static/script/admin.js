$(function () {
	var Admin={
		init:function() {
			$("button[name=add_menu]").on("click",Admin.addMenu);
			$("#menu_back").on("click",Admin.menuBack);
			$("#menu_add_submit").on("click",Admin.addMenuSubmit);
			$("input[cmd=delete_menu]").on("click",Admin.delMenu);
			$("input[cmd=modify_user]").on("click",Admin.modify);
			$("input[cmd=update_menu]").on("click",Admin.modifyMenu)
    	},
    	modifyMenu:function() {
    		$("input[name=menu_name]").val("");
			$("input[name=menu_url]").val("");
			$("input[name=menu_sort]").val("");
			$("input[name=hd_id]").val("0");
			
			$("#modify_item_form").show();
    		$("#record_list_table").hide();
    		
    		var id = $(this).attr("name");
    		
    		$.get("/admin/menu/detail.json",
    				{"id":id},
    				function(data){
    					if (data.code == 1) {
    						layer.alert(data.msg,{
	    						icon:2,
	    						skin:'layer-ext-moon'
	    					});
    					} else if(data.code == 0) {
    						var msg = data.msg;
    						$("input[name=menu_name]").val(msg.name);
        					$("input[name=menu_url]").val(msg.url);
        					$("input[name=menu_sort]").val(msg.sort);
        					$("#hd_id").val(id);
    					}
    	    });
    	},
    	delMenu:function() {
    		var id = $(this).attr("name");
    		layer.confirm('确定删除此菜单?',{
    			btn:['确定','取消'] //按钮
    		},function(){
    			$.post("/admin/menu/del",
	    			{"id":id},
	    			function(data) {
	    				if(data.code == 0) {
	    					location.reload();
	    					layer.msg('删除成功!');
	    				} else if (data.code == 1) {
	    					window.location.href = "/login"
	    				} else {
	    					layer.alert(data.msg,{
	    						icon:2,
	    						skin:'layer-ext-moon'
	    					});
	    				}
	    			}
    	    	);
    		},function(){});
    	},
    	addMenuSubmit:function() {
    		var name = $("input[name=menu_name]").val();
    		var url = $("input[name=menu_url]").val();
    		var sort = $("input[name=menu_sort]").val();
    		var id = $("#hd_id").val();
    		if (name == null || name.trim() == '') {
    			layer.alert('菜单名称不能为空',{
					icon:2,
					skin:'layer-ext-moon'
				});
    			return;
    		}
    		if(url == null || url.trim() == '') {
    			layer.alert('菜单URL不能为空',{
					icon:2,
					skin:'layer-ext-moon'
				});
    			return ;
    		}
    		if(sort == null || sort.trim() == '') {
    			layer.alert('菜单位置不能为空',{
					icon:2,
					skin:'layer-ext-moon'
				});
    			return;
    		}
    		/*var reg = /^\d+$/;
			if(!reg.test(sort)) {
				layer.alert('菜单位置必须为数字',{
					icon:2,
					skin:'layer-ext-moon'
				});
    			return;
			}*/
    		
    		$.post("/admin/menu/addOrUpdate",
    			{
    				"name":name,
    				"url":url,
    				"sort":sort,
    				"id":id
    			},
    			function(data) {
    				if(data.code == 0) {
    					$("input[name=menu_name]").val("");
    					$("input[name=menu_url]").val("");
    					$("input[name=menu_sort]").val("");
    					location.reload();
    					layer.msg('操作成功!');
    				} else {
    					layer.alert(data.msg,{
							icon:2,
							skin:'layer-ext-moon'
						});
    				}
    			},
    			"json"
    		);
    	},
    	addMenu:function() {
    		$("input[name=menu_name]").val("");
			$("input[name=menu_url]").val("");
			$("input[name=menu_sort]").val("");
			$("input[name=hd_id]").val("0");
			
    		$("#modify_item_form").slideDown();
    		$("#record_list_table").hide();
    	},
    	menuBack:function() {
    		$("#modify_item_form").hide();
    		$("#record_list_table").fadeIn();
    	}
  	};
	
	var User={
		init:function() {
			$("button[name=add_user]").on("click",User.addUser);
			$("#user_back").on("click",User.userBack);
			$("#user_add_submit").on("click",User.addUserSubmit);
			$("input[cmd=delete_user]").on("click",User.del);
			$("input[cmd=modify_user]").on("click",User.modify);
			$("#user_right_submit").on("click",User.rightSubmit);
			$("#user_right_reset").on("click",User.rightReset);
			$("#all_rights").hide();
    	},
    	rightReset:function() {
    		$("#all_rights").hide()
    	},
    	rightSubmit:function() {
    		var id=$("#user_right_modify_id").val();
    		var boxes = $("input[name=u_right]");
    		var rights="";
    		for(i=0 ; i<boxes.length ; i++) {
    			if(boxes[i].checked) {
    				rights=rights+boxes[i].value+",";
    			}
			}
    		$.post("/admin/user/modify",
    			{
    				"id":id,
    				"rights":rights
    			},
    			function(data) {
    				if(data.code == 0) {
    					location.reload();
    					layer.msg("修改成功!");
    				} else {
    					layer.alert(data.msg,{
    						icon:2,
    						skin:'layer-ext-moon'
    					});
    				}
    			}
    		);
    	},
    	modify:function() {
    		var id=$(this).attr("name");
    		$("#user_right_modify_id").val(id);
    		$("#all_rights").insertAfter("#tr_" + id).show();//★
    		var boxes = $("input[name=u_right]");
    		
    		//初始化都是没有选中
    		for(i=0 ; i<boxes.length ; i++) {
				boxes[i].checked=false;
			}
    		
    		if($(this).attr("admin") == 1) { //超级管理员有所有权限
    			for(i=0 ; i<boxes.length ; i++) {
    				boxes[i].checked=true;
    			}
    		} else { //普通管理员
    			var rights=$(this).attr("right");
    			var rs=rights.split(",")||[];
    			for(i=0 ; i<rs.length ; i++) {
    				if(rs[i] != "") {
    					for(j=0 ; j<boxes.length ; j++) {
    						if(boxes[j].value == rs[i]) {
    							boxes[j].checked=true;
    						} 
    	    			}
    				}
    			}
    		}
    	},
    	del:function() {
    		var id = $(this).attr("name");
    		layer.confirm('确定删除此用户?',{
    			btn:['确定','取消']
    		},function(){
    			$.post("/admin/user/del",
    	    			{"id":id},
    	    			function(data) {
    	    				if(data.code == 0) {
    	    					location.reload();
    	    					layer.msg("删除成功!");
    	    				} else {
    	    					layer.alert(data.msg,{
                                    icon:2,
                                    skin:'layer-ext-moon'
                                });
    	    				}
    	    			}
    	    		);
    		});
    	},
    	addUserSubmit:function() {
    		var isAdmin = 0;
    		for(i=0 ; i<$("input[name=user_isAdmin]").length ; i++) {
    			if($("input[name=user_isAdmin]")[i].checked) {
    				isAdmin = $("input[name=user_isAdmin]")[i].value;
    				break;
    			}
    		}
    		
    		var user_name = $("input[name=user_name]").val();
    		var user_email = $("input[name=user_email]").val();
    		
    		if (user_name == null || user_name.trim() == '') {
    			layer.alert('用户姓名不能为空!',{
					icon:2,
					skin:'layer-ext-moon'
				});
    			return;
    		}
    		if (user_email == null || user_email.trim() == '') {
    			layer.alert('登录账号不能为空!',{
    				icon:2,
    				skin:'layer-ext-moon'
    			});
    			return;
    		}
    		if(!/^[0-9a-zA-Z]{6,30}$/.test(user_email)) {
    			layer.alert('由6-30位数字或字母组成!',{
    				icon:2,
    				skin:'layer-ext-moon'
    			});
    			return;
    		}
    		
    		$.post("/admin/user/add",
    			{
    				"name":user_name,
    				"email":user_email,
    				"isAdmin":isAdmin
    			},
    			function(data) {
    				if(data.code == 0) {
    					$("input[name=user_name]").val("");
    					$("input[name=user_email]").val(""),
    					$("input[name=user_isAdmin]").val("")
    					location.reload();
    					layer.msg('添加成功!');
    				} else {
    					layer.alert(data.msg,{
    						icon:2,
    						skin:'layer-ext-moon'
    					});
    				}
    			}
    		);
    	},
    	addUser:function() {
    		$("#modify_item_form").show();
    		$("#record_list_table").hide();
    	},
    	userBack:function() {
    		$("#modify_item_form").hide();
    		$("#record_list_table").show();
    	}
	};
	
	var Redis={
		init:function() {
			$("select[name=ip_port_selector]").on('change',Redis.changeRedis);
			var host=RedisHost.host+":"+RedisHost.port;
			var options=$("select[name=ip_port_selector]").children("option")||[];
			for(i=0 ; i<options.length ; i++) {
				if(host == options[i].value) {
					options[i].selected=true;
				}
			}
		},
	
		changeRedis:function() {
			ip_port=$("select[name=ip_port_selector] option:selected").text().split(":");
			window.location.href="/cache?host=" + ip_port[0] + "&port=" + ip_port[1];
		}
	};
	
	var App = {
		init:function() {
			//修改按钮
			$("button[cmd=modify]").on('click', function() {
				app_id=$(this).attr("app_id");
				window.location.href = "/app/modify?id=" + app_id + "&type=1";
			});
			//更新图片
			$("button[cmd=modify_img]").on('click', function() {
				app_id=$(this).attr("app_id");
				window.location.href = "/app/modify?id=" + app_id + "&type=2";
			});
			//修改按钮
			$("#modify_submit").on("click", App.modify);
			$("#modify_cancel").on("click", function() {window.location.href="/app";});
		},
		
		modify: function() {
			id = $("input[name=id]").val();
			secondName = $("input[name=second_name]").val();
			size = $("input[name=size]").val();
			os = $("input[name=os]").val();
			apk = $("input[name=apk]").val();
			app_desc = $("textarea[name=app_desc]").val();
			
			$.post("/app/modify.json",
				{"id":id,"secondName":secondName,"size":size,"os":os,"apk":apk,"app_desc":app_desc},
				function(data) {
					if(data.code != 0) {
						alert(data.msg);
					} else {
						window.location.href="/app";
					}
				}, 
				"json");
		}
	};
	
	var UserInfo = {
			init:function() {
				$("#user_info_update").on("click",UserInfo.updateUserInfo);
				
				layer.prompt({title:'请输入密码',formType:1},function(pass,index){
					layer.close(index);
					$.post("/admin/user/validate.json",
							{
								"password":pass
							},
							function(data){
								if (data.code == 1) {
									window.location.href = "/login";
								} else if (data.code == 2) {
									layer.msg('密码错误');
									location.reload();
								} else if(data.code == 0) {
									$("input[name=name]").val(data.msg.name);
									$("input[name=password]").val(data.msg.password);
									$("input[name=email]").val(data.msg.email);
									$("input[name=phone]").val(data.msg.phone);
									
									$("#modify_item_form").show();
								}
							});
					
				});
			},
			updateUserInfo:function(){
				var name = $("input[name=name]").val();
				var password = $("input[name=password]").val();
				var email = $("input[name=email]").val();
				var phone = $("input[name=phone]").val();
				
				if(name != null && "" != name.trim()) {
		    		if(!/^[0-9a-zA-Z]{6,30}$/.test(name)) {
		    			layer.alert('账号由6-30位数字或字母组成!',{
		    				icon:2,
		    				skin:'layer-ext-moon'
		    			});
		    			return;
		    		}
				}
				
				if(password != null && "" != password.trim()) {
		    		if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,20}$/.test(password)) {
		    			layer.alert('密码格式不正确!',{
		    				icon:2,
		    				skin:'layer-ext-moon'
		    			});
		    			return;
		    		}
				}
				
				if(email != null && "" != email.trim()) {
					var regEmail =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
					if(!regEmail.test(email)) {
						layer.alert('email格式不正确!',{
		    				icon:2,
		    				skin:'layer-ext-moon'
		    			});
		    			return;
					}
					
				}
				
				if(phone != null && "" != phone.trim()) {
					var regMobile = /^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/; 
					if(!regMobile.test(phone)) {
						layer.alert('手机号码格式不正确!',{
		    				icon:2,
		    				skin:'layer-ext-moon'
		    			});
		    			return;
					}
				}
				
				$.post("/admin/user/update.json",
						{
							"name":name,
							"password":password,
							"email":email,
							"phone":phone,
						},
						function(data) {
							if(data.code == 0) {
//								location.reload();
		    					layer.msg('修改成功!');
		    					window.location.href = "/login/out";
							} else {
								layer.alert(data.msg,{
		    						icon:2,
		    						skin:'layer-ext-moon'
		    					});
							}
						}, 
				"json");
			}
	};
	
	/**
	 * 订单统计
	 */
	var Statis = {
			init:function(path) {
				//区分城市统计
				$("#query_select").attr("action",path);
				//点击重置
				$('#query_reset').on('click',Statis.queryReset);
				//点击查询
				$('#query_btn').on('click',Statis.queryByButton);
				//改变每页显示的条数
				$('select[name=changePageSize]').on('change',Statis.pageSizeChange);
				//改变页面数
				$('select[name=changePageNo]').on('change',Statis.pageNoChange);
				//获取焦点
				$('#startDate').on('focus',Statis.lostFocus);
				$('#endDate').on('focus',Statis.lostFocus);
			},
			lostFocus:function() {
				$(this).blur();
			},
			queryReset:function(){
				$('input[name=shopId]').attr('checked',false);
				$('input[name=typeId]').attr('checked',false);
				$('input[name=payId]').attr('checked',false);
				$('input[name=statusId]').attr('checked',false);
				$('#startDate').val('');
				$('#endDate').val('');
			},
			pageNoChange:function() {
				var pageNo = $(this).val();
				$("#pageNo").val(pageNo);
				$('#query_select').submit();
			},
			pageSizeChange:function() {
				var pageSize = $(this).val();
				$("#pageSize").val(pageSize);
				$("#pageNo").val('1');
				$('#query_select').submit();
			},
			queryByButton:function() {
				var startDate = $('#startDate').val();
				var endDate = $('#endDate').val();
				var shopIds = $('input[name=shopId]');
				var typeIds = $('input[name=typeId]');
				var payIds = $('input[name=payId]');
				var statusIds = $('input[name=statusId]');
				
				//日期判断
				if(startDate == '' || endDate == '') {
					layer.alert('开始日期或结束日期不能为空',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}
				if (startDate != '' && endDate != '') {
					var sDate = new Date(startDate);
					var eDate = new Date(endDate);
					if(sDate > eDate) {
		    			layer.alert('开始日期不能大于结束日期',{
							icon:2,
							skin:'layer-ext-moon'
						});
		    			return;
					}
				}
				
				//分店
				/*var shopIdsNum = 0;
				for(i = 0;i < shopIds.length; i++) {
					if(shopIds[i].checked == false) {
						shopIdsNum++;
					}
				}
				if(shopIdsNum == shopIds.length) {
					layer.alert('至少需要选择一个分店',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}*/

				//套餐
				/*var typeIdsNum = 0;
				for(i = 0; i < typeIds.length; i++) {
					if(typeIds[i].checked == false) {
						typeIdsNum++;
					}
				}
				if(typeIdsNum == typeIds.length) {
					layer.alert('至少需要选择一个套餐',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}*/
				//支付方式
				/*var payIdsNum = 0;
				for(i = 0; i < payIds.length; i++) {
					if(payIds[i].checked == false) {
						payIdsNum++;
					}
				}
				if(payIdsNum == payIds.length) {
					layer.alert('至少需要选择一个支付方式',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}*/
				
				//订单状态
				/*var statusIdsNum = 0;
				for(i = 0; i < statusIds.length;i++) {
					if(statusIds[i].checked == false) {
						statusIdsNum++;
					}
				}
				if(statusIdsNum == statusIds.length) {
					layer.alert('至少需要选择一个订单的状态',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}*/
				

				//提交
				$("#pageNo").val('1');
				$('#query_select').submit();
			},
	};
	
	/**
	 * 2017-09-07-订单查询
	 * 
	 */
	var Order = {
		init:function(path) {
			//区分城市查询地址
			$("#query_select").attr("action",path);
			//点击查询
			$('#query_btn').on('click',Order.queryByButton);
			//点击重置
			$('#query_reset').on('click',Order.queryReset);
			//改变每页显示的条数
			$('select[name=changePageSize]').on('change',Order.pageSizeChange);
			//改变页面数
			$('select[name=changePageNo]').on('change',Order.pageNoChange);
			//获取焦点
			$('#startDate').on('focus',Statis.lostFocus);
			$('#endDate').on('focus',Statis.lostFocus);
		},
		lostFocus:function() {
			$(this).blur();
		},
		pageNoChange:function() {
			var pageNo = $(this).val();
			$("#pageNo").val(pageNo);
			$('#query_select').submit();
		},
		pageSizeChange:function() {
			var pageSize = $(this).val();
			$("#pageSize").val(pageSize);
			$("#pageNo").val('1');
			$('#query_select').submit();
		},
		queryByButton:function() {
			var startDate = $('#startDate').val();
			var endDate = $('#endDate').val();
//			console.log('startDate:' + startDate + ",endDate:" + endDate);
			//日期判断
			if (startDate != null && endDate != null) {
				var sDate = new Date(startDate);
				var eDate = new Date(endDate);
				if(sDate > eDate) {
	    			layer.alert('开始日期不能大于结束日期',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}
			}
			//如果有电话号码了也判断一下
			var phone = $('#phone').val();
			if(phone != null && '' != phone.trim()) {
				var regMobile = /^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/; 
				if(!regMobile.test(phone)) {
					layer.alert('手机号码不正确!',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return;
				}
			}

			//提交
			$("#pageNo").val('1');
			$('#query_select').submit();
		},
		queryReset:function() {
			$('#phone').val('');
			$('#startDate').val('');
			$('#endDate').val('');
			$('#shopId').val('0');
			$('#typeId').val('0');
			$('#payId').val('0');
			$('#statusId').val('-1');
		}
	};
	
	/**
	 * 客户预约
	 */
	var Book = {
			init:function(path) {
				$("button[name=add_book]").on("click",Book.addBook);
				$("#book_back").on("click",Book.bookBack);
				$("#book_add_submit").on("click",Book.addBookSubmit);
				$("input[cmd=delete_book]").on("click",Book.delBook);
//				$("input[name=query_select]").on("click",Book.selectByParam);
				$("input[cmd=update_book]").on("click",Book.modifyBook);
				$("#query_select").attr("action",path);
				
				
/*				$("#record_list_table tbody tr").on("mouseover",function(){
					$(this).find("td").addClass("mouse_color");
				});
				$("#record_list_table tbody tr").on("mouseout",function(){
					$(this).find("td").removeClass("mouse_color");
				});
				$("#record_list_table tbody tr:odd").find("td").addClass("tr_odd"); 
				$("#record_list_table tbody tr:even").find("td").addClass("tr_even"); */
				//获取焦点
				$('#datepicker').on('focus',Statis.lostFocus);
				$('#datetimepicker').on('focus',Statis.lostFocus);
			},
			lostFocus:function() {
				$(this).blur();
			},
	    	selectByParam:function() {
	    		var shopId = $("select[name=param_shop]").val();
	    		var bookTime = $("input[name=param_book_time]").val();
	    		
	    		var condition = "?shopId=" + shopId;
	    		if(bookTime == null || '' == bookTime.trim()) {
	    			layer.alert('请选择日期',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			return null;
	    		}
	    		condition += "&bookTime=" + bookTime;
	    		window.location.href= "/book" + condition;
	    	},
	    	modifyBook:function() {
	    		$("#record_list_table").hide();
	    		$("#condtion").hide();
	    		$("#modify_item_form").show();
	    		$("#form_title").html("修改预约:");
	    		
	    		$("input[name=username]").val("");
				$("input[name=phone]").val("");
				$("select[name=shop]").val("0");
				$("input[name=book_time]").val('');
				$("select[name=type]").val('0');
				$("select[name=payType]").val('0');
				$("input[name=payPrice]").val('');
				$("input[name=payCode]").val('');
			
//				$("input:radio[name='book_status']").eq(1).attr('checked','checked');
				var book_status = $("input:radio[name='book_status']");
				for(i = 0; i < book_status.length; i++) {
					if(book_status[i].value == 1) {
						book_status[i].checked = true;
					}
				}
//				console.log("选中的radio的值：" +$("input[name='book_status']:checked").val());
				
				$("textarea[name=backUp]").val('');
				$("input[name=hd_id]").val("0");
	    		
				var id = $(this).attr("name");
	    		$.get("/book/detail.json",
	    				{"id":id},
	    				function(data){
	    					if (data.code == 1) {
	    						layer.alert(data.msg,{
		    						icon:2,
		    						skin:'layer-ext-moon'
		    					});
	    					} else if(data.code == 0) {
	    						var msg = data.msg;
	    						$("input[name=username]").val(msg.username);
	        					$("input[name=phone]").val(msg.phone);
	        					$("select[name=shop]").val(msg.shopId);
	        					$("input[name=bookTime]").val(timeStamp2String(msg.bookTime));
	        					$("select[name=type]").val(msg.typeId);
	        					$("select[name=payType]").val(msg.payId);
	        					$("input[name=payPrice]").val(msg.payPrice);
	        					$("input[name=payCode]").val(msg.payCode);
	        					var book_status = $("input:radio[name='book_status']");
	        					for(i = 0; i < book_status.length; i++) {
	        						if(book_status[i].value == msg.status) {
	        							book_status[i].checked = true;
	        						}
	        					}
	        					$("textarea[name=backUp]").val(msg.backUp);
	        					$("#hd_id").val(id);
	    					}
	    			});
	    	},
	    	delBook:function() {
	    		var id = $(this).attr("name");
	    		layer.confirm('确定删除此订单?',{
	    			btn:['确定','取消'] //按钮
	    		},function(){
	    			$.post("/book/del",
		    			{"id":id},
		    			function(data) {
		    				if(data.code == 0) {
//		    					location.reload();
		    					$("#tr_"+id).remove();
		    					layer.msg('删除成功!');
		    				} else if (data.code == 1) {
		    					window.location.href = "/login"
		    				} else {
		    					layer.alert(data.msg,{
		    						icon:2,
		    						skin:'layer-ext-moon'
		    					});
		    				}
		    			}
	    	    	);
	    		},function(){});
	    	},
	    	addBookSubmit:function() {
	    		$("#book_add_submit").attr("disabled","disabled");
	    		var id = $("#hd_id").val();
	    		var username = $("input[name=username]").val();
	    		var phone = $("input[name=phone]").val();
	    		var shop = $("select[name=shop]").val();
	    		var bookTime =  $("input[name=bookTime]").val();
	    		var type = $("select[name=type]").val();
	    		var payType = $("select[name=payType]").val();
	    		var payCode = $("input[name=payCode]").val();
	    		var payPrice = $("input[name=payPrice]").val();
	    		var status = $("input[name='book_status']:checked").val();
	    		var backUp = $("textarea[name=backUp]").val();
	    		
	    		//手机号码
	    		if(phone == null || '' == phone.trim()) {
	    			layer.alert('手机号码不能为空!',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			$("#book_add_submit").removeAttr('disabled');
	    			return;
	    		} else {
	    			var regMobile = /^1[3|4|5|6|7|8|9][0-9]{1}[0-9]{8}$/; 
	    			if(!regMobile.test(phone)) {
	    				layer.alert('手机号码不正确!',{
							icon:2,
							skin:'layer-ext-moon'
						});
	    				$("#book_add_submit").removeAttr('disabled');
		    			return;
	    			}
	    		}
	    		//分店
	    		if(shop == 0) {
	    			layer.alert('请选择分店',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			$("#book_add_submit").removeAttr('disabled');
	    			return;
	    		}
	    		//订单状态
	    		if(id == 0 && status != 1) {
	    			layer.alert('请选择预约状态!',{
						icon:2,
						skin:'layer-ext-moon'
					});
	    			$("#book_add_submit").removeAttr('disabled');
	    			return;
	    		}
	    		
	    		$.post("/book/addorupdate",
	    				{
	    					"username":username,
	    					"phone":phone,
	    					"shop":shop,
	    					"bookTime":bookTime,
	    					"type":type,
	    					"payType":payType,
	    					"payCode":payCode,
	    					"payPrice":payPrice,
	    					"status":status,
	    					"backUp":backUp,
	    					"id":id
	    				},function(data){
//	    					console.log(bookTime.substring(0,10));
		    				if(data.code == 0) {
		    					//location.reload();
		    					if(id == 0) {
		    						layer.msg('预约成功!');
		    					} else {
		    						layer.msg('修改成功!');
		    					}
		    					
		    					//添加或修改成功后显示修改后的分店和日期
		    					$("#param_shop").val(shop);
		    					$("#datepicker").val(bookTime.substring(0,10));
		    					$("#query_select").submit();
		    				} else if (data.code == 4) {
		    					window.location.href = "/login"
		    				} else {
		    					$("#book_add_submit").removeAttr('disabled');
		    					layer.alert(data.msg,{
		    						icon:2,
		    						skin:'layer-ext-moon'
		    					});
		    				}
	    		});
	    		
	    	},
	    	addBook:function() {
	    		$("#record_list_table").hide();
	    		$("#condtion").hide();
	    		$("#modify_item_form").show();
	    		$("#form_title").html("添加新预约:")
	    		
	    		$("input[name=username]").val("");
				$("input[name=phone]").val("");
				$("select[name=shop]").val("0");
				$("input[name=book_time]").val('');
				$("select[name=type]").val('0');
				$("select[name=pay_type]").val('0');
				$("input[name=pay_price]").val('');
				$("input[name=pay_code]").val('');
			
//				$("input:radio[name='book_status']").eq(1).attr('checked','checked');
				var book_status = $("input:radio[name='book_status']");
				for(i = 0; i < book_status.length; i++) {
					if(book_status[i].value == 1) {
						book_status[i].checked = true;
					}
				}
//				console.log("选中的radio的值：" +$("input[name='book_status']:checked").val());
				
				$("textarea[name=back_up]").val('');
				$("#hd_id").val("0");
				
	    	},
	    	bookBack:function() {
	    		$("#modify_item_form").hide();
	    		$("#record_list_table").fadeIn();
	    		$("#condtion").show();
	    	}
	};
	
	var path = window.location.pathname;
	if(path == "/admin/menus") {
		Admin.init();
	} else if(path == "/admin/users") {
		User.init();
	} else if(/^\/book\/\w+$/.test(path)) {
		Book.init(path);
	} else if(/app/.test(path)) {
		App.init();
	} else if("/admin/myinfo" == path) {
		UserInfo.init();
	} else if(/^\/order\/\w+$/.test(path)) {
		Order.init(path);
	} else if(/^\/order\/statistics\/\w+$/.test(path)) {
		Statis.init(path);
	}
})

/**
 * 精确到分钟
 * @param time
 * @returns {String}
 */
function timeStamp2String(time){  
    var datetime = new Date();  
    datetime.setTime(time);  
    var year = datetime.getFullYear();  
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;  
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();  
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();  
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();  
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();  
    return year + "-" + month + "-" + date+" "+hour+":"+minute ;//+":"+second;  
}   

/**
 * JS加减日期
 * @param date	
 * @param days
 * @returns {String}
 */
function addDate(date,days){ 
    var d=new Date(date); 
    d.setDate(d.getDate()+days); 
    var m=d.getMonth()+1; 
    return d.getFullYear()+'-'+m+'-'+d.getDate(); 
} 

/**
 * 月份处理
 * @returns {String}
 */
function addMonth(month) {
	var d = new Date();
	d.setMonth(d.getMonth()+month);
	return d.getFullYear()+'-'+d.getMonth()+'-'+d.getDate(); 
}