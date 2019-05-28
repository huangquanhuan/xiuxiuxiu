package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Activity;

import com.xiuxiuxiu.model.Manager;

import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.ManagerService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ManagerNavController {

	@Resource
	ManagerService managerService;
	

	@RequestMapping("/manager")
	public String index() {
		return "redirect:/manager/activity";
	}
	


	@RequestMapping("/manager/login")
	public String login(Model model ,@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password,HttpSession session) {

		Manager manager = managerService.findManagerByPhoneNumber(phoneNumber);
		
		if(manager == null) {
			model.addAttribute("err", "抱歉，该账号不存在！");
			System.out.println("登陆账号不存在！");
		} else if (manager.getPhoneNumber().length() < 1) {
			model.addAttribute("err", "请输入登录名！");
		} else if (manager.getPassword().length() < 1) {
			model.addAttribute("err", "请输入密码！");
		} else if (!manager.getPassword().equals(password)) {
			// 登陆失败
			System.out.println("真实密码："+manager.getPhoneNumber());
			System.out.println("输入密码："+phoneNumber);
			model.addAttribute("err", "密码错误！");
			System.out.println("登陆密码错误！");
		} else {
			// 登陆成功
			session.setAttribute("administrator", manager);
			
		}
		
		return "redirect:/manager";
	}
	
	@RequestMapping("manager/exit")
	public String exit(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/manager";
		
	}

}
