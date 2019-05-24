package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller
public class StudentController {

	@Resource
	StudentService studentService;
	@Resource
	ActivityService activityService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("activityList", activityList);
		return "home/HomePage";
	}


	@RequestMapping("/edit")
	public String edit(Student student) {
		studentService.edit(student);
		return "redirect:/list";
	}

	@RequestMapping("/delete")
	public String delete(int id) {
		studentService.delete(id);
		return "redirect:/list";
	}

	@RequestMapping("/student/login")
	public String login(Model model ,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("password") String password,HttpSession session) {

		System.out.println("电话："+phoneNumber);
		Student student = studentService.findStudentByPhoneNumber(phoneNumber);
		
		if(student==null) {
			model.addAttribute("err", "抱歉，该账号不存在！");
			System.out.println("登陆账号不存在！");
		} else if (student.getPhoneNumber().length() < 1) {
			model.addAttribute("err", "请输入登录名！");
		} else if (student.getPassword().length() < 1) {
			model.addAttribute("err", "请输入密码！");
		} else if (!student.getPhoneNumber().equals(phoneNumber)) {
			// 登陆失败
			System.out.println("真实密码："+student.getPhoneNumber());
			System.out.println("输入密码："+phoneNumber);
			model.addAttribute("err", "密码错误！");
			System.out.println("登陆密码错误！");
		} else {
			// 登陆成功
			session.setAttribute("user", student);
		}
		
		return "redirect:/home";
	}

}
