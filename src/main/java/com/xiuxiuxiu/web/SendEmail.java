package com.xiuxiuxiu.web;

import java.security.GeneralSecurityException;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.sun.mail.util.MailSSLSocketFactory;

/*
 * 用于发送邮箱，参数为接受者、发送者以及标题、内容
 */
public class SendEmail {

	public static void simpleMailSend(String email,String subject,String msg) {
		 //创建邮件发送服务器
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
	        mailSender.setHost("smtp.qq.com");
	        mailSender.setPort(465);//ssl加密，避开25端口
	        mailSender.setUsername("2238114559@qq.com");
	        mailSender.setPassword("iogjgitbdjvkdifi");//qq邮箱验证码
	        //加认证机制
	        
	    	 Properties properties = new Properties();
	         properties.setProperty("mail.smtp.auth", "true");//开启认证
	         properties.setProperty("mail.debug", "true");//启用调试
	         properties.setProperty("mail.smtp.starttls", "true");
	         //Transport使用SSL连接邮箱协议名称需要使用smtps，而不是smtp
	         //properties.setProperty("mail.transport.protocol", "smtps");
	         properties.setProperty("mail.smtp.timeout", "1000");//设置链接超时
	         // SSL  
	         properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
	         properties.setProperty("mail.smtp.socketFactory.fallback", "false");  
	         properties.setProperty("mail.smtp.port", "465");  
	         properties.setProperty("mail.smtp.socketFactory.port", "465"); 
	         MailSSLSocketFactory sf = null;
	         try {
	             sf = new MailSSLSocketFactory();
	         } catch (GeneralSecurityException e) {
	             e.printStackTrace();
	         }
	         sf.setTrustAllHosts(true);
	         properties.put("mail.smtp.ssl.enable", "true");
	         properties.put("mail.smtp.ssl.socketFactory", sf);
	         mailSender.setJavaMailProperties(properties);
	        //创建邮件内容
	        SimpleMailMessage message=new SimpleMailMessage();
	        message.setFrom("2238114559@qq.com");
	        message.setTo(email);
	        message.setSubject(subject);
	        message.setText(msg);
	        //发送邮件
	        mailSender.send(message);
	        System.out.println("发送成功");
		} 

}
