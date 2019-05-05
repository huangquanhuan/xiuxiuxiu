<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="xiuxiuxiu.dao.*"%>
<%@page import="xiuxiuxiu.pojo.Article"%>
<%@ page import="java.io.*,java.util.*"%>
<%@page import="xiuxiuxiu.servelt.*"%>
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
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/blog.css" rel="stylesheet" type="text/css" media="all" />

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

	<div class="agile_inner_banner_info">
		<h2>文章列表</h2>
	</div>
	<div class="services">
		<div class="container">
			<div class="col-md-8 event-left w3-agile-event-left">
				<c:forEach items="${articles}" var="article">
					<div class="event-left1 w3-agile-event-left1">
						<div class="col-xs-6 event-left1-left agile-event-left1-left">
							<a href=Single.jsp><img src="images/7.jpg" alt=" "
								class="img-responsive" /></a>
							<div
								class="event-left1-left-pos agileits-w3layouts-event-left1-left-pos">
								<ul>
									<li><a href="#"><span class="fa fa-tags"
											aria-hidden="true"></span>5 Tags</a></li>
									<li><a href="#"><span class="fa fa-user"
											aria-hidden="true"></span>${article.authorName}</a></li>
								</ul>
							</div>
						</div>
						<div
							class="col-xs-6 event-left1-right w3-agileits-event-left1-right">
							<h4>${article.time}</h4>
							<h5>
								<a href=文章详情页.jsp>${article.title}</a>
							</h5>
							<p>${article.authorName}</p>
						</div>
						<div class="clearfix"></div>
					</div>
					<div
						class="col-xs-6 event-left1-right w3-agileits-event-left1-right">
						<h4>${article.time}</h4>
						<h5>
							<a href="ArticleServlet?type=single&id=${article.id}">${article.title}</a>
						</h5>
						<p>${article.authorName}</p>
					</div>
					<div class="clearfix"></div>
				</c:forEach>
			</div>


		</div>
		<div class="col-md-4 event-right wthree-event-right">
			<div class="event-right1 agileinfo-event-right1">
				<div class="search1 agileits-search1">
					<form action="#" method="post">
						<input type="search" name="Search" placeholder="搜索文章/通知"
							required=""> <input type="submit" value="检索">
					</form>
				</div>
				<div class="posts w3l-posts">
					<h3>通知</h3>
					<div class="posts-grids w3-posts-grids">
						<div class="posts-grid w3-posts-grid">
							<div class="posts-grid-left w3-posts-grid-left">
								<a href="single.html"><img src="images/1.jpg" alt=" "
									class="img-responsive" /></a>
							</div>
							<div class="posts-grid-right w3-posts-grid-right">
								<h4>
									<a href="single.html">维修场次变更通知</a>
								</h4>
								<ul class="wthree_blog_events_list">
									<li><i class="fa fa-calendar" aria-hidden="true"></i>10/5/2017</li>
									<li><i class="fa fa-user" aria-hidden="true"></i><a
										href="single.html">管理员</a></li>
								</ul>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="posts-grid w3-posts-grid">
							<div class="posts-grid-left w3-posts-grid-left">
								<a href="single.html"><img src="images/6.jpg" alt=" "
									class="img-responsive" /></a>
							</div>
							<div class="posts-grid-right w3-posts-grid-right">
								<h4>
									<a href="single.html">维修场地变更通知</a>
								</h4>
								<ul class="wthree_blog_events_list">
									<li><i class="fa fa-calendar" aria-hidden="true"></i>12/5/2017</li>
									<li><i class="fa fa-user" aria-hidden="true"></i><a
										href="single.html">管理员</a></li>
								</ul>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="posts-grid w3-posts-grid">
							<div class="posts-grid-left w3-posts-grid-left">
								<a href="single.html"><img src="images/7.jpg" alt=" "
									class="img-responsive" /></a>
							</div>
							<div class="posts-grid-right w3-posts-grid-right">
								<h4>
									<a href="single.html">零件库更新通知</a>
								</h4>
								<ul class="wthree_blog_events_list">
									<li><i class="fa fa-calendar" aria-hidden="true"></i>13/5/2017</li>
									<li><i class="fa fa-user" aria-hidden="true"></i><a
										href="single.html">管理员</a></li>
								</ul>
							</div>
							<div class="clearfix"></div>
						</div>
					</div>
				</div>
				<nav class="paging1 agileits-w3layouts-paging1">
					<ul class="pagination paging w3-agileits-paging">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
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
		<div class="clearfix"></div>
	</div>

	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
</body>
</html>