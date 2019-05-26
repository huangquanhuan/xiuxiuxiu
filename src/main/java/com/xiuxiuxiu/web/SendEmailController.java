package com.xiuxiuxiu.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiuxiuxiu.web.SendEmail;
@Controller
public class SendEmailController {
		@RequestMapping("/sendemail")
		@ResponseBody 
		public void sendEmail(Model model,HttpSession session,HttpServletRequest req) {
			//生成五位数验证码，并存入session中
			String code = (int)((Math.random()*9+1)*10000)+"";
			session.setAttribute("email", code);
			String receiver = req.getParameter("email").trim();
			String title="验证您的电子邮箱地址";
			String text="您的验证码为:"+code;
			System.out.println("邮件接收者"+receiver);
			System.out.println("标题为"+title);
			System.out.println("正文为"+text);
			try {
				SendEmail.simpleMailSend(receiver, title, text);
			}catch(Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("msg","验证码发送成功");
			model.addAttribute("receiver",receiver);
		}
		
}
