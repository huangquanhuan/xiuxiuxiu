//发送邮箱信的ajax
//提交Form表单
$("#sendEmail").unbind("click").click(function() {
var email = document.getElementById("register-email").value;
if(email == ""){  
	alert("邮箱错误");  
   return false;  
}else{
	$.ajax({
   	 type:"post",
   	 url:"/sendemail",
   	 data : {"email":"2609339303@qq.com"},
   	 dataType:"json",
   	 beforeSend:function(){
   		 $("#sendEmail").val("正在提交，请稍等...");
   		 }, 
   	 success:function(data){
          alert("success");	           
       }   
    });
//	 $ajax({
//	 type="post",
//	 url="/sendemail",
//	 dataType:"json",
//	 data:{"email":$("#email").val()},
//	 success:function (data){
//		 alert("success");
//	 },
//	 error:function (data){
//		 alert("false");
//	 }			
//});
}
});
//var countdown=60; 	
//var flag=1;
//$("#sendEmail").unbind("click").click(function(){
//	alert("sus");
//	var phoneNumber = document.getElementById("register-phoneNumber").value;
//	 if(phoneNumber == ""){  
//		alert("邮箱错误");  
//        return false;  
//	 }
//	 if(flag==1){
//		 $ajax({
//			 type="post",
//			 url="/sendemail",
//			 dataType:"json",
//			 data:{"email":$("#email").val()},
//			 success:function (data){
//				 alert("success");
//			 },
//			 error:function (data){
//				 alert("false");
//			 }			
//		 });
//	 }
//});