	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="col-sm-3 col-md-2 sidebar">
  		<ul class="nav nav-sidebar">
    		<li><a id="a_0" href="/index" <c:if test="${'/index' == uri }">style="color:#fff;background-color:#428bca;"</c:if>>首页</a></li>
    		<c:forEach items="${menus}" var="item">
    			<li><a id="a_${item.id}" href="${item.url}" <c:if test="${item.url == uri }">style="color:#fff;background-color:#428bca;"</c:if> >${item.name}</a></li>
    		</c:forEach>
  		</ul>
  
  		<c:if test="${isAdmin == 1 }">
	  		<hr style="width: 100%;margin: 0px;padding: 0px;size: 2px"/>
	  		<ul class="nav nav-sidebar">
	    		<li><a id="a_100" href="/admin/users" <c:if test="${'/admin/users' == uri }">style="color:#fff;background-color:#428bca;"</c:if>>权限管理</a></li>
	    		<li><a id="a_101" href="/admin/menus" <c:if test="${'/admin/menus' == uri }">style="color:#fff;background-color:#428bca;"</c:if>>菜单管理</a></li>
	  		</ul>
  		</c:if>
  		
 		<hr style="width: 100%;margin: 0px;padding: 0px;size: 2px"/>
  		<ul class="nav nav-sidebar">
    		<li><a id="a_102" href="/admin/myinfo" <c:if test="${'/admin/myinfo' == uri }">style="color:#fff;background-color:#428bca;"</c:if> >我的信息</a></li>
  		</ul>
  		
	</div>
