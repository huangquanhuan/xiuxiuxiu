<!doctype html>
<%@page import="xiuxiuxiu.pojo.RepairActivity"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<!-- Required meta tags -->

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>首页</title>
</head>
<body>
	<div class="container text-center">

		<div class="header"
			style="background-image: url(http://assets.youzhan.org/img/d/dd/2de797545de56274f03a5920eb3a1.jpg)">
			<div class="row">
				<div class="col-xs-12">
					<div class="logotxt">
						<br> <br>
						<h1>
							<a href="#">维修活动场次</a>
						</h1>
						<br> <br>
					</div>
				</div>
			</div>
		</div>
		<div>
			<a href="HomePageServlet"><button type="button"
					class="btn btn-info">刷新</button></a>
		</div>
		<div class="table-responsive">
			<table class="table">
				<caption>最近的线下免费维修活动</caption>
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
							<td>
								<form class="ApplyActivity"
									action="<!-- 这边记得改 ...-->ApplyServlet?type=ApplyActivity&id=${student.id}&password=${student.password}"
									method="post">
									<button type="submit" class="">预约此活动</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>

				<a href="#"><button type="button" class="btn btn-info">更多...</button></a>
			</div>
			<br>
		</div>

		<div class="row">
			<div class="col-xs-12">
				<h4>修咻咻 维修平台</h4>
				<h4>欢迎您的使用！</h4>
			</div>
		</div>

	</div>


</body>
</html>