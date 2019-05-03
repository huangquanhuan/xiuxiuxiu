<!doctype html>
<!-- <%@ page language="java" contentType="text/html;charset=UTF-8" -->
<!--          pageEncoding="UTF-8" isELIgnored="false" %> -->
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
<title>个人信息修改页</title>

</head>

<body>
	<!-- data-target触发模态弹出窗元素 -->
	<button class="btn btn-info" data-toggle="modal"
		data-target="#changeinfo-data" type="button">修改个人信息</button>


	<!-- 个人信息修改弹出页窗内容 -->
	<div class="modal" id="changeinfo-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form role="form" action="..." method="post"
					class="registration-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span><span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title">个人信息修改</h4>
					</div>
					<div class="modal-body">
						<!-- Top content -->
						<div class="top-content">
							<div class="row">
								<div class="col-sm-8 col-sm-offset-2 text">
									<div class="description">
										<h3>13123191995</h3>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6 col-sm-offset-3 form-box">
									<div class="table-responsive">
										<table class="table">
											<tr>
												<td>姓名</td>
												<td><input type="text" name="name"
													placeholder="${user.getName()}" class="form-control">
												</td>
											</tr>
											<tr>
												<td>学号</td>
												<td><input type="text" name="name"
													placeholder="${user.getStudentID()}" class="form-control"></td>
											</tr>
											<tr>
												<td>邮箱</td>
												<td><input type="text" name="name"
													placeholder="${user.getEmail()}" class="form-control">
												</td>
											</tr>
											<tr>
												<td>地址</td>
												<td><input type="text" name="name"
													placeholder="${user.getAddress()}" class="form-control">
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="submit" class="btn btn-success">确认修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>



</body>

</html>