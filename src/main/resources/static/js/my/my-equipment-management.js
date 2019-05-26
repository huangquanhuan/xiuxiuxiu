//该js仅用于用户端的个人设备管理弹窗页
//实现按钮动态变化

var map = {};
function changeDisabled(btn, id) {
	var btn_text = btn.firstChild;
	var form = btn.parentNode;
	var input = form.childNodes[3];// 输入框
	var btn1 = form.childNodes[5];// 第一个按钮
	var btn2 = form.childNodes[7];// 第二个按钮
	var btn1_text = btn1.firstChild;
	var btn2_text = btn2.firstChild;

	if (btn_text.nodeValue == "修改") {
		map[id] = input.value;
		input.removeAttribute("disabled");// 将输入框设为可编辑
		// 将第一个button变为红色确定按钮
		btn1_text.nodeValue = "确定";
		btn1.setAttribute("class", "btn btn-danger");
		btn1.setAttribute("type", "button");
		// 将第二个button变为绿色取消按钮
		btn2_text.nodeValue = "取消";
		btn2.setAttribute("class", "btn btn-success");
		btn2.setAttribute("type", "button");
		btn2.setAttribute("onclick", "changeDisabled(this," + id + ")");
	} else if (btn_text.nodeValue == "取消") {
		input.value = map[id];// 还原输入框的值
		input.setAttribute("disabled", "disabled");// 将输入框设为不可编辑
		// 将第一个button变为绿色色修改按钮
		btn1_text.nodeValue = "修改";
		btn1.setAttribute("class", "btn btn-success");
		btn1.setAttribute("type", "button");
		// 将第一个button变为绿色色修改按钮
		btn2_text.nodeValue = "删除";
		btn2.setAttribute("class", "btn btn-danger");
		btn2.setAttribute("type", "button");
		btn2.setAttribute("onclick", "del(this)");
	} else if (btn_text.nodeValue == "确定") {
		// 修改action为编辑
		form.action = "student/equipmentEdit";
		// 提交修改
		btn1.setAttribute("type", "submit");
	} else{
		alert("按钮状态未知！！")
	}
}

function del(btn) {
	var form = btn.parentNode;
	form.action = "student/equipmentDelete";
	btn.setAttribute("type", "submit");
}

function addInput() {
	// 获取前一个form元素
	var form = document.getElementById("equipment-form-add")
	form.style.display = "block";
}

function removeInput() {
	// 获取前一个form元素
	var form = document.getElementById("equipment-form-add")
	form.style.display = "none";
}
