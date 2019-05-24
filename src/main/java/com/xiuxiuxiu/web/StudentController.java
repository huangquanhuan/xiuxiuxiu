package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Activity;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.StudentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
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

	@RequestMapping("/list")
	public String list(Model model) {
		List<Student> students = studentService.getStudentList();
		model.addAttribute("users", students);
		return "student/list";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		List<Student> students = studentService.getStudentList();
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("users", students);
		model.addAttribute("activityList", activityList);
		return "student/HomePage";
	}

	@RequestMapping("/toAdd")
	public String toAdd() {
		return "student/studentAdd";
	}

	@RequestMapping("/add")
	public String add(Student student) {
		studentService.save(student);
		return "redirect:/list";
	}

	@RequestMapping("/toEdit")
	public String toEdit(Model model, int id) {
		Student student = studentService.findStudentById(id);
		model.addAttribute("student", student);
		return "student/studentEdit";
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
	public String login(HttpSession session, String phoneNumber, String password) {

		// 登陆成功

		Student student = studentService.findStudentByPhoneNumber(phoneNumber);
		session.setAttribute("user", student);

		return "/student/HomePage";
	}

}
