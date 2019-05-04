<!DOCTYPE html>
<%@page import="javax.security.auth.message.callback.PrivateKeyCallback.Request"%>
<%@page import="xiuxiuxiu.pojo.*"%>
<%@page import="xiuxiuxiu.dao.*"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<title>预约管理页</title>

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
							<a href="http://www.youzhan.org">预约管理页</a>
						</h1>
						<br> <br>
					</div>
				</div>
			</div>
		</div>
		<div>
			<button type="button" class="btn btn-default  pull-left">
				<span class="glyphicon glyphicon-chevron-left"></span> 返回我的信息
			</button>
			<br /> <br />
		</div>

		<!-- Top content -->
		<div class="top-content">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>已提交的预约</strong>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">

						<%
						    List<Reservation> reservationList = (List<Reservation>) request.getAttribute("ReservationList");
						    for (Reservation reservation : reservationList) {
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<%!String applicationTimeAndState, requiredTimeAndPlace, detail;%>
								<%!EquipmentDAO equipmentDao = new EquipmentDAOImpl();%>
								<%
								    applicationTimeAndState = reservation.getApplicationTime();
								        requiredTimeAndPlace = reservation.getRequiredTime();

								        if (reservation.getStateInt() == 0) {
								            applicationTimeAndState = applicationTimeAndState + "(未受理)";
								        } else if (reservation.getStateInt() == 1) {
								            applicationTimeAndState = applicationTimeAndState + "(已受理，未完成)";
								        } else if (reservation.getStateInt() == 2) {
								            applicationTimeAndState = applicationTimeAndState + "(已完成)";
								        } else {
								            out.print("<script>alert('获取预约单的目前状态失败！')</script>");
								        }
								        requiredTimeAndPlace = reservation.getRequiredTime() + " " + reservation.getPlace();
								        detail = reservation.getDetail();
								        if (detail != null && detail.length() > 8)
								            detail = detail.substring(0, 7) + "...";
								%>
								<h3 class="panel-title"><%=applicationTimeAndState%></h3>
								<div>
									<button type="button" class="btn btn-primary">编辑</button>
									<button type="button" class="btn btn-danger">撤销</button>

									<button type="button" class="btn btn-danger">联系</button>
									<button class="btn btn-success" data-toggle="modal"
										data-target="#evaluation-data" type="button">评价</button>
									<button class="btn btn-danger" data-toggle="modal"
										data-target="#feedback-data" type="button">反馈</button>

								</div>
							</div>
							<div class="panel-body">
								<p><%=requiredTimeAndPlace%></p>
								<label><%=equipmentDao.getEquipmentName(reservation.getEquipmentID())%></label>
								<label><%=detail%></label>
							</div>
						</div>
						<%
						    }
						%>
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
				<form role="form" action="..." method="post"
					class="registration-form">
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
				<form role="form" action="..." method="post"
					class="registration-form">
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