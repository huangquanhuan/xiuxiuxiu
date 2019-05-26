package com.xiuxiuxiu.web;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/*
 * 用于发送邮箱，参数为接受者、发送者以及标题、内容
 */
public class SendEmail {

	public static void simpleMailSend(String email,String subject,String msg) {
		 //创建邮件发送服务器
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
	        mailSender.setHost("smtp.163.com");
//	      mailSender.setPort(465);
	        mailSender.setUsername("fzuxiuxiuxiu@163.com");
	        mailSender.setPassword("123xiuxiuxiu");
	        //加认证机制
	        Properties javaMailProperties = new Properties();
	    	javaMailProperties.put("mail.smtp.auth", true); 
	    	javaMailProperties.put("mail.smtp.starttls.enable", true); 
	    	javaMailProperties.put("mail.smtp.timeout", 30000); 
	        mailSender.setJavaMailProperties(javaMailProperties);
	        //创建邮件内容
	        SimpleMailMessage message=new SimpleMailMessage();
	        message.setFrom("fzuxiuxiuxiu@163.com");
	        message.setTo(email);
	        message.setSubject(subject);
	        message.setText(msg);
	        //发送邮件
	        mailSender.send(message);
	        System.out.println("发送成功");
		} 

}
