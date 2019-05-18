<%@page import="xiuxiuxiu.pojo.Student"%>
<%@page import="xiuxiuxiu.pojo.Equipment"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<!-- 看要不要改变按钮的样式，要按钮变方的话就引用新的3.37版本的bootstrap -->
<!-- <link -->
<!-- 	href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" -->
<!-- 	rel="stylesheet"> -->
<body>

	<!-- 注意！！该文档别删除或增加行，用了dom的元素查找，有点迷~ -->
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



						<form id="form-add" class="form-inline asd" role="form"
							action="MyEquipmentServlet?method=add" method="post"
							style="display: none">
							<input type="text" class="form-control" name="equipmentName">
							<button type="submit" class="btn btn-info">确认添加</button>
							<button type="button" class="btn btn-default">取消</button>
						</form>
						<br>
						<button class="btn btn-info btn-lg" type="button"
							onclick="addInput()">
							<span class="glyphicon glyphicon-plus">添加设备</span>
						</button>

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
	var map={};
	function changeDisabled(btn,id) {
		var btn_text = btn.firstChild;
		var form = btn.parentNode;
		var input = form.childNodes[1];//输入框
		var btn1 = form.childNodes[3];//第一个按钮
		var btn2 = form.childNodes[5];//第二个按钮
		var btn1_text = btn1.firstChild;
		var btn2_text = btn2.firstChild;

		if (btn_text.nodeValue == "修改") {
			map[id]=input.value;
			input.removeAttribute("disabled");//将输入框设为可编辑
			//将第一个button变为红色确定按钮
			btn1_text.nodeValue = "确定";
			btn1.setAttribute("class", "btn btn-danger");
			btn1.setAttribute("type", "button");
			//将第二个button变为绿色取消按钮
			btn2_text.nodeValue = "取消";
			btn2.setAttribute("class", "btn btn-success");
			btn2.setAttribute("type", "button");
			btn2.setAttribute("onclick", "changeDisabled(this,"+id+")");
		} else if(btn_text.nodeValue == "取消"){
			input.value=map[id];//还原输入框的值
			input.setAttribute("disabled", "disabled");//将输入框设为不可编辑
			//将第一个button变为绿色色修改按钮
			btn1_text.nodeValue = "修改";
			btn1.setAttribute("class", "btn btn-success");
			btn1.setAttribute("type", "button");
			//将第一个button变为绿色色修改按钮
			btn2_text.nodeValue = "删除";
			btn2.setAttribute("class", "btn btn-danger");
			btn2.setAttribute("type", "button");
			btn2.setAttribute("onclick", "del(this,"+id+")");
		} else if(btn_text.nodeValue == "确定"){
			//修改action
			form.action="MyEquipmentServlet?id="+id+"&method=edit";
			//提交修改
			btn1.setAttribute("type", "submit");
		}
	}

	function del(btn, id) {
		var form = btn.parentNode;
		form.action="MyEquipmentServlet?id="+id+"&method=delete";
		btn.setAttribute("type", "submit");
	}
	
	function addInput(btn) {
		//获取前一个form元素
		var form = document.getElementById("form-add")
		form.style.display="block";
	}
</script>

</html>
