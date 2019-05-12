
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.io.*,java.util.*"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="zxx">

<head>
<title>修咻咻维修平台</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
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
<link href="css/slicebox.css" rel="stylesheet" type="text/css">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome-icons -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link
	href="http://fonts.googleapis.com/css?family=Raleway:100i,200,200i,300,400,500,500i,600,700,700i,800,800i"
	rel="stylesheet">


</head>

<body>
	<%@include file="导航栏.jsp"%>

	<div class="banner">

		<div class="wrapper">

			<ul id="sb-slider" class="sb-slider">
				<li><a href="#"><img src="images/banner1.jpg" alt="image1" /></a>
					<div class="sb-description">
						<h3>上门维修，在线预约</h3>
					</div></li>
				<li><a href="#"><img src="images/banner4.jpg" alt="image1" /></a>
					<div class="sb-description">
						<h3>服务同学，建设校园</h3>
					</div></li>

			</ul>

			<div id="shadow" class="shadow"></div>

			<div id="nav-arrows" class="nav-arrows">
				<a href="#">Next</a> <a href="#">Previous</a>
			</div>

			<div id="nav-dots" class="nav-dots">
				<span class="nav-dot-current"></span> <span></span> <span></span> <span></span>

			</div>
		</div>
		<!-- /wrapper -->
	</div>
	<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="mid_agile_bannner_top_info">
				<h2>维修活动展示</h2>
				<div class="heading-underline">
					<div class="h-u1"></div>
					<div class="h-u2"></div>
					<div class="h-u3"></div>
					<div class="clearfix"></div>
				</div>
			</div>
			<!--详细的维修活动 -->
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<td><strong>序号</strong></td>
							<td><strong>活动时间</strong></td>
							<td><strong>举办地点</strong></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${repairActivityList}" var="repairActivity"
							varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${repairActivity.time}</td>
								<td>${repairActivity.place}</td>
<!-- 								<td> -->
<!-- 									<form  id="applythis" class="ApplyActivity" -->
<!-- 										action="makeReservation?method=beforeReserve" -->
<!-- 										method="post"> -->
<!-- 										<button  type="submit" class="">预约此活动</button> -->
<!-- 									</form> -->
<!-- 								</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<a href="HomePageServlet"><button type="submit" class="">刷新</button></a>
					<a href="#"><button type="submit" class="">更多</button></a>
				</div>
				<br>
			</div>
			<div class="col-md-6 agileits_banner_bottom_left">
				<h3>
					<span>修咻咻</span>维修平台介绍
				</h3>
				<p class="w3l_para">这是为福大计算机协会维修活动的公告和接受维修预约的一个网站</p>
				<div class="w3l_social_icons">
					<div class="w3l_social_icon_grid w3ls_social_icon_grid">
						<div class="w3l_social_icon_gridl w3_dribbble">
							<a href="#"> <i class="fa fa-dribbble" aria-hidden="true"></i>
							</a>
						</div>
						<div class="w3l_social_icon_gridr">
							<p>服务人次超过</p>
							<p class="counter">332</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="w3l_social_icon_grid">
						<div class="w3l_social_icon_gridl w3_instagram">
							<a href="#"> <i class="fa fa-instagram" aria-hidden="true"></i>
							</a>
						</div>
						<div class="w3l_social_icon_gridr">
							<p>维修设备数多于</p>
							<p class="counter">342</p>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6 agileits_banner_bottom_right">
				<div class="w3ls_banner_bottom_right">
					<img src="images/2.jpg" alt=" " class="img-responsive" />
					<div class="w3ls_banner_bottom_right_pos">
						<img src="images/1.jpg" alt=" " class="img-responsive" />
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!-- //banner-bottom -->
	<!-- //banner-bottom-icons -->
	<!-- Modal1 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>Treasurer</h4>
					<img src="images/banner2.jpg" alt="blog-image" /> <span>Lorem
						ipsum dolor sit amet, Sed ut perspiciatis unde omnis iste natus
						error sit voluptatem , eaque ipsa quae ab illo inventore veritatis
						et quasi architecto beatae vitae dicta sunt explicabo.accusantium
						doloremque laudantium, totam rem aperiamconsectetur adipiscing
						elit, sed do eiusmod tempor incididunt ut labore et dolore magna
						aliqua.</span>
				</div>
			</div>
		</div>
	</div>
	<!-- //Modal1 -->
	<!-- //News -->
	<!-- events-top -->
	<!-- //events-top -->


	<!-- bootstrap-pop-up -->
	<div class="modal video-modal fade" id="myModal" tabindex="-1"
		role="dialog" aria-labelledby="myModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					Treasurer
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<section>
					<div class="modal-body">
						<div class="col-md-6 w3_modal_body_left">
							<img src="images/2.jpg" alt=" " class="img-responsive" />
						</div>
						<div class="col-md-6 w3_modal_body_right">
							<p>
								Ut enim ad minima veniam, quis nostrum exercitationem ullam
								corporis suscipit laboriosam, nisi ut aliquid ex ea commodi
								consequatur? Quis autem vel eum iure reprehenderit qui in ea
								voluptate velit esse quam nihil molestiae consequatur. <i>"
									Quis autem vel eum iure reprehenderit qui in ea voluptate velit
									esse quam nihil molestiae consequatur.</i>
							</p>
						</div>
						<div class="clearfix"></div>
					</div>
				</section>
			</div>
		</div>
	</div>
	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
	<%@include file="错误提示窗口.jsp"%>


</body>
</html>