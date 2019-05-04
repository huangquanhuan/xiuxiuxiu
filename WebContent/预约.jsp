<%--
    make-a-reservation-1.jsp
    用户端预约界面（第一步：确认个人信息）
    author: 刘忠燏
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<title>维修预约 - 第一步</title>

<script type="application/x-javascript">
	
      addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
      }, false);

      function hideURLbar() {
        window.scrollTo(0, 1);
      }
    
</script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/slicebox.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet" />
<!-- //font-awesome-icons -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css' />
<link
	href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i"
	rel="stylesheet" />
</head>
<body>

	<div class="agile_inner_banner_info">
		<h2>预约维修</h2>
	</div>
	<!-- 主体内容：开始 -->
	<div class="services">
		<div class="container">
			<h1>第一步：基本信息</h1>
			<p>个人基本信息必须与本人账户对应，不提供修改</p>
			<br />

			<form class="form-horizontal" action="*" method="post" role="form">
				<div class="form-group">
					<label class="col-sm-2 control-label" for="name">姓名</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" id="name" name="name"
							readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="studentId">学号</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" id="studentId"
							name="studentId" readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="contact">联系方式</label>
					<div class="col-sm-10">
						<input class="form-control" type="tel" id="contact" name="contact"
							readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="address">地址</label>
					<div class="col-sm-10">
						<input class="form-control" type="text" id="address"
							name="contact" readonly />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="预约2.jsp" class="btn btn-success">下一步</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 主体内容：结束 -->
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
							<form class="loginForm" action="UserServlet?type=login"
								method="post">
								<input id="name" name="name" placeholder="手机/会员名/邮箱" type="text"
									required="required"> <input id="password"
									type="password" name="password" placeholder="密码"
									required="required">
								<div class="tp">
									<input type="submit" value="登录">
								</div>
							</form>
						</div>
						<p>
							<a href="#" data-toggle="modal" data-target="#myModal3">
								还没有账号?</a>
						</p>
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
							<form class="registerForm" action="UserServlet?type=register"
								method="post">
								<input id="name" type="text" name="name" placeholder="昵称"
									required="required"> <input id="phonenumber"
									type="text" name="tel" placeholder="手机号码" required="required">
								<input id="password" type="password" name="password"
									placeholder="密码" required="required"> <input
									type="password" name="password" id="password2"
									placeholder="确认密码" required="required"> <input
									type="submit" value="注册">
							</form>
						</div>
						<p>
							<a href="#"> 已有账号？</a>
						</p>
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
	<!-- js -->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
	<!-- password-script -->
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("password1").onchange = validatePassword;
			document.getElementById("password2").onchange = validatePassword;
		}

		function validatePassword() {
			var pass2 = document.getElementById("password2").value;
			var pass1 = document.getElementById("password1").value;
			if (pass1 != pass2)
				document.getElementById("password2").setCustomValidity(
						"Passwords Don't Match");
			else
				document.getElementById("password2").setCustomValidity('');
			//empty string means no validation error
		}
	</script>
	<!-- //password-script -->

	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>
	<!-- //js -->
	<!-- password-script -->
	<script type="text/javascript">
		window.onload = function() {
			document.getElementById("password1").onchange = validatePassword;
			document.getElementById("password2").onchange = validatePassword;
		}

		function validatePassword() {
			var pass2 = document.getElementById("password2").value;
			var pass1 = document.getElementById("password1").value;
			if (pass1 != pass2)
				document.getElementById("password2").setCustomValidity(
						"Passwords Don't Match");
			else
				document.getElementById("password2").setCustomValidity('');
			//empty string means no validation error
		}
		//管理员删除时弹出警告
		function showDelMessage() {

		}
	</script>

	<!-- 由session判断是否登录成功 -->
	<%
		if (session.getAttribute("name") == null) {
	%>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$("#exit").toggle(function() {
				$(this).hide();
			});
		});
	</script>
	<%
		} else {
	%>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$("#login").toggle(function() {
				$(this).hide();
			});
			$("#register").toggle(function() {
				$(this).hide();
			});
		});
	</script>
	<%
		}
	%>

	<!-- for bootstrap working -->
	<script src="js/bootstrap.js"></script>
	<!-- //for bootstrap working -->
	<!-- start-smoth-scrolling -->
	<script type="text/javascript" src="js/move-top.js"></script>
	<script type="text/javascript" src="js/easing.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			$(".scroll").click(function(event) {
				event.preventDefault();
				$('html,body').animate({
					scrollTop : $(this.hash).offset().top
				}, 1000);
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

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //here ends scrolling icon -->
</body>
</html>