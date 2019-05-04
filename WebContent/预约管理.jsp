<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<body>
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
	<h2>已提交的预约</h2>
</div>
	<div class="container text-center">

		<!-- Top content -->
		<div class="top-content">
			<div class="container">
				<div class="row">
					
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">2019-03-29(未受理)</h3>
								<div>
									<button type="button" class="btn btn-primary">编辑</button>
									<button type="button" class="btn btn-danger">撤销</button>
								</div>
							</div>
							<div class="panel-body">
								<p>张三 2019-03-32( 20:00~22:00 )32#6楼活动室</p>
								<label>联想拯救者 Y700 </label> <label>更换硬盘并且...</label>
							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">2019-03-29(已受理，未完成)</h3>
								<div>
									<button type="button" class="btn btn-danger">联系</button>
								</div>
							</div>
							<div class="panel-body">
								<p>王五 2019-03-13(19:00~21:00) 6#407宿舍</p>
								<label>联想拯救者 Y700</label> <label> 清灰</label>
							</div>
						</div>


						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">2019-03-29(已完成)</h3>
								<div>
									<button class="btn btn-success" data-toggle="modal"
										data-target="#evaluation-data" type="button">评价</button>
									<button class="btn btn-danger" data-toggle="modal"
										data-target="#feedback-data" type="button">反馈</button>
								</div>
							</div>
							<div class="panel-body">
								<p>李四 2019-02-12( 14:00~17:00 )青春广场</p>
								<label>微软 Surface Pro </label> <label> 显示屏出问题了...</label>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- data-target触发模态弹出窗元素 -->
	<button class="btn btn-primary" data-toggle="modal"
		data-target="#mymodal-data" type="button">通过data-target触发</button>


	<!-- 评价弹出窗内容 -->
	<div class="modal" id="evaluation-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form role="form" action="..." method="post" class="registration-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">评价</h4>
					</div>
					<div class="modal-body">
						<textarea class="form-control" rows="3" placeholder="我觉得本次维修..."></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-primary">保存</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 反馈弹出窗内容 -->
	<div class="modal" id="feedback-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form role="form" action="..." method="post" class="registration-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">反馈问题</h4>
					</div>
					<div class="modal-body">
						<textarea class="form-control" rows="3" placeholder="我觉得本次维修..."></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-danger">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>

</html>