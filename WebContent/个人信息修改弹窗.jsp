<%@page import="xiuxiuxiu.pojo.User"%>
<%@page import="xiuxiuxiu.pojo.Student"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<% Student student = (Student) session.getAttribute("name");
%>
<!-- 个人信息修改弹出页窗内容 -->
<div class="modal" id="changeinfo-data" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form role="form" action="ChangeInfoServlet" method="post"
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
									<h3><%=tel%></h3>
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
												value="<%=name%>" class="form-control"></td>
										</tr>
										<tr>
											<td>学号</td>
											<td><input type="text" name="studentID"
												value="<%=studentID%>" class="form-control"></td>
										</tr>
										<tr>
											<td>邮箱</td>
											<td><input type="text" name="Email"
												value="<%=email%>" class="form-control"></td>
										</tr>
										<tr>
											<td>地址</td>
											<td><input type="text" name="address"
												value="<%=address%>"" class="form-control">
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
