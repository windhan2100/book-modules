	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- nav -->
    <div class="navbar navbar-default navbar-fixed-top"  role="navigation">
      <div class="container-fluid">
       <nav class="nav">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">展开导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button> 
          <a href="/">
          	<b class="navbar-brand logo"></b>
          	<span class="navbar-title">十月爱四维摄影管理系统</span>
          </a>
        </div>        
	        <div class="navbar-collapse collapse">
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#">欢迎：${admin_user.name}</a></li>
	            <li><a href="/login/out">退出</a></li>
	          </ul>
	          <form class="navbar-form navbar-right">
	            <input type="text" class="form-control" placeholder="Search...">
	          </form>
	        </div>
        </nav>
      </div>
    </div>
 