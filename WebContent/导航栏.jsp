<%@ page language="java" contentType="text/html;charset=UTF-8"
  pageEncoding="UTF-8" isELIgnored="false"%>
    
<%@page import="xiuxiuxiu.pojo.Student"%>

<!-- 导航栏 -->
<nav class="navbar navbar-default">
	<div class="navbar-header navbar-left">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<h1>
			<a class="navbar-brand" href="index.html"><i
				class="fa fa-crosshairs" aria-hidden="true"></i> 修咻咻维修平台</a>
		</h1>

	</div>
	<ul class="agile_forms">
		<li><a class="active" href="#" data-toggle="modal"
			data-target="#myModal2" id="login">登录</a></li>
		<li><a href="#" data-toggle="modal" data-target="#myModal3"
			id="register"> 注册</a></li>
		<li><a href="#" data-toggle="modal" data-target="#myModal6"
			id="exit">退出登录</a></li>
	</ul>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse navbar-right"
		id="bs-example-navbar-collapse-1">
		<nav>
			<ul class="nav navbar-nav">
				<li><a href="HomePageServlet" class="hvr-underline-from-center">首页</a></li>
				<li><a href="预约.jsp" id="appaly" class="hvr-underline-from-center">预约</a></li>
				<li><a href="ArticleServlet?type=list"
					class="hvr-underline-from-center">文章&通知</a></li>
				<li class="dropdown"><a id="my_message" href="#"
					class="dropdown-toggle hvr-underline-from-center"
					data-toggle="dropdown">我的<b class="fa fa-caret-down"></b></a>
					<ul class="dropdown-menu agile_short_dropdown">
						<%
							Student user = (Student) session.getAttribute("name");
							String name = "null", tel = "null", address = "null", studentID = "null", email = "null";
			
							if (session.getAttribute("name") != null) {
								name = user.getName();
								tel = user.getPhoneNumber();
								address = user.getAddress();
								studentID = user.getStudentID();
								email = user.getEmail();
							}
						%>
						<li><p>姓名:<%=name%></p></li>
						<li><p>学号:<%=studentID%></p></li>
						<li><p>手机号:<%=tel%></p></li>
						<li><p>住址:<%=address%></p></li>
						<li><p>邮箱:<%=email%></p></li>
						<li><a></a></li>
						<li><a class="active" data-toggle="modal"
							data-target="#changeinfo-data" href="#" id="update">修改个人信息</a></li>
						<li><a class="active" data-toggle="modal"
							data-target="#myDevice-data" href="#" id="device">我的设备</a></li>
						<li><a class="active" href="MyReservationManageServlet"
							id="appointment">预约管理</a></li>
					</ul></li>
			</ul>
		</nav>
	</div>




</nav>