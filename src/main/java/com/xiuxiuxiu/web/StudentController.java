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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


	@RequestMapping("/student/edit")
	public String edit(HttpSession session,HttpServletRequest request) {
		
		String changeName = request.getParameter("name");
		String changeStudentId = request.getParameter("studentId");
		String changeEmail = request.getParameter("email");
		String changeAddress = request.getParameter("address");
		Student user = (Student) session.getAttribute("user");
		user.setName(changeName);
		user.setStudentId(changeStudentId);
		user.setEmail(changeEmail);
		user.setAddress(changeAddress);
		
		studentService.edit(user);
		return "redirect:/home";
	}

	@RequestMapping("/student/delete")
	public String delete(int id) {
		studentService.delete(id);
		return "redirect:/home";
	}

	@RequestMapping("/student/login")
	public String login(Model model ,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("password") String password,HttpSession session) {

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
	@RequestMapping("student/register")
	public String register(Model model,@RequestParam("name") String name,@RequestParam("phoneNumber") String phoneNumber,@RequestParam("passWord") String passWord,
			@RequestParam("passWord2") String passWord2,@RequestParam("address") String address,HttpSession session) {
		System.out.println("昵称:"+name);
		System.out.println("号码:"+phoneNumber);
		System.out.println("地址:"+address);
		System.out.println("密码:"+passWord);
		System.out.println("确认密码:"+passWord2);
		if(!passWord.equals(passWord2)) {
			model.addAttribute("err", "两次密码不同！");
			System.out.println("两次密码不同！");
		}else if(!isMobileNO(phoneNumber)) {
			model.addAttribute("err", "手机号格式错误！");
			System.out.println("手机号格式错误！");
		}else if(name.length()<2||name.length()>12) {
			model.addAttribute("err","昵称长度范围在“2~12”之间");
			System.out.println("昵称长度范围在“2~12”之间");
		}else {
			Student student = new Student();
			student.setName(name);
			student.setAddress(address);
			student.setPassword(passWord);
			student.setPhoneNumber(phoneNumber);
			student.setAccessLevel(0);
			studentService.save(student);
			System.out.println("注册成功");
		}
		return "redirect:/home";
	}
	/*
	 * 判断手机号格式
	 */
	public boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
		}
	/*
	 * 判断邮箱格式
	 */
	public boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
		}
}
