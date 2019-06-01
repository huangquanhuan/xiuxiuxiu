// 验证用户输入的数据正确性
	
	function ret_validateInput() {
		var phone = document.getElementById("reset-phoneNumber").value;
		var email = document.getElementById("reset_email").value;
		var pass2 = document.getElementById("reset_password2").value;
		var pass1 = document.getElementById("reset_password1").value;
		var code = document.getElementById("reset_code").value;
		if(!isPhoneNumber(phone)){
			document.getElementById("reset-phoneNumber").setCustomValidity("手机号码格式错误");
		}else if (pass1 != pass2){
			document.getElementById("reset_password2").setCustomValidity("密码不匹配！");
		}else if(!isEmail(email)){
			document.getElementById("reset_email").setCustomValidity("邮箱格式错误");
		}else{
			document.getElementById("reset_password2").setCustomValidity('');
			document.getElementById("reset_email").setCustomValidity('');
			document.getElementById("reset-phoneNumber").setCustomValidity('');
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
