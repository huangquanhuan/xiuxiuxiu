package com.xiuxiuxiu.web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyuncs.http.HttpRequest;
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
			String title="欢迎注册修咻咻平台，请验证您的邮箱账号";
			String text="您的验证码为:"+code+"如非本人操作请忽略该信息";
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
		
		@RequestMapping("/sendcontact")
		@ResponseBody
		public void sendContact(Model model,HttpSession session,HttpServletRequest req) {
			String receiver = req.getParameter("Email").trim();
			String subject = req.getParameter("Subject").trim();
			String name = req.getParameter("Name").trim();
			String text = req.getParameter("Message").trim()+"\n"+"发送者为:"+name+"\n"+"邮箱为:"+receiver;
			System.out.println("发送者姓名:"+name);
			System.out.println("邮件主题:"+subject);
			System.out.println("邮件正文:"+text);
			System.out.println("发送者邮箱:"+receiver);
			try {
				SendEmail.simpleMailSend("fzuxiuxiuxiu@163.com", subject, text);
			}catch(Exception e) {
				model.addAttribute("msg","反馈发送失败");
				e.printStackTrace();
			}
			model.addAttribute("msg","反馈发送成功");
			model.addAttribute("receiver",receiver);
		}
		
}
