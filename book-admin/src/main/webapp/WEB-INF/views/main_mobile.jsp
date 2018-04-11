<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common/base.jsp" %>
<html>
<head>
	<%@ include file="common/header.jsp" %>
</head>
<body>
 <%@ include file="common/nav_mobile.jsp" %>
 
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">欢迎 [${admin_user.name}]使用本系统 !</h2>
        <ul>
    		<c:forEach items="${menus}" var="item">
    			<li style="margin:15px;"><a href="${item.url}">${item.name}</a></li>
    		</c:forEach>
    		<c:if test="${isAdmin == 1 }">
	  			<hr style="width: 100%;margin: 0px;padding: 0px;size: 2px"/>
	    		<li style="margin:15px;"><a href="/admin/users">权限管理</a></li>
	    		<li style="margin:15px;"><a href="/admin/menus">菜单管理</a></li>
  			</c:if>
  			<li style="margin:15px;"><a href="/admin/myinfo">我的信息</a></li>
  		</ul>
          <div class="table-responsive">
            <table class="table table-striped">
              <tbody>
                <tr>
                  <td>
                  	<c:if test="${fn:length(menus) <= 0 }">
                  		你还没有分配权限，请联系超管....
                  	</c:if>
                  	<h1><c:if test="${error != null && '' != error }">${error }</c:if></h1>
                  	<img alt="logo" src="../../static/images/logo.png">
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    
     <c:catch var="exception">
	  	<%@ include file="common/footer.jsp" %>
	 </c:catch>
</body>
</html>