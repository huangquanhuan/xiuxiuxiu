package com.xiuxiuxiu.web;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuxiu.web.SendCode;
@Controller
public class SendCodeController {
@RequestMapping("/sendcode")
@ResponseBody//此注解不能省略 否则ajax无法接受返回值
public void send(Model model,@RequestParam("phoneNumber") String phoneNumber,HttpSession session) {
	String code =(int) ((Math.random()*9+1)*100000)+"";
	session.setAttribute("code", code);
	System.out.println("验证码为"+code);
	try {
		SendCode.sendSms(phoneNumber, code,"SMS_163852856");  //调用短信发送接口，三个参数，手机号，验证码，短信模板
	}catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	model.addAttribute("msg", "验证码发送成功！");
	model.addAttribute("phoneNumber", phoneNumber);
	System.out.println(phoneNumber);
}

}
