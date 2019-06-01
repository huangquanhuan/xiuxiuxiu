var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function resetPwMessage() {
curCount = count;
var email = document.getElementById("re_email").value;
var oError = document.getElementById("error")
var isError = true;
if(!isEmail(email)){
	$("#error")[0].style.display = 'block'; 
	oError.innerHTML = "邮箱格式错误";
	isError = false;
}else{
// 设置button效果，开始计时
//	$("#error")[0].style.display = 'block'; 
//	oError.innerHTML = "正在发送中";
document.getElementById("btnResetPwCode").setAttribute("disabled","true" );//设置按钮为禁用状态
InterValObj = window.setInterval(RetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
document.getElementById("btnResetPwCode").value="在" + curCount + "秒后再次获取";//更改按钮文字
// 向后台发送处理数据
$.ajax({
  	 type:"post",
  	 url:"/resetemail",
  	 data : {"email":email},
  	 dataType:"json",
  	 beforeSend:function(){
  		
  		 }, 
  	 success:function(){
  		oError.innerHTML = "已发送";          
      }   
   });
}
}
function RetRemainTime() {
if (curCount == 0) {                
window.clearInterval(InterValObj);// 停止计时器
document.getElementById("btnResetPwCode").removeAttribute("disabled");//移除禁用状态改为可用
document.getElementById("btnResetPwCode").value="重新发送验证码";
}else {
curCount--;
document.getElementById("btnResetPwCode").value="在" + curCount + "秒后再次获取";
}
}
/*
 * 判断邮箱格式
 */
function isEmail(email){
	var myreg=/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
	if(!myreg.test(email)){
		return false;
	}else{
		return true;
	}
}
