<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="xiuxiuxiu.pojo.*"%>
<%@page import="xiuxiuxiu.dao.*"%>
<%@page import="java.util.List"%>


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
	
<!-- 引入bootstrap的css -->
<link
	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
	
</head>

<body>
	<%@include file="导航栏.jsp"%>

	<div class="agile_inner_banner_info">
		<h2>已提交的预约</h2>
	</div>
	<div class="container text-center">

		<!-- Top content -->
		<div class="top-content">
			<div class="container">
				<div class="row"></div>
				<%
					int targetID = -1;
					List<Reservation> reservationList = (List<Reservation>) request.getAttribute("ReservationList");
					for (Reservation reservation : reservationList) {
				%>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
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
										detail = "详情:" + reservation.getDetail();
										if (detail != null && detail.length() > 8)
											detail = detail.substring(0, 7) + "...";
								%>
								<h3 class="panel-title"><%=applicationTimeAndState%></h3>
								<div>
									<a
										href="MyReservationManageServlet?method=edit&targetID=${viewComponents.reservationID}"
										class="btn btn-primary">编辑</a>
									<button class="btn btn-danger"
										onclick="setTargetID(<%=reservation.getID()%>)"
										data-toggle="modal" data-target="#cancel-data" type="button">撤销</button>
									<button class="btn btn-danger"
										onclick="setTargetID(<%=reservation.getID()%>)"
										data-toggle="modal" data-target="#contact-data" type="button">联系</button>
									<button class="btn btn-success"
										onclick="setTargetID(<%=reservation.getID()%>)"
										data-toggle="modal" data-target="#remark-data" type="button">评价</button>
									<button class="btn btn-danger"
										onclick="setTargetID(<%=reservation.getID()%>)"
										data-toggle="modal" data-target="#feedback-data" type="button">反馈</button>
									<a
										href="MyReservationManageServlet?method=getForID&id=${viewComponents.reservationID}"
										class="btn btn-info">详情</a>
								</div>
							</div>

							<div class="panel-body">
								<p><%=requiredTimeAndPlace%></p>
								<label><%="设备：" + equipmentDao.getEquipmentName(reservation.getEquipmentID())%></label>
								<label><%=detail%></label>
							</div>
						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>

	<!-- 撤销弹出窗内容 -->
	<div class="modal" id="cancel-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="cancelForm" role="form"
					action="MyReservationManageServlet?method=cancel&targetID=用js赋值"
					method="post" class="registration-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">撤销</h4>
					</div>
					<div class="modal-body">
						<label>你确定要取消该预约单么？</label>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-default">确定</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- 评价弹出窗内容 -->
	<div class="modal" id="remark-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="remarkForm" role="form"
					action="MyReservationManageServlet?method=setRemark&targetID=用js赋值"
					method="post" class="registration-form">
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
				<form id="feedBackForm" role="form"
					action="MyReservationManageServlet?method=setFeedBack&targetID=用js赋值"
					method="post" class="registration-form">
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

	<script type="text/javascript">
	function setTargetID(id){
		document.getElementById("cancelForm").action="MyReservationManageServlet?method=cancel&targetID="+id;
		document.getElementById("remarkForm").action="MyReservationManageServlet?method=setRemark&targetID="+id;
		document.getElementById("feedBackForm").action="MyReservationManageServlet?method=setFeedBack&targetID="+id;
		
	}
	
	</script>



	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
</body>

</html>