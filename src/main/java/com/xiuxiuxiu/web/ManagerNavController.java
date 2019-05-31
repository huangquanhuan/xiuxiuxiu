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
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class ManagerNavController {

	@Resource
	ManagerService managerService;
	@Resource
	ActivityService activityService;	

	@RequestMapping("/manager")
	public String inex(Model model) {
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("activityList", activityList);
		return "home/MHomePage";
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
		return "redirect:/index";
		
	}
	
	@RequestMapping("manager/editInfo")
	public String editInfo(Model model, HttpSession session,@RequestParam("name") String name,
			@RequestParam("address") String address,@RequestParam("email") String email) {

		Manager manager = (Manager) session.getAttribute("administrator");
		manager.setName(name);
		manager.setAddress(address);
		manager.setEmail(email);
		try {
			managerService.save(manager);
			System.out.println("管理员修改个人信息成功!");
			model.addAttribute("messsage", "管理员修改个人信息成功!");
		} catch (Exception e) {
			System.out.println("管理员修改个人信息失败!!");
			model.addAttribute("err", "管理员修改个人信息失败!!");
		}
		return "redirect:/manager";
		
	}

	
}
