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
          <h2 class="sub-header">权限管理:</h2>
          <div class="table-responsive">
            <form action="/admin/user/add" method="post" id="modify_item_form" style="display: none;">
				<table id="modify_item_table" class="table table-striped">
					<thead>
		                <tr>
							<th colspan="2">添加管理员：</th>
		                </tr>
		            </thead>
		            <tbody>
						<tr height="30px">
							<td align="right" width="150px">姓名：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 150px;" name="user_name" required="required" placeholder="请输入姓名"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">登录账号：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 150px;" name="user_email" required="required" placeholder="字母或数字"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">超级管理员：</td>
							<td style="background-color: #FFFFFF">
								<input type="radio" name="user_isAdmin" value="0"/>普通管理员&nbsp;&nbsp;
								<input type="radio" name="user_isAdmin" value="1"/>超级管理员
							</td>
						</tr>
						<tr height="32px">
							<td align="right"></td>
							<td style="background-color: #FFFFFF">
								<input id="user_add_submit" type="button" value="提交"/>
								<input id="user_back" type="button" value="返回"/>
							</td>
						</tr>
		            </tbody>
				</table>
			</form>
			<table id="record_list_table" class="table table-striped">
				<thead>
	                <tr>
						<th>编号</th>
	                  	<th>姓名</th>
	                  	<th>登录账号</th>
	                  	<th>是否超管</th>
	                  	<th>权限</th>
	                  	<th>添加者</th>
	                  	<th>添加时间</th>
	                  	<th>
	                  		<button name="add_user">添加管理员</button>
	                  	</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:choose>
	            		<c:when test="${fn:length(users) > 0 }">
	            			<c:forEach items="${users }" var="item" varStatus="status">
								<tr id="tr_${item.id }" style="background-color: #FFFFFF;">
									<td align="center" width="50px">${status.count }</td>
									<td>${item.chineseName }</td>
									<td>${item.name }</td>
									<td>
										<c:if test="${item.isAdmin == 0 }">No</c:if>
										<c:if test="${item.isAdmin == 1 }">Yes</c:if>
									</td>
									<td>
										<c:if test="${item.isAdmin == 0 }">${item.rights }</c:if>
										<c:if test="${item.isAdmin == 1 }">所有权限</c:if>
									</td>
									<td>${item.creatorName }</td>
									<td><fmt:formatDate value="${item.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>
										<input cmd="delete_user" type="button" name="${item.id }" value="删除"/>
										<input cmd="modify_user" type="button" admin="${item.isAdmin }" right="${item.rights }" name="${item.id }" value="权限管理"/>
									</td>
								</tr>
							</c:forEach>
							<tr id="all_rights" style="display:none;">
								<td colspan="8">
									<input id="user_right_modify_id" type="hidden" name="id" value=""/>
									<ul>
										<c:forEach var="_menu" items="${menus }" varStatus="_mstatus">
											<li>
											<input id="${_menu.id}" type="checkbox" name="u_right" value="${_menu.id}"/>
											<label style="font-weight:normal;" for="${_menu.id }">&nbsp;${_menu.name }</label>
											</li>
										</c:forEach>
										<button id="user_right_submit">提交</button>
										<button id="user_right_reset">取消</button>
									</ul>
								</td>
							</tr>
	            		</c:when>
	            		<c:otherwise>
	            			<tr>
								<td colspan="8">还没有管理员...</td>
			                </tr>
	            		</c:otherwise>
	            	</c:choose>
				</tbody>
			</table>
          </div>
        </div>
      </div>
    </div>
    
     <c:catch var="exception">
	  	<%@ include file="common/footer.jsp" %>
	 </c:catch>
	 <script src="../../static/script/admin.js"></script>
</body>
