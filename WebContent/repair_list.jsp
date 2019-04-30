<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%
   // 获取session创建时间
   Date createTime = new Date(session.getCreationTime());
   // 获取最后访问页面的时间
   Date lastAccessTime = new Date(session.getLastAccessedTime());

   String title = "SessionTest";
   Integer visitCount = new Integer(0);
   String visitCountKey = new String("visitCount");
   String userIDKey = new String("userID");
   String userID = new String("ABCD");

   // 检测网页是否有新的访问用户
   if (session.isNew()){
      title = "NewSessionTest";
      session.setAttribute(userIDKey, userID);
      session.setAttribute(visitCountKey,  visitCount);
   } else {
       visitCount = (Integer)session.getAttribute(visitCountKey);
       visitCount += 1;
       userID = (String)session.getAttribute(userIDKey);
       session.setAttribute(visitCountKey,  visitCount);
   }
%>
<!DOCTYPE html>
<html lang="zxx">
<head>
<title>修</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
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
				<h1><a class="navbar-brand" href="index.html"><i class="fa fa-crosshairs" aria-hidden="true"></i> Treasurer</a></h1>

			</div>
			<ul class="agile_forms">
				<li><a class="active" href="#" data-toggle="modal" data-target="#myModal2">登录</a> </li>
				<li><a href="#" data-toggle="modal" data-target="#myModal3"> 注册</a> </li>
				<li><a href="#" data-toggle="modal" data-target="#myModal1">增加维修场次</a> </li>
			</ul>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
				<nav>
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle hvr-underline-from-center" data-toggle="dropdown">管理人员/场次<b class="fa fa-caret-down"></b></a>
							<ul class="dropdown-menu agile_short_dropdown">
								<li><a href="repair_list.html">维修场次管理</a></li>
								<li><a href="managers_list.html">维修人员管理</a></li>

							</ul>
						</li>
						<li><a href="contact.html" class="hvr-underline-from-center">Contact</a></li>
					</ul>
				</nav>

			</div>
		</nav>

		<div class="clearfix"> </div>
	</div>
<!-- Modal1 -->
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
																					<input type="text" name="phonenumber" placeholder="phonenumber" required="required">
																					<input type="password" name="password" placeholder="Password" required="required">
																					<div class="tp">
																						<input type="submit" value="Sign In">
																					</div>
																				</form>
																			</div>
																			<p><a href="#" data-toggle="modal" data-target="#myModal3" > Don't have an account?</a></p>
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
																			<div class="login-form">
																				<form action="#" method="post">
																				   <input type="text" name="name" placeholder="Username" required="">
																					<input type="email" name="email" placeholder="Email" required="">
																					<input type="password" name="password" id="password1" placeholder="Password" required="">
				                                                                    <input type="password" name="password" id="password2" placeholder="Confirm Password" required="">

																					<input type="submit" value="Sign Up">
																				</form>
																			</div>
																			<p><a href="#"> By clicking register, I agree to your terms</a></p>
																		</div>
																</div>
															</div>
														</div>
													</div>
													<!-- //Modal2 -->
													<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">增加维修场次</h3>	
																			<div class="login-form">
																				<form action="#" method="post">
																				   <input type="text" name="begin_time" placeholder="开始时间" required="required">
																					<input type="text" name="end_time" placeholder="结束时间" required="required">
				                                                                    <input type="text" name="place" placeholder="地点" required="required">
																					<input type="submit" value="确定">
																				</form>
																			</div>
																			
																		</div>
																</div>
															</div>
														</div>
													</div>
													<!-- //Modal1 -->
													<div class="modal fade" id="myModal4" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">修改维修场次</h3>	
																			<div class="login-form">
																				<form action="#" method="post">
																				   <input type="text" name="begin_time" placeholder="开始时间" required="required">
																					<input type="text" name="end_time" placeholder="结束时间" required="required">
				                                                                    <input type="text" name="place" placeholder="地点" required="required">
																					<input type="submit" value="确定">
																				</form>
																			</div>
																			
																		</div>
																</div>
															</div>
														</div>
													</div>

													<div class="modal fade" id="myModal5" tabindex="-1" role="dialog">
														<div class="modal-dialog">
														<!-- Modal content-->
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close" data-dismiss="modal">&times;</button>
																	
																	<div class="signin-form profile">
																	<h3 class="agileinfo_sign">是否删除该维修场次？</h3>	
																			<div class="login-form">
																				<form action="#" method="post">
																					<input type="submit" value="确定">
																				</form>
																			</div>
																			
																		</div>
																</div>
															</div>
														</div>
													</div>
					<!-- /agile_inner_banner_info -->													
							<div class="agile_inner_banner_info">
							  <h2>维修场次 </h2>
							</div>
					<!-- //agile_inner_banner_info -->
<!-- typo -->
	<div class="services">
		<div class="container">
			<h3 class="tittle_agile_w3">维修场次列表</h3>
			<div class="heading-underline">
				<div class="h-u1"></div><div class="h-u2"></div><div class="h-u3"></div><div class="clearfix"></div>
			</div>

			<div class="w3l-news_info_agile_its">
				<div class="media response-info">
						<div class="media-body response-text-right">
							<h5>生活一区青春广场</h5>
							<ul>
								<li><i class="fa fa-calendar" aria-hidden="true"></i>4月6日, 9:00-12:00</li>
								<li><i class="fa fa-users" aria-hidden="true"></i>Followers : 2546</li>
							</ul>
							<p>Lorem ipsum dolor sit amet, Lorem ipsum Lorem ipsum.</p>
							<div class="read">
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal4">修改</a>
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal5">删除</a>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>

					<div class="media response-info">
						<div class="media-body response-text-right">
							<h5>生活一区青春广场</h5>
							<ul>
								<li><i class="fa fa-calendar" aria-hidden="true"></i>4月6日, 9:00-12:00</li>
								<li><i class="fa fa-users" aria-hidden="true"></i>Followers : 2546</li>
							</ul>
							<p>Lorem ipsum dolor sit amet, Lorem ipsum Lorem ipsum.</p>
							<div class="read">
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal4">修改</a>
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal5">删除</a>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>

					<div class="media response-info" >
						
						<div class="media-body response-text-right">
							<h5>生活一区青春广场</h5>
							<ul>
								<li><i class="fa fa-calendar" aria-hidden="true"></i>4月6日, 9:00-12:00</li>
								<li><i class="fa fa-users" aria-hidden="true"></i>Followers : 7485</li>
							</ul>
							<p>Lorem ipsum dolor sit amet, Lorem ipsum Lorem ipsum.</p>
							<div class="read">
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal4">修改</a>
								<a href="#" class="view resw3" data-toggle="modal" data-target="#myModal5">删除</a>
							</div>
						</div>
						<div class="clearfix"> </div>
					</div>

				</div>
			</div>

	    </div>
	</div>
<!-- //typo -->	
<!-- footer -->
<!-- //footer -->
<!-- menu -->
<!-- js -->
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
	<!-- //password-script -->

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