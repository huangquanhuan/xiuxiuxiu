<%@ page language="java" contentType="text/html;charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<!-- 注册Modal -->
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>

				<div class="signin-form profile">
					<h3 class="agileinfo_sign">注册</h3>
					<div class="register-form">
						<form class="registerForm" action="StudentServlet?type=register"
							method="post">
							<input id="name" type="text" name="name" placeholder="姓名"
								required="required"> <input id="phonenumber" type="text"
								name="phonenumber" placeholder="手机号码" required="required">
							<input id="address" type="text" name="address"
								placeholder="地址(例如：1#101)" required="required"> <input
								id="password" type="password" name="password" placeholder="密码"
								required="required"> <input type="password"
								name="password" id="password2" placeholder="确认密码"
								required="required"> <input type="submit" value="注册">
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>
</div>