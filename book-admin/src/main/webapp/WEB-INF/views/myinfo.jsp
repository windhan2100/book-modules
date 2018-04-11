<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
</head>
<body>
 <%@ include file="common/nav.jsp" %>

    <div class="container-fluid">
      <div class="row">
        <%@ include file="common/nav_sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">我的信息:</h2>
          <div class="table-responsive">
            <form action="/admin/user/update" method="post" id="modify_item_form" style="display:none;">
				<table id="modify_item_table" class="table table-striped">
					<thead>
		                <tr>
							<th colspan="2">管理我的信息：</th>
		                </tr>
		            </thead>
		            <tbody>
						<tr height="30px">
							<td align="right" width="150px">登录账号：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 200px;" name="name" required="required" placeholder="由6-16位数字或字母组成" value=""/>&nbsp;由6-16位数字或字母组成
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="200px">登录密码：</td>
							<td style="background-color: #FFFFFF">
								<input type="password" style="width: 200px;" name="password" required="required" placeholder="字母、数字、下划线，必须同时含有字母和数字" value=""/>&nbsp;字母、数字、下划线，必须同时含有字母和数字
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="200px">电子邮箱：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 200px;" name="email" required="required" placeholder="请输入电子邮箱" value=""/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="200px">手机号码：</td>
							<td style="background-color: #FFFFFF">
								<input type="number" style="width: 200px;" name="phone" required="required" placeholder="请输入手机号码" value=""/>
							</td>
						</tr>
						<tr height="32px">
							<td align="right"></td>
							<td style="background-color: #FFFFFF">
								<input id="user_info_update" name="user_info_update" type="button" value="提交"/>
								<span style="color:red;">&nbsp;* 提交后您需要重新登录!</span>
								<%--
								<input id="user_info_back" type="button" value="返回"/>
								 --%>
							</td>
						</tr>
		            </tbody>
				</table>
			</form>
          </div>
        </div>
      </div>
    </div>
    
     <c:catch var="exception">
	  	<%@ include file="common/footer.jsp" %>
	 </c:catch>
	 <script src="../../static/script/admin.js"></script>
</body>
