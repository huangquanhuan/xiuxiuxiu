//发送短信的ajax
var countdown=60; 	
var flag=1;
function settime(val) {    
 	var phoneNumber = document.getElementById("register-phoneNumber").value;
 	 if(phoneNumber == ""){  
 		alert("号码错误");  
         return false;  
     } else{
     	if(flag==1){
     		$.ajax({
     	 		type:"POST",
     	 		dataType:"json",
     	 		url:"/sendcode",
     	 		data:phoneNumber,
//     	 		date:{
//     	 			phoneNumber:${"#register-phoneNumber"}.val()
//     	 			},
     	 		error:function(){
     	 			alert("发送失败");
     	 		},
     	 		success:function(data){
     	 			$("#tip").html("<font color='#339933'>√ 短信验证码已发到您的手机,请查收</font>");
     	 		}
     	 	})
     	}
     	flag=0;
 		if (countdown == 0) { 
 		val.removeAttribute("disabled");    
 		val.value="免费获取验证码"; 
 		countdown = 60;  
 		} else { 
 		val.setAttribute("disabled", true); 
 		val.value="重新发送(" + countdown + ")"; 
 		countdown--; 
 		} 
 		setTimeout(function() {   
 		settime(val) 
 		},1000) 
 		return false;
 		}
}
