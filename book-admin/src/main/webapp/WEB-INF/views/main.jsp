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
        <c:catch var="exception">
	  	<%@ include file="common/nav_sidebar.jsp" %>
	 	</c:catch> 
	 	
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h2 class="sub-header">欢迎 [${admin_user.name}] 使用本系统 !</h2>
          <ul>
          	<li style="margin:10px;"><a href="http://www.shiyueai.cn" target="blank"/>十月爱四维摄影官网</li>
          	<li style="margin:10px;"><a href="http://bj.meituan.com/s/?w=%E5%8D%81%E6%9C%88%E7%88%B1&mtt=1.index%2Ffloornew.0.0.j72xw3ib" target="blank"/>十月爱美团团购链接</li>
          	<li style="margin:10px;"><a href="http://www.dianping.com/search/keyword/2/0_%E5%8D%81%E6%9C%88%E7%88%B1" target="blank"/>十月爱大众点评团购链接</li>
          	<li style="margin:10px;"><a href="https://www.nuomi.com/search?k=%E5%8D%81%E6%9C%88%E7%88%B1" target="blank"/>十月爱糯米团购链接</li>
          	<hr/>
          	<li style="margin:10px;"><a href="http://e.dianping.com/slogin" target="blank"/>美团点评商户中心</li>
          	<li style="margin:10px;"><a href="https://mct.y.nuomi.com/login" target="blank"/>糯米网商户中心</li>
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