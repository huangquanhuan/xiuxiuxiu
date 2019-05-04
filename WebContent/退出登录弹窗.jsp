<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!-- 退出登录弹窗 -->
<div class="modal fade" id="myModal6" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>

				<div class="signin-form profile">
					<h3 class="agileinfo_sign">是否退出登录？</h3>
					<div class="login-form">
						<form action="StudentServlet?type=exit" method="post">
							<input type="submit" value="确定">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
