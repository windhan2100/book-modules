<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
</head>
<body >
 <%@ include file="common/nav_mobile.jsp" %>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">菜单管理:</h2>
          <div class="table-responsive">
            <form action="/admin/menu/add" method="post" id="modify_item_form" style="display: none;">
            	<p id="notifaction_menu" style="text-align: center;color: #FF0000;font-weight: bold;"></p>
            	<input type="hidden" id="hd_id" >
				<table id="modify_item_table" class="table table-striped">
					<thead>
		                <tr>
							<th colspan="2">添加新菜单：</th>
		                </tr>
		            </thead>
		            <tbody>
						<tr height="30px">
							<td align="right" width="150px">菜单名称：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 150px;" name="menu_name" required="required" placeholder="请输入菜单名"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">菜单URL：</td>
							<td style="background-color: #FFFFFF">
								<input type="text" style="width: 250px;" name="menu_url" required="required" placeholder="请输入菜单URL"/>
							</td>
						</tr>
						<tr height="30px">
							<td align="right" width="150px">菜单位置：</td>
							<td style="background-color: #FFFFFF">
								<input type="number" style="width: 100px;" name="menu_sort" required="required" placeholder="序号"/>
							</td>
						</tr>
						<tr height="32px">
							<td align="right"></td>
							<td style="background-color: #FFFFFF">
								<input id="menu_add_submit" type="button" value="提交"/>
								<input id="menu_back" type="button" value="返回"/>
							</td>
						</tr>
		            </tbody>
				</table>
			</form>
			<table id="record_list_table" class="table table-striped">
				<thead>
	                <tr>
						<th>ID</th>
	                  	<th>名称</th>
	                  	<th>url</th>
	                  	<th>序号</th>
	                  	<th>添加者</th>
	                  	<th>添加时间</th>
	                  	<th>
	                  		<button name="add_menu">添加菜单</button>
	                  	</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:choose>
	            		<c:when test="${fn:length(records) > 0 }">
	            			<c:forEach items="${records }" var="item">
								<tr id="tr_${item.id }" style="background-color: #FFFFFF;">
									<td align="center" width="50px">${item.id }</td>
									<td>${item.name }</td>
									<td>${item.url }</td>
									<td>${item.sort }</td>
									<td>${item.creatorName }</td>
									<td><fmt:formatDate value="${item.createTime }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
									<td>
										<input cmd="delete_menu" type="button" name="${item.id }" value="删除"/>
										<input cmd="update_menu" type="button" name="${item.id }" value="修改"/>
									</td>
								</tr>
							</c:forEach>
	            		</c:when>
	            		<c:otherwise>
	            			<tr>
								<td colspan="7">还没有菜单...</td>
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
