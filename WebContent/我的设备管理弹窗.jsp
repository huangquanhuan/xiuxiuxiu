<%@page import="xiuxiuxiu.pojo.Student"%>
<%@page import="xiuxiuxiu.pojo.Equipment"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<!-- 我的设备管理弹出页窗内容 -->
	<div class="modal" id="myDevice-data" tabindex="-1" role="dialog"
		aria-labelledby="mySmallModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">我的设备管理</h4>
				</div>
				<div class="modal-body">
					<!-- Top content -->
					<div class="top-content text-center">

						<%
							Student student = (Student) session.getAttribute("name");
							if (student != null) {
						%>
						<c:forEach items="<%=student.getEquipment()%>" var="equipment">
							<form class="form-inline" role="form"
								action="MyEquipmentServlet?id=${equipment.id}&method="
								method="post">
								<input type="text" class="form-control"
									value="${equipment.equipmentName}" name="equipmentName"
									disabled="disabled">
								<button type="button" class="btn btn-success"
									onclick="changeDisabled(this,${equipment.id})">修改</button>
								<button type="button" class="btn btn-danger"
									onclick="del(this,${equipment.id})">删除</button>

							</form>
							<br>
						</c:forEach>

						<form role="form" action="MyEquipmentServlet?method=add"
							method="post">
							<button class="btn btn-info btn-lg text-center" type="button"
								data-toggle="tooltip" title="新增" id="addCenterIpGrpBtn"
								onclick="addCenterIpGrp(this)" disabled>
							写到这了	<span class="glyphicon glyphicon-plus">添加设备</span>
							</button>
						</form>


						<%
							}
						%>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	function changeDisabled(btn, id) {
		var btn_text = btn.firstChild;
		var form = btn.parentNode;
		var input = btn.parentNode.childNodes[1];

		if (btn_text.nodeValue == "修改") {
			input.removeAttribute("disabled");
			btn_text.nodeValue = "确定";
			btn.setAttribute("class", "btn btn-danger");
			btn.setAttribute("type", "button");
		} else {
			input.setAttribute("disabled", "disabled");
			btn_text.nodeValue = "修改";
			btn.setAttribute("class", "btn btn-success");
			
			form.action="MyEquipmentServlet?id="+id+"&method=edit";
			
			btn.setAttribute("type", "submit");
		}
	}

	function del(btn, id) {
		var form = btn.parentNode;
			
		form.action="MyEquipmentServlet?id="+id+"&method=delete";
			
		btn.setAttribute("type", "submit");
		
	}
</script>

</html>
