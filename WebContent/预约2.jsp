<%--
    make-a-reservation-1.jsp
    用户端预约界面（第一步：确认个人信息）
    author: 刘忠燏
 --%>

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<title>维修预约 - 第二步</title>

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
	<%@include file="导航栏.jsp"%>
	<div class="agile_inner_banner_info">
		<h2>预约维修</h2>
	</div>
	<!-- 主体内容：开始 -->
	<div class="services">
		<div class="container">
			<ul id="formTab" class="nav nav-tabs" role="tablist">
				<li class="nav-item"><a class="hvr-underline-from-center"
					data-toggle="tab" href="#field-service">活动预约</a></li>
				<li><a class="hvr-underline-from-center" data-toggle="tab"
					href="#door-to-door-service">上门服务</a></li>
			</ul>

			<div class="tab-content">
				<!-- 申请现场维修的表单：开始 -->
				<div id="field-service" class="container tab-pane active">
					<br />
					<form action="makeReservation?method=addFieldService" method="post" enctype="multipart/form-data">
						<!-- 场次选择 -->
						<div class="form-group">
							<label for="session-select" class="col-sm-3">预约场次</label>
							<div class="col-sm-9">
								<select id="session-select" class="form-control" name="activity">
									<!-- 这里装入场次时间信息 -->
									<c:forEach items="${activities}" var="activity">
										<option value="${activity.ID}">${activity.time}，地点：${activity.place}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<!-- 设备选择 -->
						<div class="form-group">
							<div class="col-sm-3">
								<label for="device-select">设备</label> <a href="#"
									class="btn btn-link">添加设备</a>
							</div>
							<div class="col-sm-9">
								<select id="device-select" class="form-control" name="device">
									<!-- 这里装入用户的设备列表 -->
									<c:forEach items="${equipments}" var="equipment">
										<option value="${equipment.id}">${equipment.equipmentName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<!-- 需求零件选择 -->
						<div class="form-group">
							<label for="component-select" class="col-sm-3">需求零件</label>
							<div id="accordion" class="col-sm-9">
								<div class="card">
									<div class="bg-info card-header">
										<a class="card-link text-muted" data-toggle="collapse"
											href="#component-list">可选零件</a>
									</div>
									<div id="component-list" class="collapse fade"
										data-parent="#accordion">
										<!-- 这里装入零件列表，插入格式见下面的样例 -->
										<c:forEach items="${components}" var="component">
											<div class="card-body">
												<label><input type="checkbox"
													name="neededComponents" value="${component.id}">${component.name}</label>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>

						<!-- 问题类型 -->
						<div class="form-group">
							<label for="issue-type-select" class="col-sm-3">问题种类</label>
							<div id="issue-type-select" class="col-sm-9">
								<select class="form-control">
									<option>硬件问题</option>
									<option>软件问题</option>
								</select> <select class="form-control">
									<!-- 这里根据前一个选项框的选择，显示对应的子分类 -->
									<option>子分类 1</option>
									<option>子分类 2</option>
								</select>
							</div>
						</div>

						<!-- 详细描述问题 -->
						<div class="form-group">
							<label for="issue-detail" class="col-sm-3">问题描述</label>
							<div id="issue-detail" class="col-sm-9">
								<textarea class="form-control" rows="3" name="issueDetail"></textarea>
							</div>
						</div>

						<!-- 图片上传 -->
						<div class="form-group">
							<label for="issue-image" class="col-sm-3">为问题描述提供图片</label>
							<div class="col-sm-9">
								<input id="issue-image" name="issueImage"  type="file" class="form-control" />
							</div>
						</div>

<!-- 						备注 -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="issue-comment" class="col-sm-3" name="">备注</label> -->
<!-- 							<div class="col-sm-9"> -->
<!-- 								<textarea id="issue-comment" class="form-control" rows="3"></textarea> -->
<!-- 							</div> -->
<!-- 						</div> -->

						<!-- 提交按钮 -->
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-9">
								<input type="submit" class="btn btn-primary" value="提交" />
							</div>
						</div>
					</form>
				</div>
				<!-- 申请现场维修的表单：结束 -->

				<!-- 申请上门服务的表单：开始 -->
				<div id="door-to-door-service" class="container tab-pane fade">
					<br />
					<form action="makeReservation?method=addDoorToDoorService" method="post" enctype="multipart/form-data">
						<!-- 时间选择 -->
						<div class="form-group">
							<label for="datetime-select" class="col-sm-3">预约时间</label>
							<div class="col-sm-9">
								<input class="form-control" type="datetime-local" name="requiredTime" />
							</div>
						</div>

						<!-- 设备选择 -->
						<div class="form-group">
							<div class="col-sm-3">
								<label for="device-select-1">设备</label> <a href="#"
									class="btn btn-link">添加设备</a>
							</div>
							<div class="col-sm-9">
								<select id="device-select-1" class="form-control"  name="device">
									<!-- 这里装入用户的设备列表 -->
									<c:forEach items="${equipments}" var="equipment">
										<!-- 用户设备信息 -->
										<option value="${equipment.id}">${equipment.equipmentName}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<!-- 需求零件选择 -->
						<div class="form-group">
							<label for="component-select-1" class="col-sm-3">需求零件</label>
							<div id="accordion-1" class="col-sm-9">
								<div class="card">
									<div class="bg-info card-header">
										<a class="card-link text-muted" data-toggle="collapse"
											href="#component-list-1">可选零件</a>
									</div>
									<div id="component-list-1" class="collapse fade"
										data-parent="#accordion-1">
										<!-- 这里装入零件列表，插入格式见下面的样例 -->
										<c:forEach items="${components}" var="component">
											<div class="card-body">
												<label><input type="checkbox"
													name="neededComponents" value="${component.id}">${component.name}</label>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>

						<!-- 问题类型 -->
						<div class="form-group">
							<label for="issue-type-select-1" class="col-sm-3">问题种类</label>
							<div id="issue-type-select-1" class="col-sm-9">
								<select class="form-control">
									<option>硬件问题</option>
									<option>软件问题</option>
								</select> <select class="form-control">
									<!-- 这里根据前一个选项框的选择，显示对应的子分类 -->
									<option>子分类 1</option>
									<option>子分类 2</option>
								</select>
							</div>
						</div>

						<!-- 详细描述问题 -->
						<div class="form-group">
							<label for="issue-detail-1" class="col-sm-3">问题描述</label>
							<div id="issue-detail-1" class="col-sm-9">
								<textarea class="form-control" rows="3" name="issueDetail"></textarea>
							</div>
						</div>

						<!-- 图片上传 -->
						<div class="form-group">
							<label for="issue-image-1" class="col-sm-3">为问题描述提供图片</label>
							<div class="col-sm-9">
								<input id="issue-image-1" name="issueImage" type="file" class="form-control" />
							</div>
						</div>

						<!-- 备注 -->
<!-- 						<div class="form-group"> -->
<!-- 							<label for="issue-comment-1" class="col-sm-3">备注</label> -->
<!-- 							<div class="col-sm-9"> -->
<!-- 								<textarea id="issue-comment-1" class="form-control" rows="3"></textarea> -->
<!-- 							</div> -->
<!-- 						</div> -->

						<!-- 提交按钮 -->
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-9">
								<input type="submit" class="btn btn-primary" value="提交" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 主体内容：结束 -->

	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>	
	<%@include file="我的设备管理弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
</body>
</html>