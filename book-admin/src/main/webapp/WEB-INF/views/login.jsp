<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta content="telephone=no" name="format-detection" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="十月爱四维后台管理系统">
<meta name="author" content="shiyueai">
<title>十月爱四维后台管理系统</title>
<link rel="shortcut icon" href="../../static/images/logo.ico">
<!-- Bootstrap core CSS -->
<link href="../../static/style/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../static/style/signin.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy this line! -->
<!--[if lt IE 9]><script src="../../static/script/ie8-responsive-file-warning.js"></script><![endif]-->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="../../static/script/html5shiv.js"></script>
      <script src="../../static/script/respond.js"></script>
    <![endif]-->
</head>

<body id="login">
	<div class="container"> 
		<form class="form-signin" method="post"> 
			<div class="login_banner">
					<!-- -->
					<b class="login_icon"></b>
          			<span class="form-signin-heading login_title">十月爱四维摄影管理系统</span>
			</div>
			<input type="hidden" name="src" value="${src }" />
			<div class="input-prepend" title="username" data-rel="tooltip">
				<span class="add-on">
					<i class="icon-user"></i>
				</span>
				<input type="text" name="username" class="form-control" placeholder="请输入登录账号" required="required" autofocus="autofocus">
			</div>
			<div class="clearfix"></div>
			<div class="input-prepend" title="password" data-rel="tooltip">
				<span class="add-on">
					<i class="icon-lock"></i>
				</span>
				<input type="password" name="password" class="form-control" placeholder="请输入密码" required="required">
			</div>
			<div class="clearfix"></div>
			<label class="checkbox"> 
				<input type="checkbox" checked="checked" id="remember" name="remember"/>记住我
			</label>
			
			<p class="center span5">
				<button id="loginBtn" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
			</p> 
		</form>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../../static/script/jquery-3.2.1.min.js"></script>
	<script src="../../static/script/layer/layer.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../../static/script/book-admin.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			//回车键登录
			$(document).keyup(function(event){
				if (event.keyCode == 13) {
					$("#loginBtn").trigger("click");
				}
			});
		});
	</script>
</body>
</html>
