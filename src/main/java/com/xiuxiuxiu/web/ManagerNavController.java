package com.xiuxiuxiu.web;

import com.xiuxiuxiu.model.Activity;

import com.xiuxiuxiu.model.Manager;
import com.xiuxiuxiu.model.Reservation;
import com.xiuxiuxiu.model.Student;
import com.xiuxiuxiu.service.ActivityService;
import com.xiuxiuxiu.service.ManagerService;
import com.xiuxiuxiu.service.ReservationService;
import com.xiuxiuxiu.utility.MyMD5Util;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
public class ManagerNavController {

	@Resource
	ManagerService managerService;
	@Resource
	ActivityService activityService;	
	@Resource
	ReservationService reservationService;	

	@RequestMapping("/manager")
	public String index(Model model) {
		List<Activity> activityList = activityService.getActivityList();
		model.addAttribute("activityList", activityList);
		
		List<Reservation> reservationsList = reservationService.getReservationList();
		int reservationCount = reservationsList.size();
		model.addAttribute("reservationCount", reservationCount);
		int serviceEquipmentCount = 0;
		for(Reservation reservation:reservationsList) {
			if(reservation.getEquipment()!=null)
				serviceEquipmentCount++;
		}
		model.addAttribute("serviceEquipmentCount", serviceEquipmentCount);
		model.addAttribute("reservationCount", reservationCount);
		return "home/MHomePage";
	}
	


	@RequestMapping("/manager/login")
	public String login(Model model ,@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("password") String password,HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {

		Manager manager = managerService.findManagerByPhoneNumber(phoneNumber);
		
		if(manager == null) {
			model.addAttribute("err", "抱歉，该账号不存在！");
			System.out.println("登陆账号不存在！");
		} else if (manager.getPhoneNumber().length() < 1) {
			model.addAttribute("err", "请输入登录名！");
		} else if (manager.getPassword().length() < 1) {
			model.addAttribute("err", "请输入密码！");
		} else if (!MyMD5Util.validPassword(password,manager.getPassword())) {
			// 登陆失败
			System.out.println("真实密码："+manager.getPassword());
			System.out.println("输入密码："+password);
			model.addAttribute("err", "密码错误！");
			System.out.println("登陆密码错误！");
		} else {
			// 登陆成功
			session.setAttribute("administrator", manager);
			
		}
		
		return index(model);
	}
	
	@RequestMapping("manager/exit")
	public String exit(Model model, HttpSession session) {
		session.invalidate();
		return "redirect:/index";
		
	}
	
	@RequestMapping("manager/editInfo")
	public String editInfo(Model model, HttpSession session,@RequestParam("name") String name,
			@RequestParam("address") String address,@RequestParam("email") String email) {
		if(session.getAttribute("administrator")==null) {
    		model.addAttribute("err", "登陆信息已过期，请重新登录！");
    		return "redirect:/manager";
    	}
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

	@RequestMapping("/manager/reset_pw")
	public String reset(Model model, @RequestParam("re_phone") String re_phone,@RequestParam("old_password") String old_password,@RequestParam("re_password1") String re_password1,
			@RequestParam("re_password2") String re_password2,  @RequestParam("re_email") String re_email,
			@RequestParam("resetpw_code") String ret_code,HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("手机号码:" + re_phone);
		System.out.println("邮箱:" + re_email);
		System.out.println("验证码" +ret_code);
		System.out.println("原密码:" + old_password);
		System.out.println("新密码:" + re_password1);
		System.out.println("确认密码:" + re_password2);
		Manager manager = managerService.findManagerByPhoneNumber(re_phone);
		if(!MyMD5Util.validPassword(old_password,manager.getPassword())) {
			model.addAttribute("err", manager.getPassword());
			System.out.println("原密码错误！");
		}else if (!re_password1.equals(re_password2)) {
			model.addAttribute("err", "两次密码不同！");
			System.out.println("两次密码不同！");
		}else if (!ret_code.equals(session.getAttribute("res_code"))) {
			model.addAttribute("err", "验证码错误！");
			System.out.println("验证码错误！");
		} else{
			String encryptedPassword = null;   
			try {   
				encryptedPassword = MyMD5Util.getEncryptedPwd(re_password1);   
				System.out.println("加密后的密码："+encryptedPassword);
				manager.setPassword(encryptedPassword);
				
			} catch (NoSuchAlgorithmException e) {   
				model.addAttribute("err", "加密算法在当前环境中不可用，注册失败！");
				System.out.println("加密算法在当前环境中不可用，注册失败！");
				e.printStackTrace();   
				return index(model);
			} catch (UnsupportedEncodingException e) {   
				model.addAttribute("err", "密码包含不支持的字符编码，注册失败！");
				System.out.println("密码包含不支持的字符编码，注册失败！");
				e.printStackTrace();   
				return index(model);
			}
			
			
			try {
				managerService.edit(manager);
				model.addAttribute("message", "重置密码成功!");
				session.setAttribute("user", manager);
				System.out.println("重置密码成功");
			} catch (Exception e) {
				model.addAttribute("err", "抱歉，由于数据库原因，重置失败");
				System.out.println("抱歉，由于数据库原因，重置失败");
				e.printStackTrace();
			}
		}
		return index(model);
	}
	
}
