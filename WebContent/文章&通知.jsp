<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="xiuxiuxiu.dao.*"%>
<%@page import="xiuxiuxiu.pojo.Article"%>
<%@ page import="java.io.*,java.util.*" %>
<%@page import="xiuxiuxiu.servelt.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
	<title>修咻咻维修平台</title>
	<!-- custom-theme -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/jsp; charset=utf-8" />
<meta name="keywords" content="" />
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/blog.css" rel="stylesheet" type="text/css" media="all" />

<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome-icons -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
<link href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i" rel="stylesheet">

</head>
<body>
	<!-- banner -->
	<div class="agileits_w3layouts_banner_nav">
		<nav class="navbar navbar-default">
			<div class="navbar-header navbar-left">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				<h1><a class="navbar-brand" href="index.html"><i class="fa fa-crosshairs" aria-hidden="true"></i> 修咻咻维修平台</a></h1>

			</div>
			<ul class="agile_forms">
				<li><a class="active" href="#" data-toggle="modal" data-target="#myModal2" id="login" >登录</a> </li>
				<li><a href="#" data-toggle="modal" data-target="#myModal3" id="register" > 注册</a> </li>
				<li><a href="#" data-toggle="modal" data-target="#myModal6" id="exit" >退出登录</a></li>
			</ul>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
				<nav>
					<ul class="nav navbar-nav">
						<li><a href="首页.jsp" class="hvr-underline-from-center">首页</a></li>
						<li><a href="预约.jsp" class="hvr-underline-from-center">预约</a></li>			
						<li><a href="ArticleServlet?type=list" class="hvr-underline-from-center">文章&通知</a></li>
						<li class="dropdown">
            			<a href="#" class="dropdown-toggle hvr-underline-from-center" data-toggle="dropdown">我的<b class="fa fa-caret-down"></b></a>
            			<ul class="dropdown-menu agile_short_dropdown">
             			<li><a href="icons.html">姓名</a></li>
              			<li><a href="icons.html">学号</a></li>
              			<li><a href="icons.html">手机号</a></li>
              			<li><a href="icons.html">邮箱</a></li>
              			<li><a href="icons.html">地址</a></li>
              			<li><a class="active" data-toggle="modal" data-target="#changeinfo-data" href="#"  id="update" >修改个人信息</a></li>
		                <li><a class="active" href="预约管理.jsp"  id="appointment" >预约管理</a></li>
           				</ul>
         				</li>
					</ul>
				</nav>

			</div>
		</nav>

		<div class="clearfix"> </div>
	</div>
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
	<div class="modal-dialog">
	<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>													
				<div class="signin-form profile">
				<h3 class="agileinfo_sign">登录</h3>	
						<div class="login-form">
							<form class="loginForm" action="UserServlet?type=login" method="post">
								<input id="name" name="name" placeholder="手机/会员名/邮箱" type="text"required="required">								
																					<input id="password" type="password" name="password" placeholder="密码" required="required">
																					<div class="tp">
																						<input type="submit" value="登录">
																					</div>
																				</form>
																			</div>
																			<p><a href="#" data-toggle="modal" data-target="#myModal3" > 还没有账号?</a></p>
																		</div>
																</div>
															</div>
														</div>
													</div>
													<!-- //Modal1 -->	
													<!-- Modal2 -->
													<div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">注册</h3>	
																			<div class="register-form">
																				<form class="registerForm" action="UserServlet?type=register" method="post">
																				   <input id="name"type="text" name="name" placeholder="昵称" required="required">
																					<input id="phonenumber"type="text" name="tel" placeholder="手机号码" required="required">
																					<input id="password"type="password" name="password" placeholder="密码" required="required">
				                                                                    <input type="password" name="password" id="password2" placeholder="确认密码" required="required">

																					<input type="submit" value="注册">
																				</form>
																			</div>
																			<p><a href="#"> 已有账号？</a></p>
																		</div>
																</div>
															</div>
														</div>
													</div>
													<div class="modal fade" id="myModal6" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">是否退出登录？</h3>	
																			<div class="login-form">
																				<form action="UserServlet?type=exit" method="post">
																					<input type="submit" value="确定">
																				</form>
																			</div>
																			
																		</div>
																</div>
															</div>
														</div>
													</div>
	<!-- //Modal2 -->
	<div class="agile_inner_banner_info">
	<h2>文章列表</h2>
