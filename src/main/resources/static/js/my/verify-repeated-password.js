// 重复密码验证 
	function validatePassword() {
		var pass2 = document.getElementById("password2").value;
		var pass1 = document.getElementById("password1").value;
		if (pass1 != pass2)
			document.getElementById("password2").setCustomValidity(
					"密码不匹配！");
		else
			document.getElementById("password2").setCustomValidity('');
		//empty string means no validation error
	}
