<%--
    make-a-reservation-1.jsp
    用户端预约界面（第一步：确认个人信息）
    author: 刘忠燏
 --%>

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@page import="xiuxiuxiu.pojo.User"%>


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
	<%@include file="导航栏.jsp"%>
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
						<input class="form-control" type="tel" id="name" name="name"
							value="<%=name%>" readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="studentId">学号</label>
					<div class="col-sm-10">
						<input class="form-control" type="tel" id="studentId"
							value="<%=studentID%>" name="studentId" readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="contact">联系方式</label>
					<div class="col-sm-10">
						<input class="form-control" type="tel" id="contact" name="contact"
							value="<%=tel%>" readonly />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label" for="address">地址</label>
					<div class="col-sm-10">
						<input class="form-control" type="tel" id="address" name="contact"
							value="<%=address%>" readonly />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a href="makeReservation?method=beforeReserve" class="btn btn-success">下一步</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- 主体内容：结束 -->

	<%@include file="注册弹窗.jsp"%>
	<%@include file="个人信息修改弹窗.jsp"%>
	<%@include file="登录弹窗.jsp"%>
	<%@include file="退出登录弹窗.jsp"%>
	<%@include file="动态js代码.jsp"%>
</body>
</html>