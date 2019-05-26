// 验证用户输入的数据正确性
	
	function validateInput() {
		var name = document.getElementById("register-name").value;
		var phone = document.getElementById("register-phoneNumber").value;
		var address = document.getElementById("address").value;
		var email = document.getElementById("register-email").value;
		var pass2 = document.getElementById("password2").value;
		var pass1 = document.getElementById("password1").value;
		var code = document.getElementById("code").value;
		if (pass1 != pass2){
			document.getElementById("password2").setCustomValidity("密码不匹配！");
		}else if(name.length<2||name.length>12){
			document.getElementById("register-name").setCustomValidity("昵称长度在2~12之间");
		}else if(!isPhoneNumber(phone)){
			document.getElementById("register-phoneNumber").setCustomValidity("手机号码格式错误");
		}else if(!isEmail(email)){
			document.getElementById("register-email").setCustomValidity("邮箱格式错误");
		}else{
			document.getElementById("register-name").setCustomValidity('');
			document.getElementById("password2").setCustomValidity('');
			document.getElementById("register-email").setCustomValidity('');
			document.getElementById("register-phoneNumber").setCustomValidity('');
		}
		//empty string means no validation error
	}
	/*
	 * 判断是否为手机号
	 */
	function isPhoneNumber(phone){
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
		if(!myreg.test(phone)){
			return false;
		}else{
			return true;
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