</div>
		<div class="services">
		<div class="container">
			<div class="col-md-8 event-left w3-agile-event-left">
				<c:forEach items="${articles}" var="article">
				<div class="event-left1 w3-agile-event-left1">
					<div class="col-xs-6 event-left1-left agile-event-left1-left">
						<a href=Single.jsp><img src="images/7.jpg" alt=" " class="img-responsive" /></a>
						<div class="event-left1-left-pos agileits-w3layouts-event-left1-left-pos">
							<ul>
								<li><a href="#"><span class="fa fa-tags" aria-hidden="true"></span>5 Tags</a></li>
								<li><a href="#"><span class="fa fa-user" aria-hidden="true"></span>${article.authorName}</a></li>
							</ul>
						</div>
					</div>
					<div class="col-xs-6 event-left1-right w3-agileits-event-left1-right">
						<h4>${article.time}</h4>
						<h5><a href="ArticleServlet?type=single&id=${article.id}">${article.title}</a></h5>
						<p>${article.authorName}</p>
					</div>
					<div class="clearfix"> </div>
				</div>
				</c:forEach>
				<nav class="paging1 agileits-w3layouts-paging1">
				  <ul class="pagination paging w3-agileits-paging">
					<li>
					  <a href="#" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					  </a>
					</li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li>
					  <a href="#" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					  </a>
					</li>
				  </ul>
				</nav>
			</div>
			<div class="col-md-4 event-right wthree-event-right">
				<div class="event-right1 agileinfo-event-right1">
					<div class="search1 agileits-search1">
						<form action="#" method="post">
							<input type="search" name="Search" placeholder="搜索文章/通知" required="">
							<input type="submit" value="检索">
						</form>
					</div>
						<div class="posts w3l-posts">
						<h3>通知</h3>
						<div class="posts-grids w3-posts-grids">
							<div class="posts-grid w3-posts-grid">
								<div class="posts-grid-left w3-posts-grid-left">
									<a href="single.html"><img src="images/1.jpg" alt=" " class="img-responsive" /></a>
								</div>
								<div class="posts-grid-right w3-posts-grid-right">
									<h4><a href="single.html">维修场次变更通知</a></h4>
									<ul class="wthree_blog_events_list">
										<li><i class="fa fa-calendar" aria-hidden="true"></i>10/5/2017</li>
										<li><i class="fa fa-user" aria-hidden="true"></i><a href="single.html">管理员</a></li>
									</ul>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="posts-grid w3-posts-grid">
								<div class="posts-grid-left w3-posts-grid-left">
									<a href="single.html"><img src="images/6.jpg" alt=" " class="img-responsive" /></a>
								</div>
								<div class="posts-grid-right w3-posts-grid-right">
									<h4><a href="single.html">维修场地变更通知</a></h4>
									<ul class="wthree_blog_events_list">
										<li><i class="fa fa-calendar" aria-hidden="true"></i>12/5/2017</li>
										<li><i class="fa fa-user" aria-hidden="true"></i><a href="single.html">管理员</a></li>
									</ul>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="posts-grid w3-posts-grid">
								<div class="posts-grid-left w3-posts-grid-left">
									<a href="single.html"><img src="images/7.jpg" alt=" " class="img-responsive" /></a>
								</div>
								<div class="posts-grid-right w3-posts-grid-right">
									<h4><a href="single.html">零件库更新通知</a></h4>
									<ul class="wthree_blog_events_list">
										<li><i class="fa fa-calendar" aria-hidden="true"></i>13/5/2017</li>
										<li><i class="fa fa-user" aria-hidden="true"></i><a href="single.html">管理员</a></li>
									</ul>
								</div>
								<div class="clearfix"> </div>
							</div>
						</div>
					</div>
					<div class="tags tags1 w3layouts-tags">
						<h3>关键词检索</h3>
						<ul>
							<li><a href="single.html">网络故障</a></li>
							<li><a href="single.html">无法开机</a></li>
							<li><a href="single.html">蓝屏</a></li>
							<li><a href="single.html">自动重启</a></li>
							<li><a href="single.html">病毒常识</a></li>
							<li><a href="single.html">系统变慢</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
<!-- //js -->
	<!-- password-script -->
	<script type="text/javascript">
		window.onload = function () {
			document.getElementById("password1").onchange = validatePassword;
			document.getElementById("password2").onchange = validatePassword;
		}

		function validatePassword() {
			var pass2 = document.getElementById("password2").value;
			var pass1 = document.getElementById("password1").value;
			if (pass1 != pass2)
				document.getElementById("password2").setCustomValidity("Passwords Don't Match");
			else
				document.getElementById("password2").setCustomValidity('');
			//empty string means no validation error
		}
		//管理员删除时弹出警告
		function showDelMessage(){

		}
	</script>
	
<!-- 由session判断是否登录成功 -->
<%if (session.getAttribute("name")==null){%>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("#exit").toggle( 
				  function () {
				    $(this).hide();
				  }
				);
	});
</script>
  <%}else {%>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$("#login").toggle( 
				  function () {
				    $(this).hide();
				  }
				);
		$("#register").toggle( 
				  function () {
				    $(this).hide();
				  }
				);
	});
</script>
  <%}%>
  
<!-- for bootstrap working -->
	<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
								
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
<!-- //here ends scrolling icon -->
</body>
</html>