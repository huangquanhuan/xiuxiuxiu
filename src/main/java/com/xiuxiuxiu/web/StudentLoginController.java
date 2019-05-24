//
//package com.xiuxiuxiu.web;
//
//import com.xiuxiuxiu.model.Activity;
//import com.xiuxiuxiu.model.Student;
//import com.xiuxiuxiu.service.ActivityService;
//import com.xiuxiuxiu.service.StudentService;
//
//import antlr.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//public class StudentLoginController {
//	
//	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
//	@ResponseBody
//	public String userLogin(HttpServletRequest request, HttpSession session) {
//	
//		String userName = request.getParameter("username");
//		String password = request.getParameter("password");
//		if (!userName.equals("") && !password.equals("")) {
//			ew.eq("user_name", userName);
//			ew.eq("pass_word", pwd);
//			ew.eq("status", AIAppConstants.STATUS);
//			User user = userService.selectOne(ew);
//			if (null == user) {
//				// return "redirect:/login";
//				return "Error";
//			}
//			
//			session.setAttribute("sessionUser", user);
//		}
//		return "OK";
//		// return "redirect:/index";
// 
//	}
//}
