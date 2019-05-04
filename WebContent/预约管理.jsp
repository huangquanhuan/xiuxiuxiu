<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="注册弹窗.jsp"%>
<%@include file="个人信息修改弹窗.jsp"%>
<%@include file="登录弹窗.jsp"%>
<%@include file="退出登录弹窗.jsp"%>

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
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

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